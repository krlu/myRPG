package SkillsAndAttributes;
import  RPGelements.CharacterProfile;
import RPGelements.Dwarf;

/******************************************
 * Basic damage ability with medium range  
 * scales with bonus magic and skill points
 * strong at high levels due to decreased 
 * cool-downs and since mana costs remain 
 * static even as damage amplifies. 
 ******************************************/

public class ArcaneFire extends Skill {

	public ArcaneFire() {
		this.skillPoints = 0;
		this.coolDown = 10.0;
		this.castRange = 4;
		this.manaCost = 8;
		this.name = "ArcaneFire";
		this.level1Cap = 0; 
		this.level2Cap = 1;
		this.level3Cap = 2;
		this.level4Cap = 3;
		this.level5Cap = 4;
	}
	/* *************************************************
	 * 1 skill point to level, 
	 * higher level skills requires higher level profile
	 ***************************************************/
	@Override
	public int level1Effect(CharacterProfile profile){
		return 12 + (int)(0.5 * profile.getBonusMagic());
	}
	@Override
	public int level2Effect(CharacterProfile profile){
		return 20 + (int)(0.5 * profile.getBonusMagic());
	}
	@Override
	/*requires level 15*/
	public int level3Effect(CharacterProfile profile){
		return 40 + (int)(0.5 * profile.getBonusMagic());
	}
	@Override
	/*requires level 20*/
	public int level4Effect(CharacterProfile profile){
		return 70 + (int)(0.5 * profile.getBonusMagic());
	}
	@Override
	/*requires level 25*/
	public int level5Effect(CharacterProfile profile){
		return 120 + (int)(0.5 * profile.getBonusMagic());
	}
	
	/*additional effects upon leveling up*/
	public void attainingLevel2(){
		this.manaCost = 15;
	}
	public void attainingLevel3(){
		this.coolDown = 9;
		this.manaCost = 30;
	}
	public void attainingLevel4(){
		this.coolDown = 8;
	}
	public void attainingLevel5(){
		this.coolDown = 7;
	}
	
	public static void main(String [] args){
		CharacterProfile me = new Dwarf("Kenny", "August", 30, 1991, "merchant", "");	
		me.updateBonusMagic(300);
		Skill arcaneFire = new ArcaneFire();
		arcaneFire.updateSkillPoints(3);
		System.out.println(arcaneFire.applyEffect(me));
	}
}
