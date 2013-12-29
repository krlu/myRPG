package SkillsAndAttributes;

import RPGelements.CharacterProfile;
import RPGelements.Dwarf;

public class EmpoweredStrike extends Skill {

/********************************************************************************
 * Basic damage ability with melee range,every 4th attack deals this skill procs 
 * and deals additional physical damage.strong at high levels due to increased 
 * damage/ratios and decreased attack counts b/w procs scales with attack speed.
 *******************************************************************************/
	
	public EmpoweredStrike() {
		this.skillPoints = 0;
		this.coolDown = 0;
		this.castRange = 0;
		this.manaCost = 0;
		this.name = "Empowered Strike";
		this.level1Cap = 0; 
		this.level2Cap = 1;
		this.level3Cap = 2;
		this.level4Cap = 3;
		this.level5Cap = 4;
	}
	@Override
	public int level1Effect(CharacterProfile profile){
		if(profile.getAttackCounter() % 4 == 0){
			return 15 + (int)(0.5 * profile.getAttackDamage());
		}
		return 0;
	}
	@Override
	public int level2Effect(CharacterProfile profile){
		if(profile.getAttackCounter() % 4 == 0){
			return 20 + (int)(0.6 * profile.getAttackDamage());
		}
		return 0;
	}
	@Override
	public int level3Effect(CharacterProfile profile){
		if(profile.getAttackCounter() % 4 == 0){
			return 30 + (int)(0.7 * profile.getAttackDamage());
		}
		return 0;
	}
	@Override
	/*requires level 10*/
	public int level4Effect(CharacterProfile profile){
		if(profile.getAttackCounter() % 3 == 0){
			return 55 + (int)(0.8 * profile.getAttackDamage());
		}
		return 0;
	}
	@Override
	/*requires level 12*/
	public int level5Effect(CharacterProfile profile){
		if(profile.getAttackCounter() % 3 == 0){
			return 100 + (int)(0.9 * profile.getAttackDamage());
		}
		return 0;
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
	public static void main(String[] args){
		CharacterProfile me = new Dwarf("Kenny", "August", 30, 1991, "merchant", "");	
		me.updateAttack(200);
		Skill empoweredStrike = new EmpoweredStrike();
		empoweredStrike.updateSkillPoints(4);
		System.out.println(empoweredStrike.applyEffect(me));
	}
}
