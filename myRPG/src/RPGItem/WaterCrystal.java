package RPGItem;

import java.util.ArrayList;

import RPGelements.CharacterProfile;
/*******************
 * Stats granted: 
 * +10 mana
 *******************/
public class WaterCrystal extends Item {

	private int manaBonus; 
	
	public WaterCrystal() {
		this.equiped = false;
		this.manaBonus = 10;
		this.goldValue = 40;
		this.totalCost = 40;
		this.upgradeCost = 0;
		this.buildsTo = new ArrayList<Item>();
		this.buildsFrom = new ArrayList<String>();
		this.name = "WaterCrystal";
		this.buildsTo.add(new BroadSword());
	}
	
	@Override
	public void applyItemEffects(CharacterProfile profile){
		profile.updateMana(this.manaBonus);
	}
	@Override
	public void removeItemEffects(CharacterProfile profile){
		profile.updateMana(-1 * this.manaBonus);
	}
	@Override
	public void printStats() {
		System.out.println("mana: + 10");
	}
}
