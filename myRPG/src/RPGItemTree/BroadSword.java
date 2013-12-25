package RPGItemTree;

import java.util.ArrayList;

import RPGelements.CharacterProfile;

public class BroadSword implements Item {
	
	private boolean equiped; 
	private int attackBonus; 
	private int upgradeCost;
	private int goldValue;
	private ArrayList<Item> buildsFrom;
	private ArrayList<Item> buildsTo;
	
	public BroadSword() {
		this.attackBonus = 6;
		this.goldValue = 0;
		this.upgradeCost = 50;
		this.buildsTo = new ArrayList<Item>();
		this.buildsFrom = new ArrayList<Item>();
		this.buildsFrom.add(new ShortSword());
	}

	public void equipItem(CharacterProfile profile) {
		if(this.equiped == true){
			System.err.println("already equiped shortsword!");
		}
		else if (profile.getEquipedItems().size() <= CharacterProfile.EquipItemThreshold){
			System.err.println("at equipe threshold, cannot equip more items!!");
		}
		else{
			this.equiped = true; 
			profile.updateAttack(this.attackBonus);
		}
	}
	
	public void unequipItem(CharacterProfile profile){
		if(this.equiped == false){
			System.err.println("never equiped shortsword!");
		}
		else{
			this.equiped = false; 
			profile.updateAttack(-1*this.attackBonus);
		}
	}
	
	public void printStats() {
		System.out.println("Attack Damage: + 6");
	}


	public ArrayList<Item> buildsFrom() {
		return this.buildsFrom;
	}
	public ArrayList<Item> buildsInto() {
		return this.buildsTo;
	}
	public int goldValue() {
		return this.goldValue;
	}

	public int upgradeCost() {
		return this.upgradeCost;
	}
	public int totalCost(){
		int total = 0;
		for(Item i : this.buildsFrom){
			total += i.totalCost();
		}
		total += this.goldValue + this.upgradeCost;
		return total;
	}
}
