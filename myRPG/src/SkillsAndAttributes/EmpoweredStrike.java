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
	private int procNumber; 
	private double damageRatio; 
	public EmpoweredStrike() {
		this.procNumber = 4;
		this.damageRatio = 0.55;
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
		return effectHelper(12, profile);
	}
	@Override
	public int level2Effect(CharacterProfile profile){
		return effectHelper(20, profile);
	}
	@Override
	public int level3Effect(CharacterProfile profile){
		return effectHelper(30, profile);
	}
	@Override
	/*requires level 20*/
	public int level4Effect(CharacterProfile profile){
		return effectHelper(50, profile);
	}
	@Override
	/*requires level 25*/
	public int level5Effect(CharacterProfile profile){
		return effectHelper(100, profile);
	}
	public int effectHelper(int baseDamage, CharacterProfile profile){
		if(profile.getAttackCounter() % this.procNumber == 0){
			int damage = baseDamage + (int)(this.damageRatio * profile.getAttackDamage());
			profile.updateTotalPhysicalDamage(damage);
			return damage;
		}
		return 0;
	}
	
	
	/* *****************************************
	 * Overridden methods
	 * TODO: not sure what to do with these yet
	 *******************************************/
	@Override
	public void attainingLevel2(){
		this.damageRatio = 0.6;
	}
	@Override
	public void attainingLevel3(){
		this.damageRatio = 0.7;
	}
	@Override
	public void attainingLevel4(){
		this.damageRatio = 0.8;
		this.procNumber = 3;
	}
	@Override
	public void attainingLevel5(){
		this.damageRatio = 0.9;
	}
	
	
	public static void main(String[] args){
		CharacterProfile me = new Dwarf("Kenny", "August", 30, 1991, "merchant", "");	
		me.updateAttack(36);
		EmpoweredStrike empoweredStrike = new EmpoweredStrike();
		empoweredStrike.updateSkillPoints(1);
		System.out.println(empoweredStrike.applyEffect(me, null));
	}
}
