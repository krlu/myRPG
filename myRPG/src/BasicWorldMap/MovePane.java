package BasicWorldMap;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import RPGelements.CharacterProfile;
import RPGelements.Human;
import SkillsAndAttributes.Blink;
import SkillsAndAttributes.Skill;
public class MovePane {

    public static void main(String[] args) {
        new MovePane();
    }

    public MovePane() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                
                // TODO: eventually want to load human data from database!!!
                // don't want to create human in memory every time.
                Human h = new Human("Kenny", "August", 30, 1991,  "merchant", "");    
                h.addSkill(new Blink());
                
                frame.add(new TestPane(h)); // pass in the character/unit object!!
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public enum Direction {

        None, Up, Down, Left, Right, Blink;
    }

    @SuppressWarnings("serial")
    // TODO: make this handle arbitrary skills besides blinking!!
	public class TestPane extends JPanel {
    	
    	private final int _SECOND = 1000;
    	private int secondInterval = 0;
		private JPanel mobby;
        private Timer moveTimer; 	// keeps track of player input events
        private Timer ambientTimer; // keeps track of ambient events
        private Direction moveDirection = Direction.None;
        private int SPEED; 
        private final int barHeight = 10; 
        private final int barLength = 400; 
        private JPanel hbar;
        private JPanel hp;
        private JPanel mbar;
        private JPanel mana;
        private ArrayList<Skill> movementSkills; // relevant movement skills 
        public TestPane(final Human h) {
        	
        	// load map objects as 2D graphics 
            JPanel pool = new JPanel(null);
        	loadGraphics(h, pool);
        	addMouseListener(pool,h);
        	
        	// load player skills on map
            movementSkills = new ArrayList<Skill>();
            this.SPEED = h.getMovementSpeed(); // will depend on the stats of the unit that's moving
            
            // get all movement skills loaded in one place
            for(Skill s : h.getSkills()){
            	if(s.isMovementSkill()){
            		this.movementSkills.add(s); 
            	}
            }
            
            /* ******************************************
             *  Load timer for ambient events        
             *  These events occur without user input
             *  And are computed separately for modularity
             ********************************************/
            ambientTimer = new Timer(10, new ActionListener(){
            	 @Override
                 public void actionPerformed(ActionEvent e) {
            		 	secondInterval += 20; 
            		 	if(secondInterval == _SECOND){
            		 		// update health and mana stats
            		 		h.updateMana(h.getManaRegen());
                       		h.updateHp(h.getHpRegen());
                       		secondInterval = 0;
            		 	}
                       	// update health and mana bars
                        Rectangle hpRect = hp.getBounds();
                        Rectangle manaRect = mana.getBounds();   
                        updateHealthAndManaBars(hpRect,manaRect);
                        
                        showRealTimeStats();
                        
            	 }
            	 
            	 public void showRealTimeStats(){
                     double remainingCD = movementSkills.get(0).getRemainingCD();
                     System.out.println("Health: " + h.getCurrentHp() + "   MANA: " + h.getCurrentMana() + "   Cooldown: " + remainingCD);
                   
            	 }
            	 
            	 public void updateHealthAndManaBars(Rectangle hpRect, Rectangle manaRect){
                     hpRect.width = (int)(barLength * ((double)h.getCurrentHp()/h.getMaxHp()));
                     manaRect.width = (int)(barLength * ((double)h.getCurrentMana()/h.getMaxMana()));
                     
                     hp.setBounds(hpRect);
                     mana.setBounds(manaRect);
                     if(h.getCurrentHp() == 0){
                     	hpRect.width = 0;
                         hp.setBounds(hpRect);
                     	mobby.setBackground(Color.BLACK);
                     	System.out.println("YOU DIED :(  T_T  D:");
                     	moveTimer.stop();
                     	ambientTimer.stop();
                     	return; 
                     }
                     if(h.getCurrentMana() == 0){
                     	manaRect.width = 0;
                     	mana.setBounds(manaRect);
                     } 
            	 }
            });
            ambientTimer.start();
            
            /* *******************************************
             *  Load timer player dependent events.       
             *  These events occur based on user input
             *  Examples include movements and skill usage
             *********************************************/
            moveTimer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	
                    Container parent = mobby.getParent(); // TODO: should be retrieving the map data!!
                    Rectangle bounds = mobby.getBounds(); // retrieves the character data!  

                    // can control speed at which the object moves
                    switch (moveDirection) {
                        case Up:
                            bounds.y -= SPEED;
                            h.setOrientation(Direction.Up);
                            break;
                        case Down:
                            bounds.y += SPEED;
                            h.setOrientation(Direction.Down);
                            break;
                        case Left:
                            bounds.x -= SPEED;
                            h.setOrientation(Direction.Left);
                            break;
                        case Right:
                            bounds.x += SPEED;
                            h.setOrientation(Direction.Right);
                            break;
                        case Blink:
                        	 // TODO: no other targets right now!!! only self targeting!!
                        	 movementSkills.get(0).applyTargetedEffect(h, null); 
                        	 bounds.x = h.getCoordinatePosition().l;
                        	 bounds.y = h.getCoordinatePosition().r;
                        default:
                        	break;
                    }
                    if (bounds.x < 0) {
                        bounds.x = 0;
                        mobby.setBackground(randomColor());
                        h.updateHp(-1);
                    } else if (bounds.x + bounds.width > parent.getWidth()) {
                    	mobby.setBackground(randomColor());
                        bounds.x = parent.getWidth() - bounds.width;
                        h.updateHp(-1);
                    }
                    if (bounds.y < 2*barHeight) {
                    	mobby.setBackground(randomColor());
                        bounds.y = 2*barHeight;
                        h.updateHp(-1);
                    } else if (bounds.y + bounds.height > parent.getHeight()) {
                    	mobby.setBackground(randomColor());
                        bounds.y = parent.getHeight() - bounds.height;
                        h.updateHp(-1);
                    }
                    h.setPosition(bounds.x, bounds.y);
                    mobby.setBounds(bounds);
                }
                
                public Color randomColor(){
                	double i = Math.random();
                	if(i < 0.25){
                		return Color.GREEN;
                	}
                	else if(i < 0.5){
                		return Color.BLUE;
                	}
                	else if (i < 0.75){
                		return Color.RED;
                	}
                	else{
                		return Color.ORANGE;
                	}
                }
            });
            moveTimer.start();
            
            InputMap im = pool.getInputMap();
            ActionMap am = pool.getActionMap();

            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "UpPressed");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "UpReleased");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "DownPressed");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "DownReleased");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "LeftPressed");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "LeftReleased");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "RightPressed");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "RightReleased");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, 0, false), "ZPressed");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, 0, true), "ZReleased");
                       
            KeyUpAction keyUpAction = new KeyUpAction();
            
            // TODO: add functionality for arbitrary hot-key assignments!!
            am.put("UpReleased", keyUpAction);
            am.put("DownReleased", keyUpAction);
            am.put("LeftReleased", keyUpAction);
            am.put("RightReleased", keyUpAction);
            am.put("ZReleased", keyUpAction);
            
            am.put("UpPressed", new MoveAction(Direction.Up));
            am.put("DownPressed", new MoveAction(Direction.Down));
            am.put("LeftPressed", new MoveAction(Direction.Left));
            am.put("RightPressed", new MoveAction(Direction.Right));
            am.put("ZPressed", new MoveAction(Direction.Blink));

        }
        public void loadGraphics(Human h, JPanel pool){
        	// health bar data
            hbar = new JPanel();
            hbar.setBackground(Color.RED); 
            hbar.setSize(barLength, barHeight);
            
        	// health bar data
            hp = new JPanel();
            hp.setBackground(Color.GREEN); 
            int hpLength = (int)(barLength * h.getCurrentHp()/h.getMaxHp());
            hp.setSize(hpLength, barHeight);
                       
            // mana bar panel prioritizing is weird!!!
        	// mana bar data
            mbar = new JPanel();
            mbar.setBackground(Color.GRAY); 
            Rectangle mRect = mbar.getBounds(); 
            mRect.y = 10;
            mbar.setBounds(mRect);
            mbar.setSize(barLength, barHeight);
            
        	// mana bar data
            mana = new JPanel();
            mana.setBackground(Color.CYAN); 
            Rectangle mRect2 = mana.getBounds(); 
            mRect2.y = 10;
            mana.setBounds(mRect2);     
            int manaLength = (int)(barLength * h.getCurrentMana()/h.getMaxMana());  
            mana.setSize(manaLength, barHeight);
            
            // the player avatar graphics
            mobby = h.avatar; 
            Rectangle r = mobby.getBounds(); 
            r.y = 20;
            mobby.setBounds(r);
            setLayout(new BorderLayout());
            
            pool.add(mobby);
            pool.add(hp);
            pool.add(hbar);
            pool.add(mana);
            pool.add(mbar);
            add(pool);
        }
        
        public void addMouseListener(JPanel pool, final CharacterProfile profile){

        	pool.addMouseListener(new MouseListener(){
            	private int X; 
            	private int Y;
            	private JPanel avatar;
            	private Rectangle r;
            	private int SPEED = profile.getMovementSpeed();
            	private Timer t; 
				@Override
				public void mouseClicked(MouseEvent e) {

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					//System.out.println("HI");
				}

				@Override
				public void mouseExited(MouseEvent e) {
					//System.out.println("BYE");
				}

				@Override
				public void mousePressed(MouseEvent e) {
					avatar = profile.avatar;
					r = avatar.getBounds(); 
					X = e.getX() - r.width/2; 
					Y = e.getY() - r.height/2;
					t = new Timer(10, new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent e) {
								if((r.x == X && r.y == Y) || profile.getCurrentHp() == 0){
									//System.out.println("STOPPED");
									t.stop();
									return;
									//moveTimer.start();
								}
								int xDist = Math.abs(r.x - X);
								int yDist = Math.abs(r.y - Y);
								if(r.x > X){
									r.x -= Math.min(xDist,SPEED);
								}
								else if(r.x < X){
									r.x += Math.min(xDist,SPEED);
								}
								
								if(r.y > Y){
									r.y -= Math.min(yDist,SPEED);
								}
								else if(r.y < Y){
									r.y += Math.min(yDist,SPEED);
								}
								avatar.setBounds(r);
							}
							
					});	
					t.start();
					//moveTimer.stop();
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					
				}
            	
            });
        }
        // TODO: map should not be resize-able!!! 
        // TODO: map should also be in map-data class!!
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(400, 400);
        }

        public class KeyUpAction extends AbstractAction {

            @Override
            public void actionPerformed(ActionEvent e) {
                moveDirection = Direction.None;
            }
        }

        
        public class MoveAction extends AbstractAction {

            private Direction direction;
            
            public MoveAction(Direction direction) {
                this.direction = direction;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                moveDirection = direction;
            }
        }
    }
}