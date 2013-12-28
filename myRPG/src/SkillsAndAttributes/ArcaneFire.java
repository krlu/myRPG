package SkillsAndAttributes;
import  RPGelements.CharacterProfile;
public class ArcaneFire extends Skill {

	public ArcaneFire() {
		this.skillPoints = 0;
		this.coolDown = 10.0;
		this.castRange = 4;
		this.manaCost = 8;
		this.name = "Arcane Fire";
	}
	/* *************************************************
	 * 1 skill point to level, 
	 * higher level skills requires higher level profile
	 ***************************************************/
	public int level1Effect(CharacterProfile profile){
		return 10 + (int)(0.5 * profile.getBonusMagic());
	}	
	public int level2Effect(CharacterProfile profile){
		return 20 + (int)(0.5 * profile.getBonusMagic());
	}
	/*requires level 8*/
	public int level3Effect(CharacterProfile profile){
		return 40 + (int)(0.5 * profile.getBonusMagic());
	}
	/*requires level 10*/
	public int level4Effect(CharacterProfile profile){
		return 70 + (int)(0.5 * profile.getBonusMagic());
	}
	/*requires level 12*/
	public int level5Effect(CharacterProfile profile){
		return 120 + (int)(0.5 * profile.getBonusMagic());
	}
	
	/*additional effects upon leveling up*/
	public void attainingLevel2(){
		this.manaCost = 12;
	}
	public void attainingLevel3(){
		this.coolDown = 9;
		this.manaCost = 20;
	}
	public void attainingLevel4(){
		this.coolDown = 8;
	}
	public void attainingLevel5(){
		this.coolDown = 7;
	}
}
