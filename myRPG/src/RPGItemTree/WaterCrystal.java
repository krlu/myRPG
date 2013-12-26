package RPGItemTree;

import java.util.ArrayList;

import RPGelements.CharacterProfile;

public class WaterCrystal extends Item {

	private int manaBonus; 
	//TODO: RESOLVE RECURSION BETWEEN SHORT AND BROAD SWORD!!
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
	
	public void printStats() {
		System.out.println("mana: + 10");
	}
}
