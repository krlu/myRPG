package SkillsAndAttributes;

import RPGelements.CharacterProfile;
import RPGelements.DamageCalculator;

/***********************************************************
 * Execution skill that deals damage based on missing health
 * making it effective at health stacking units as well as 
 * low health targets with high magic resist. Long range so
 * that user can cast from safe distance. 
 * Skill has high cooldown, use wisely!!
 ***********************************************************/
public class GaiaIgnius extends Skill {
	
	private final double percentRatio = 0.0005;
	
	public GaiaIgnius() {
		this.maxTargets = 3;
		this.castRange = 6;
		this.effectRadius = 1;
		this.skillPoints = 0;
		this.coolDown = 120.0;
		this.castRange = 4;
		this.manaCost = 110;
		this.name = "ArcaneFire";
		this.level1Cap = 1; 
		this.level2Cap = 2;
		this.level3Cap = 4;
		this.level4Cap = -1;
		this.level5Cap = -1;
	}
	/*requires level 15*/
	@Override
	public int level1Effect(CharacterProfile profile,CharacterProfile target){
		return effectHelper(100, profile,target);
	}
	/*requires level 20*/
	@Override
	public int level2Effect(CharacterProfile profile,CharacterProfile target){
		return effectHelper(140, profile,target);
	}
	/*requires level 25*/
	@Override
	public int level3Effect(CharacterProfile profile,CharacterProfile target){
		return effectHelper(230, profile,target);
	}
	public int effectHelper(int baseDamage, CharacterProfile profile,CharacterProfile target){
		double percent = 0.20 + this.percentRatio * profile.getBonusMagic();
		int scaleDamage = (int) ( percent * (target.getMaxHp() - target.getCurrentHp()));
		target.updateMagicDamageReceived(scaleDamage);
		target.updateTrueDamageReceived(baseDamage);
		return scaleDamage;
	}
	
	@Override
	public boolean requirementsBeforeSkillPoints(CharacterProfile profile){
		if(this.skillPoints < this.level3Cap){
			return true;
		}
		else if(this.skillPoints == this.level3Cap){
			return profile.getLevel() >= 20;
		}
		else if(this.skillPoints == this.level4Cap){
			return profile.getLevel() >= 25;
		}
		else{
			return false;
		}
	}
	@Override
	public void attainingLevel2(){
		this.effectRadius = 2;
		this.castRange = 7;
		this.coolDown = 110;
		this.manaCost = 150;
	}
	@Override
	public void attainingLevel3(){
		this.castRange = 9;
		this.coolDown = 95;
	}
	
	public static void main(String args[]){
		System.out.println(DamageCalculator.effectiveDamage((500 * 0.0005 + 0.25) * 550 + 230, 56));
		System.out.println(DamageCalculator.effectiveDamage((500 * 0.0005 + 0.25) * 550, 56) + 230);
	}
}
