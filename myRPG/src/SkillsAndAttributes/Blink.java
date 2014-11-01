package SkillsAndAttributes;

import java.awt.Rectangle;

import BasicWorldMap.MovePane.Direction;

public class Blink extends Skill {
	public double lastUsedSkillTime; 
	public Blink() {
		//this.MOVEMENTSKILL = true;
		this.lastUsedSkillTime = -1;
		this.amountOfEffectTime = 0;
		this.effectRadius = 0;
		this.maxTargets = 2;
		this.skillPoints = 0;
		this.coolDown = 3.0;
		this.castRange = 200;
		this.manaCost = 15;
		this.name = "Blink";
		this.level1Cap = 0; 
		this.level2Cap = 1;
		this.level3Cap = 2;
		this.level4Cap = 3;
		this.level5Cap = 4;
	}
	public void blinkMove(Rectangle bounds, Direction currentDirection){
		 int BLINK = this.castRange;
    	 double now = System.currentTimeMillis();
    	 if(lastUsedSkillTime > 0 && (now - lastUsedSkillTime)/1000 < this.coolDown){
    		 System.out.println("SKILL IS ON COOLDOWN!!");
    		 return;
    	 }
    	 else{
    		 lastUsedSkillTime = now;
    		 System.out.println("BLINKED!!");
    	 }
    	 switch (currentDirection) {
         	case Up:
             	bounds.y -= BLINK;
             	break;
         	case Down:
        	 	bounds.y += BLINK;
             	break;
         	case Left:
             	bounds.x -= BLINK;
             	break;
         	case Right:
             	bounds.x += BLINK;
             	break;
         	case Blink:
        	 
         	default:
         		break;
    	 }
    }
}
