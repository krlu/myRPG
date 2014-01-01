package SkillsAndAttributes;

import RPGelements.CharacterProfile;
import RPGelements.Dwarf;

public class EmpoweredStrike extends Skill {

/********************************************************************************
 * Basic damage ability with melee range,every 4th attack deals this skill procs 
 * and deals additional physical damage. Lowers to 3 attacks b/w procs at lvl 4
 * Weak at mid-levels due to poor scaling on damage and requires attack speed
 * Strong at high levels due to increased damage/ratios and decreased counts 
 * b/w procs and high scaling with purchase of attack speed items
 *******************************************************************************/
	
	public EmpoweredStrike() {
		this.skillPoints = 0;
		this.coolDown = 0;
		this.castRange = 0;
		this.manaCost = 0;
		this.name = "EmpoweredStrike";
		this.level1Cap = 0; 
		this.level2Cap = 1;
		this.level3Cap = 2;
		this.level4Cap = 3;
		this.level5Cap = 4;
	}
	@Override
	public int level1Effect(CharacterProfile profile){
		if(profile.getAttackCounter() % 4 == 0){
			return 12 + (int)(0.55 * profile.getAttackDamage());
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
			return 30 + (int)(0.65 * profile.getAttackDamage());
		}
		return 0;
	}
	@Override
	/*requires level 20*/
	public int level4Effect(CharacterProfile profile){
		if(profile.getAttackCounter() % 3 == 0){
			return 50 + (int)(0.75 * profile.getAttackDamage());
		}
		return 0;
	}
	@Override
	/*requires level 25*/
	public int level5Effect(CharacterProfile profile){
		if(profile.getAttackCounter() % 3 == 0){
			return 100 + (int)(0.85 * profile.getAttackDamage());
		}
		return 0;
	}

	public static void main(String[] args){
		CharacterProfile me = new Dwarf("Kenny", "August", 30, 1991, "merchant", "");	
		me.updateAttack(200);
		Skill empoweredStrike = new EmpoweredStrike();
		empoweredStrike.updateSkillPoints(4);
		System.out.println(empoweredStrike.applyEffect(me));
	}
}
