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

import FundamentalStructures.Tuple;
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
                Human profile = new Human("Kenny", "August", 30, 1991,  "merchant", "");    
                profile.addSkill(new Blink());
                
                frame.add(new SimpleMap(profile)); // pass in the character/unit object!!
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
	public class SimpleMap extends JPanel {
    	
    	private final int _SECOND = 1000;
    	private int secondInterval = 0;
		private JPanel mobby;
        private Timer ambientTimer; // keeps track of ambient events
        private Direction moveDirection = Direction.None;
        private final int barHeight = 10; 
        private final int barLength = 400; 
        private JPanel hbar;
        private JPanel hp;
        private JPanel mbar;
        private JPanel mana;
        private ArrayList<Skill> movementSkills; // relevant movement skills 
        private ArrayList<JPanel> mapObjects; // objects on the map

        public SimpleMap(final CharacterProfile profile) {
        	
        	// load mapData
        	
        	// load map objects as 2D graphics 
            JPanel pool = new JPanel(null);
        	loadGraphics(profile, pool);       	
        	
        	// load player skills on map
            movementSkills = new ArrayList<Skill>();
            
            // get all movement skills loaded in one place
            for(Skill s : profile.getSkills()){
            	if(s.isMovementSkill()){
            		this.movementSkills.add(s); 
            	}
            }
            
            /* ******************************************
             *  Load timer for ambient events        
             *  These events occur without user input
             *  Includes hp/mana regen, buffs/debuffs...etc
             ********************************************/
            ambientTimer = new Timer(10, new MyActionListener(profile){
            	 @Override
                 public void actionPerformed(ActionEvent e) {
            		 	secondInterval += 20; 
            		 	if(secondInterval == _SECOND){
            		 		// update health and mana stats
            		 		profile.updateMana(profile.getManaRegen());
                       		profile.updateHp(profile.getHpRegen());
                       		secondInterval = 0;
            		 	}
                       	// update health and mana bars
                        Rectangle hpRect = hp.getBounds();
                        Rectangle manaRect = mana.getBounds();   
                        updateHealthAndManaBars(hpRect,manaRect);
                        
                        showRealTimeStats();
                        
            	 }
            	 
            	 public void showRealTimeStats(){
                   //  double remainingCD = movementSkills.get(0).getRemainingCD();
                   //  System.out.println("Health: " + profile.getCurrentHp() + "   MANA: " + profile.getCurrentMana() + "   Cooldown: " + remainingCD);
                   
            	 }
            	 
            	 public void updateHealthAndManaBars(Rectangle hpRect, Rectangle manaRect){
                     hpRect.width = (int)(barLength * ((double)profile.getCurrentHp()/profile.getMaxHp()));
                     manaRect.width = (int)(barLength * ((double)profile.getCurrentMana()/profile.getMaxMana()));
                     
                     hp.setBounds(hpRect);
                     mana.setBounds(manaRect);
                     if(profile.getCurrentHp() == 0){
                     	hpRect.width = 0;
                        hp.setBounds(hpRect);
                     	mobby.setBackground(Color.BLACK);
                     	System.out.println("YOU DIED :(  T_T  D:");
                     	ambientTimer.stop();
                     	return; 
                     }
                     if(profile.getCurrentMana() == 0){
                     	manaRect.width = 0;
                     	mana.setBounds(manaRect);
                     } 
            	 }
            });
            ambientTimer.start();
            
            /* **************************************************
             *  Load timer for player dependent events.       
             *  These events occur based on user input such as: 
             *  targeted/non-targeted skills, mouse click, etc...
             ****************************************************/          
            addMouseListener(pool,profile, mapObjects);
            
            InputMap im = pool.getInputMap();
            ActionMap am = pool.getActionMap();          
            KeyUpAction keyUpAction = new KeyUpAction();       
            bindKeys(im,am, keyUpAction);
        }
        
        
       public void bindKeys(InputMap im, ActionMap am, KeyUpAction keyUpAction){
    	   
           im.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, 0, false), "ZPressed");
           im.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, 0, true), "ZReleased");
           
           am.put("ZPressed", new MoveAction(Direction.Blink));
           am.put("ZReleased", keyUpAction);
       }
        
        public void loadGraphics(CharacterProfile profile, JPanel pool){
        	// health bar data
            hbar = new JPanel();
            hbar.setBackground(Color.RED); 
            hbar.setSize(barLength, barHeight);
            
        	// health bar data
            hp = new JPanel();
            hp.setBackground(Color.GREEN); 
            int hpLength = (int)(barLength * profile.getCurrentHp()/profile.getMaxHp());
            hp.setSize(hpLength, barHeight);
                       
            // mana bar panel prioritizing is weird!!!
        	// mana bar data
            mbar = new JPanel();
            mbar.setBackground(Color.GRAY); 
            Rectangle mRect = mbar.getBounds(); 
            mRect.y = barHeight;
            mbar.setBounds(mRect);
            mbar.setSize(barLength, barHeight);
            
        	// mana bar data
            mana = new JPanel();
            mana.setBackground(Color.CYAN); 
            Rectangle mRect2 = mana.getBounds(); 
            mRect2.y = barHeight;
            mana.setBounds(mRect2);     
            int manaLength = (int)(barLength * profile.getCurrentMana()/profile.getMaxMana());  
            mana.setSize(manaLength, barHeight);
            
            // player avatar graphics
            mobby = profile.avatar; 
            Rectangle r = mobby.getBounds(); 
            r.y = 2*barHeight;
            mobby.setBounds(r);
            setLayout(new BorderLayout());
            
            mapObjects = new ArrayList<JPanel>();
            
            JPanel wall = new JPanel(); 
            wall.setBackground(Color.DARK_GRAY);
            Rectangle wallRect = wall.getBounds();
            wallRect.setSize(20, 200);
            wallRect.y = 2*barHeight + mobby.getBounds().height + 10;
            wall.setBounds(wallRect);
           // this.mapObjects.add(wall);
            
          //  pool.add(wall);
            pool.add(mobby);
            pool.add(hp);
            pool.add(hbar);
            pool.add(mana);
            pool.add(mbar);            
            add(pool);
        }
        
        public void addMouseListener(JPanel pool, final CharacterProfile profile, final ArrayList<JPanel> mapObjects){
        	pool.addMouseListener(new MyMouseListener(profile, mapObjects){
            	private int X = 0; 
            	private int Y = 2*barHeight;
            	private JPanel avatar;
            	private Rectangle r;
            	private int SPEED = profile.getMovementSpeed();
            	private int usedBlink = 0;
            	private Tuple<Double,Double> vector;
            	
            	// TODO: find less hacky solution to creating timers!!
            	// So far all skills used are in here!!
            	private Timer mouseTimer = new Timer(10, new MyActionListener(profile){
					@Override
					public void actionPerformed(ActionEvent e) {
						Container parent = profile.avatar.getParent();
	
						if(profile.getCurrentHp() == 0){
							mouseTimer.stop();
						}				
						int xDist = Math.abs(X - r.x);
						int yDist = Math.abs(Y - r.y);
						
						// for when vector components are < 1
						if(xDist == 1){
							r.x = X;							
						}
						else if(r.x > X){
							r.x += Math.min(xDist,SPEED)*vector.l;
						}
						else if(r.x < X){
							r.x += Math.min(xDist,SPEED)*vector.l;
						}

						
						if(yDist == 1){
							r.y = Y;
						}
						else if(r.y > Y){
							r.y += Math.min(yDist,SPEED)*vector.r;
						}
						else if(r.y < Y){
							r.y += Math.min(yDist,SPEED)*vector.r;
						}
						switch (moveDirection) {
	                        case Blink:	                        	 
	                        	 // TODO: no other targets right now!!! only self targeting!!
	                        	 usedBlink = movementSkills.get(0).applyTargetedEffect(profile, null); 
	                        	 if(usedBlink > 0){
	                        		 X = profile.getCoordinatePosition().l;
	                        		 Y = profile.getCoordinatePosition().r;
		                        	 r.x = profile.getCoordinatePosition().l;
		                        	 r.y = profile.getCoordinatePosition().r; 							 	
	                        	 }
	                        default:
	                        	break;
	                    }
						if (r.x < 0) {
	                        r.x = 0;
	                        X = r.x;
	                        mobby.setBackground(randomColor());
	                        profile.updateHp(-10);
	                    } else if (r.x + r.width > parent.getWidth()) {
	                    	mobby.setBackground(randomColor());
	                        r.x = parent.getWidth() - r.width;
	                        X = r.x;
	                        profile.updateHp(-10);
	                    }
	                    if (r.y < 2*barHeight) {
	                    	mobby.setBackground(randomColor());
	                        r.y = 2*barHeight;
	                        profile.updateHp(-10);
	                        Y = r.y;
	                    } else if (r.y + r.height > parent.getHeight()) {
	                    	mobby.setBackground(randomColor());
	                        r.y = parent.getHeight() - r.height;
	                        profile.updateHp(-10);
	                        Y = r.y;
	                    }
	                    
	                    checkIntersection(r,mobby, mapObjects);
	                    profile.setPosition(r.x, r.y);
	                    mobby.setBounds(r);

						avatar.setBounds(r);
					}
					public void checkIntersection(Rectangle r, JPanel mobby, ArrayList<JPanel> mapObjects){		
						for(JPanel mapObject : mapObjects){
							Rectangle mapRect = mapObject.getBounds(); 
							if(IntersectionCalculator.computeIntersectionCases(r, mapRect)){
								mobby.setBackground(randomColor());							
								profile.updateHp(-10);
								X = r.x; 
								Y = r.y;	
							}
						}
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
				@Override
				public void mouseClicked(MouseEvent e) {
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					avatar = profile.avatar;					
					r = avatar.getBounds(); 
					mouseTimer.start();
				}

				@Override
				public void mouseExited(MouseEvent e) {
					mouseTimer.stop();
				}

				@Override
				public void mousePressed(MouseEvent e) {					
					if(profile.getCurrentHp() == 0){
						return;
					} 
					X = e.getX() - r.width/2; 
					Y = e.getY() - r.height/2;
					this.vector = vector2D(X-r.x,Y-r.y);					
					profile.setDirection(vector.l, vector.r);		
				}

				@Override
				public void mouseReleased(MouseEvent e) {		
				}
				
				public Tuple<Double, Double> vector2D(int xDiff, int yDiff){
					
					double magnitude = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff,2));					
					if(magnitude == 0){
						return new Tuple<Double,Double>(0.0,0.0);
					}
					Tuple<Double, Double> vector2D = new Tuple<Double, Double>(xDiff/magnitude,yDiff/magnitude);
					return vector2D;
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
        private class MyActionListener implements ActionListener {
            public MyActionListener(CharacterProfile profile) {
            }
            public void actionPerformed(ActionEvent e) {
                
            }
        }
        private class MyMouseListener implements MouseListener{
        	public MyMouseListener(CharacterProfile profile, ArrayList<JPanel> mapObjects){
        	}
        	
			@Override
			public void mouseClicked(MouseEvent arg0) {				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {			
			}

			@Override
			public void mouseExited(MouseEvent arg0) {				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
        	
        }
    }
}