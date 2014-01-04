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
		this.manaCost = 15;
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
		return 10 + (int)(0.5 * profile.getBonusMagic());
	}
	@Override
	public int level2Effect(CharacterProfile profile){
		return 20 + (int)(0.5 * profile.getBonusMagic());
	}
	@Override
	/*requires level 15*/
	public int level3Effect(CharacterProfile profile){
		return 35 + (int)(0.5 * profile.getBonusMagic());
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
	@Override
	public void attainingLevel2(){
		this.manaCost = 20;
	}
	@Override
	public void attainingLevel3(){
		this.coolDown = 9;
		this.manaCost = 30;
	}
	@Override
	public void attainingLevel4(){
		this.coolDown = 8;
	}
	@Override
	public void attainingLevel5(){
		this.coolDown = 7;
	}
	
	public static void main(String [] args){
		CharacterProfile me = new Dwarf("Kenny", "August", 30, 1991, "merchant", "");	
		me.updateBonusMagic(10);
		me.updateAttack(6);
		Skill arcaneFire = new ArcaneFire();
		Skill vitality = new Vitality();
		Skill empoweredStrike = new EmpoweredStrike();
		
		System.out.println(arcaneFire.applyEffect(me,null) + " , " + empoweredStrike.applyEffect(me,null) + " , " + vitality.applyEffect(me,null));
		
		me.updateBonusMagic(50);
		me.updateAttack(30);
		arcaneFire.updateSkillPoints(1);
		vitality.updateSkillPoints(1);
		empoweredStrike.updateSkillPoints(1);
		System.out.println(arcaneFire.applyEffect(me,null) + " , " + empoweredStrike.applyEffect(me,null) + " , " + vitality.applyEffect(me,null));
		
		me.updateBonusMagic(100);
		me.updateAttack(54);
		arcaneFire.updateSkillPoints(1);
		vitality.updateSkillPoints(1);
		empoweredStrike.updateSkillPoints(1);
		System.out.println(arcaneFire.applyEffect(me, null) + " , " + empoweredStrike.applyEffect(me,null) + " , " + vitality.applyEffect(me, null));
		
		me.updateBonusMagic(140);
		me.updateAttack(60);
		arcaneFire.updateSkillPoints(1);
		vitality.updateSkillPoints(1);
		empoweredStrike.updateSkillPoints(1);
		System.out.println(arcaneFire.applyEffect(me, null) + " , " + empoweredStrike.applyEffect(me, null) + " , " + vitality.applyEffect(me, null));
		
		me.updateBonusMagic(160);
		me.updateAttack(80);
		arcaneFire.updateSkillPoints(1);
		vitality.updateSkillPoints(1);
		empoweredStrike.updateSkillPoints(1);
		System.out.println(arcaneFire.applyEffect(me, null) + " , " + empoweredStrike.applyEffect(me, null) + " , " + vitality.applyEffect(me, null));
	}
}
