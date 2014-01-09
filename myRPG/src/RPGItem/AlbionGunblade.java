package RPGItem;

import java.util.ArrayList;

import RPGelements.CharacterProfile;

/**
 * Requires user with range > 0 (non-melee only)
 */
public class AlbionGunblade extends Item {
	
	private double attackSpeedBonus;
	private int bonusMagic;
	private int manaBonus;
	private int attackBonus; 
	public AlbionGunblade() {
		this.equiped = false;
		this.attackSpeedBonus = 0.20;
		this.bonusMagic = 20;
		this.manaBonus = 20;
		this.attackBonus = 10;
		
		this.goldValue = 0;
		this.totalCost = 470;
		this.upgradeCost = 85;
		this.buildsTo = new ArrayList<Item>();
		this.buildsFrom = new ArrayList<String>();
		this.buildsFrom.add("AlbionRifle");
		this.buildsFrom.add("EmeraldSword");
		this.name = "AlbionGunblade";
	}
	@Override
	public boolean equipeConditions(CharacterProfile profile){
		if(profile.getAttackRange() > 0){
			return true;
		}
		else{
			System.err.println("You are not a ranged unit!");
			return false;
		}
	}
	@Override
	public void applyItemEffects(CharacterProfile profile){
		profile.updateMana(this.manaBonus);
		profile.updateAttack(this.attackBonus);
		profile.updateAttackSpeed(this.attackSpeedBonus);
		profile.updateBonusMagic(this.bonusMagic);
	}
	@Override
	public void removeItemEffects(CharacterProfile profile){
		profile.updateMana(-1 * this.manaBonus);
		profile.updateAttack(-1*this.attackBonus);
		profile.updateAttackSpeed(-1 * this.attackSpeedBonus);	
		profile.updateBonusMagic(-1 * this.bonusMagic);
	}

	public void printStats(){
		System.out.println("attackSpeedBonus: + 20% \n" + 
						   "bonusMagic: + 20" + 
						   "mana: +20" + 
						   "attack: + 10");
	}
}
