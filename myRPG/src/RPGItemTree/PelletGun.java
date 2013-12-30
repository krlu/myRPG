package RPGItemTree;

import java.util.ArrayList;

import RPGelements.CharacterProfile;
/**
 * TODO: only equipable on profiles with range > 0 (non-melee only)
 */
public class PelletGun extends Item {
	private double attackSpeedBonus; 
	public PelletGun() {
		this.equiped = false;
		this.attackSpeedBonus = 0.10;
		this.goldValue = 50;
		this.totalCost = 50;
		this.upgradeCost = 0;
		this.buildsTo = new ArrayList<Item>();
		this.buildsFrom = new ArrayList<String>();
		this.buildsTo.add(new AlbionRifle());
		this.name = "PelletGun";
	}
	@Override
	public void applyItemEffects(CharacterProfile profile){
		profile.updateAttackSpeed(this.attackSpeedBonus);
	}
	@Override
	public void removeItemEffects(CharacterProfile profile){
		profile.updateAttackSpeed(-1 * this.attackSpeedBonus);	
	}
}
