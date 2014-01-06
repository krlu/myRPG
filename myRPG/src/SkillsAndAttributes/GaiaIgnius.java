package SkillsAndAttributes;

import RPGelements.CharacterProfile;
import RPGelements.DamageCalculator;

/***********************************************************
 * Execution move that deals damage based on missing health
 */
public class GaiaIgnius extends Skill {
	private final double percentRatio = 0.0003;
	public GaiaIgnius() {
		this.maxTargets = 1;
		this.skillPoints = 0;
		this.coolDown = 100.0;
		this.castRange = 4;
		this.manaCost = 100;
		this.name = "ArcaneFire";
		this.level1Cap = 1; 
		this.level2Cap = 2;
		this.level3Cap = 4;
	}
	/*requires level 15*/
	@Override
	public int level1Effect(CharacterProfile profile){
		return effectHelper(100, profile);
	}
	/*requires level 20*/
	@Override
	public int level2Effect(CharacterProfile profile){
		return effectHelper(140, profile);
	}
	/*requires level 25*/
	@Override
	public int level3Effect(CharacterProfile profile){
		return effectHelper(230, profile);
	}
	public int effectHelper(int baseDamage, CharacterProfile profile){
		CharacterProfile target = this.targets.get(0);
		double percent = 0.20 + 0.0005 * profile.getBonusMagic();
		int damage = baseDamage + (int) ( percent * (target.getMaxHp() - target.getCurrentHp()));
		profile.updateTotalMagicDamage(damage);
		return damage;
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
		this.coolDown = 90;
	}
	@Override
	public void attainingLevel3(){
		this.coolDown = 75;
	}
	
	public static void main(String args[]){
		System.out.println(DamageCalculator.effectiveDamage((500 * 0.0005 + 0.25) * 550 + 230, 56));
		System.out.println(DamageCalculator.effectiveDamage((500 * 0.0005 + 0.25) * 550, 56) + 230);
	}
}
