package RPGItem;

import java.util.ArrayList;

import RPGelements.CharacterProfile;

/*******************
 * Stats granted: 
 * +20 mana
 * +7 attack damage
 *******************/

public class EmeraldSword extends Item{
	private int manaBonus;
	private int attackBonus; 
	
	public EmeraldSword() {
		this.equiped = false;
		this.manaBonus = 20;
		this.attackBonus = 7;
		this.goldValue = 0;
		this.totalCost = 220;
		this.upgradeCost = 80;
		this.buildsTo = new ArrayList<Item>();
		this.buildsFrom = new ArrayList<String>();
		this.name = "WaterCrystal";
		this.buildsFrom.add("BroadSword");
		this.buildsFrom.add("WaterCrystal");
	}
	
	@Override
	public void applyItemEffects(CharacterProfile profile){
		profile.updateMana(this.manaBonus);
		profile.updateAttack(this.attackBonus);
	}
	@Override
	public void removeItemEffects(CharacterProfile profile){
		profile.updateMana(-1 * this.manaBonus);
		profile.updateAttack(-1*this.attackBonus);
	}
	@Override
	public void printStats() {
		System.out.println("mana: + 20 \n"+ "Attack Damage: + 7");
	}
}
