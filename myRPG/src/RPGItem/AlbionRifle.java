package RPGItem;

import java.util.ArrayList;

import RPGelements.CharacterProfile;

public class AlbionRifle extends Item {

	private double attackSpeedBonus; 
	private int bonusMagic; 
	public AlbionRifle() {
		this.equiped = false;
		this.attackSpeedBonus = 0.10;
		this.bonusMagic = 15;
		this.goldValue = 0;
		this.totalCost = 175;
		this.upgradeCost = 75;
		this.buildsTo = new ArrayList<Item>();
		this.buildsFrom = new ArrayList<String>();
		this.buildsTo.add(new AlbionGunblade());
		this.buildsFrom.add("PelletGun");
		this.buildsFrom.add("JadeRelic");
		this.name = "AlbionRifle";
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
		profile.updateBonusMagic(this.bonusMagic);
	}
	@Override
	public void removeItemEffects(CharacterProfile profile){
		profile.updateAttackSpeed(-1 * this.attackSpeedBonus);	
		profile.updateBonusMagic(-1 * this.bonusMagic);
	}

}
