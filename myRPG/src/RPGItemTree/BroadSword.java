package RPGItemTree;

import java.util.ArrayList;

import RPGelements.CharacterProfile;

public class BroadSword extends Item {
	 
	private int attackBonus; 
	
	public BroadSword(String buildsFrom){
		constructorHelper();
	}
	public BroadSword() {
		constructorHelper();
		this.buildsFrom.add(new ShortSword(this.name));
	}
	public void constructorHelper(){
		this.attackBonus = 6;
		this.goldValue = 0;
		this.upgradeCost = 50;
		this.buildsTo = new ArrayList<Item>();
		this.buildsFrom = new ArrayList<Item>();
		this.name = "BroadSword";
	}
	public void equipItem(CharacterProfile profile) {
		if(this.equiped == true){
			System.err.println("already equiped broadsword!");
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
	
	
	public static void main(String[] args){
		Item item = new BroadSword();
		System.out.println(item.totalCost());
	}
}
