package SkillsAndAttributes;

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
		return blinkMove(profile);
	}	
	
	public int blinkMove(CharacterProfile profile){
		 
		 int BLINK = this.castRange;
		 
		 int x = profile.getCoordinatePosition().l; 
		 int y = profile.getCoordinatePosition().r;
		 
    	 double now = System.currentTimeMillis();
    	 if(lastUsedSkillTime > 0 && (now - lastUsedSkillTime)/1000 < this.coolDown){
    		// System.out.println("SKILL IS ON COOLDOWN!!");
    		 return 0;
    	 }
    	 else if(profile.getCurrentMana() < this.manaCost){
    	 //	 System.out.println("Not enough mana!!");
    		 return 0;
    	 }
    	 else if(profile.getDirectionVector().l == 0 && profile.getDirectionVector().r == 0){
    		 System.out.println("No direction!");
    		 return 0;
    	 }
    	 else{
    		 lastUsedSkillTime = now;
    	//	 System.out.println("BLINKED!!");
    		 profile.updateMana(- this.manaCost);
    	 }
    	 x += BLINK * profile.getDirectionVector().l;
    	 y += BLINK * profile.getDirectionVector().r;
    	 
    	 profile.setPosition(x, y);
    	 return 1;
    }
}
