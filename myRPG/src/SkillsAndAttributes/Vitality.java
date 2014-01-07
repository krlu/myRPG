package SkillsAndAttributes;

import RPGelements.CharacterProfile;

/********************************************
 * Basic healing skill. High base cooldowns 
 * and mana costs but high base stats as well
 ********************************************/
public class Vitality extends Skill {

	private double healRatio;
	public Vitality() {
		this.effectRadius = 0;
		this.maxTargets = 1;
		this.healRatio = 0.3;
		this.skillPoints = 0;
		this.coolDown = 20;
		this.castRange = 2;
		this.manaCost = 25;
		this.name = "Vitality";
		this.level1Cap = 0; 
		this.level2Cap = 1;
		this.level3Cap = 2;
		this.level4Cap = 3;
		this.level5Cap = 4;
	}
	@Override
	public int level1Effect(CharacterProfile profile, CharacterProfile target){
		return effectHelper(25, profile, target);
	}
	@Override
	public int level2Effect(CharacterProfile profile, CharacterProfile target){
		return effectHelper(50, profile , target);
	}
	
	/*requires level 15*/
	@Override
	public int level3Effect(CharacterProfile profile, CharacterProfile target){
		return effectHelper(80, profile, target);
	}
	/*requires level 19*/
	@Override
	public int level4Effect(CharacterProfile profile ,CharacterProfile target){
		return effectHelper(125, profile, target);
	}
	/*requires level 23*/
	@Override
	public int level5Effect(CharacterProfile profile, CharacterProfile target){
		return effectHelper(170, profile, target);
	}
	private int effectHelper(int baseHeal, CharacterProfile profile , CharacterProfile target){
		int totalHeal = baseHeal + (int)(this.healRatio * profile.getBonusMagic());
		target.updateHp(totalHeal);
		return totalHeal;
	}	
	
	@Override
	public boolean requirementsBeforeSkillPoints(CharacterProfile profile){
		if(this.skillPoints < this.level2Cap){
			return true;
		}
		else if(this.skillPoints == this.level2Cap){
			return profile.getLevel() >= 15;
		}
		else if(this.skillPoints == this.level3Cap){
			return profile.getLevel() >= 19;
		}
		else if(this.skillPoints == this.level4Cap){
			return profile.getLevel() >= 25;
		}
		else{
			return false;
		}
	}
	/*additional effects upon leveling up*/
	@Override
	public void attainingLevel2(){
		this.manaCost = 30;
	}
	@Override
	public void attainingLevel3(){
		this.coolDown = 18;
		this.manaCost = 40;
	}
	@Override
	public void attainingLevel4(){
		this.manaCost = 50;
	}
	@Override
	public void attainingLevel5(){
		this.manaCost = 60;
		this.coolDown = 16;
	}
}
