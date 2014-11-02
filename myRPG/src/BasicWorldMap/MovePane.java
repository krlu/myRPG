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
		private JPanel mobby;
        private Timer moveTimer; 	// keeps track of player input events
        private Timer ambientTimer; // keeps track of ambient events
        private Direction moveDirection = Direction.None;
        private int SPEED; 
        
        private ArrayList<Skill> movementSkills; // relevant movement skills 
        
        public TestPane(final Human h) {
        	/* creates the image on the map
        	 * can specify size and color through character profile
        	 */
            mobby = h.avatar;
            setLayout(new BorderLayout());
            JPanel pool = new JPanel(null);
            pool.add(mobby);
            add(pool);
            
            movementSkills = new ArrayList<Skill>();
            this.SPEED = h.getMovementSpeed(); // will depend on the stats of the unit that's moving
            
            // get all movement skills loaded in one place
            for(Skill s : h.getSkills()){
            	if(s.isMovementSkill()){
            		this.movementSkills.add(s); 
            	}
            }
            
            ambientTimer = new Timer(_SECOND, new ActionListener(){
            	 @Override
                 public void actionPerformed(ActionEvent e) {
                       	h.updateMana(h.getManaRegen());
                       	h.updateHp(h.getHpRegen());
            	 }
            });
            ambientTimer.start();
            
            moveTimer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Container parent = mobby.getParent(); // TODO: should be retrieving the map data!!
                    Rectangle bounds = mobby.getBounds(); // retrieves the character data!   
                    
                    if(h.getCurrentHp() == 0){
                    	mobby.setBackground(Color.BLACK);
                    	System.out.println("YOU DIED :(  T_T  D:");
                    	moveTimer.stop();
                    	ambientTimer.stop();
                    	return; 
                    }
                    double lastUsedSkillTime = movementSkills.get(0).getLastUsedTime();
                    double diff = (System.currentTimeMillis() - lastUsedSkillTime)/1000;                    
                    double remainingCD = Math.max(0,movementSkills.get(0).getCoolDown() - diff);
                    
                    System.out.println("Health: " + h.getCurrentHp() + "   MANA: " + h.getCurrentMana() + "   Cooldown: " + remainingCD);
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
                    if (bounds.y < 0) {
                    	mobby.setBackground(randomColor());
                        bounds.y = 0;
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
            
            // TODO: add functionality for hot-key assignments!!
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

        // TODO: map should not be resize-able!!! 
        // TODO: map should also be in map-data class!!
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(400, 400);
        }

        public class KeyUpAction extends AbstractAction {

            @Override
            public void actionPerformed(ActionEvent e) {
               // moveTimer.stop();
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
               // moveTimer.start();
            }
        }
    }
}