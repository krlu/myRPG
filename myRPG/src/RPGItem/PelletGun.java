package RPGItem;

import java.util.ArrayList;

import RPGelements.CharacterProfile;
/**
 * Requires user with range > 0 (non-melee only)
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
		profile.updateAttackSpeed(this.attackSpeedBonus);
	}
	@Override
	public void removeItemEffects(CharacterProfile profile){
		profile.updateAttackSpeed(-1 * this.attackSpeedBonus);	
	}
	public static void main(String[] args){
		CharacterProfile me = new CharacterProfile("Kenny", "August", 30, 1991, "human", "merchant", "");			
		Item item = new AlbionGunblade();
		item.equipItem(me);
	}
}
