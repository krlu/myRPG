package RPGItemTree;

import java.util.ArrayList;

import RPGelements.CharacterProfile;

public class BroadSword implements Item {
	
	private boolean equiped; 
	private int attackBonus; 
	private int upgradeCost;
	private int goldValue;
	
	public BroadSword() {
		this.attackBonus = 6;
		this.goldValue = 0;
		this.upgradeCost = 50;
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


	@Override
	public ArrayList<Item> buildsFrom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Item> buildsInto() {
		// TODO Auto-generated method stub
		return null;
	}
	public int goldValue() {
		return this.goldValue;
	}

	public int upgradeCost() {
		return this.upgradeCost;
	}
	public int totalCost(){
		return this.goldValue + this.upgradeCost;
	}
}
