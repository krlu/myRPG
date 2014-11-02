package SkillsAndAttributes;

import BasicWorldMap.MovePane.Direction;
import RPGelements.CharacterProfile;

// TODO: make blinking call-able from skill class!!
public class Blink extends Skill { 
	public Blink() {
		this.MOVEMENTSKILL = true;
		this.lastUsedSkillTime = -1;
		this.amountOfEffectTime = 0;
		this.effectRadius = 0;
		this.maxTargets = -1; // if max targets is < 0 then only target is self!
		this.skillPoints = 0;
		this.coolDown = 3.0;
		this.castRange = 200;
		this.manaCost = 13;
		this.name = "Blink";
		this.level1Cap = 0; 
		this.level2Cap = 1;
		this.level3Cap = 2;
		this.level4Cap = 3;
		this.level5Cap = 4;
	}
	
	public int level1Effect(CharacterProfile profile, CharacterProfile target){
		blinkMove(profile, profile.getOrientation());
		return 0;
	}	
	
	public void blinkMove(CharacterProfile profile, Direction currentDirection){
		 
		 int BLINK = this.castRange;
		 
		 int x = profile.getCoordinatePosition().l; 
		 int y = profile.getCoordinatePosition().r;
		 
    	 double now = System.currentTimeMillis();
    	 if(lastUsedSkillTime > 0 && (now - lastUsedSkillTime)/1000 < this.coolDown){
    		// System.out.println("SKILL IS ON COOLDOWN!!");
    		 return;
    	 }
    	 else if(profile.getCurrentMana() < this.manaCost){
    		 System.out.println("Not enough mana!!");
    		 return;
    	 }
    	 else{
    		 lastUsedSkillTime = now;
    	//	 System.out.println("BLINKED!!");
    		 profile.updateMana(- this.manaCost);
    	 }
    	 switch (currentDirection) {
         	case Up:
             	y -= BLINK;
             	break;
         	case Down:
        	 	y += BLINK;
             	break;
         	case Left:
             	x -= BLINK;
             	break;
         	case Right:
             	x += BLINK;
             	break;       	 
         	default:
         		break;
    	 }
    	 profile.setPosition(x, y);
    }
}
