package SkillsAndAttributes;

import RPGelements.CharacterProfile;

/********************************************
 * Basic healing skill. High base cooldowns 
 * and mana costs but high base stats as well
 ********************************************/
public class Vitality extends Skill {

	private double healRatio;
	public Vitality() {
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
	public int level1Effect(CharacterProfile profile){
		return effectHelper(25, profile);
	}
	@Override
	public int level2Effect(CharacterProfile profile){
		return effectHelper(50, profile);
	}
	
	/*requires level 15*/
	@Override
	public int level3Effect(CharacterProfile profile){
		return effectHelper(80, profile);
	}
	/*requires level 19*/
	@Override
	public int level4Effect(CharacterProfile profile){
		return effectHelper(125, profile);
	}
	/*requires level 23*/
	@Override
	public int level5Effect(CharacterProfile profile){
		return effectHelper(170, profile);
	}
	private int effectHelper(int baseHeal, CharacterProfile profile){
		int totalHeal = baseHeal + (int)(this.healRatio * profile.getBonusMagic());
		profile.updateHp(totalHeal);
		return totalHeal;
	}
	/*additional effects upon leveling up*/
	@Override
	public void attainingLevel2(){
		this.manaCost = 30;
	}
	@Override
	public void attainingLevel3(){
		this.coolDown = 18;
		this.manaCost = 35;
	}
	@Override
	public void attainingLevel4(){
		this.manaCost = 40;
	}
	@Override
	public void attainingLevel5(){
		this.manaCost = 50;
		this.coolDown = 16;
	}
}
