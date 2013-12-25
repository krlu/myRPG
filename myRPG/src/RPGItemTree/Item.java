package RPGItemTree;
import java.util.ArrayList;

import RPGelements.CharacterProfile;

public class Item{
	//private int itemID; 
	public boolean equiped; 
	protected int upgradeCost;
	protected int goldValue;
	protected String name;
	protected ArrayList<Item> buildsFrom;
	protected ArrayList<Item> buildsTo;
	public Item(){
		
	}
	
	public void equipItem(CharacterProfile profile) {
		if(this.equiped == true){
			System.err.println("already equiped shortsword!");
		}
		else if (profile.getEquipedItems().size() > CharacterProfile.EquipItemThreshold){
			System.err.println("at equipe threshold, cannot equip more items!!");
		}
		else{
			this.equiped = true; 
			applyItemEffects(profile);
		}
	}
	
	public void unequipItem(CharacterProfile profile){
		if(this.equiped == false){
			System.err.println("never equiped shortsword!");
		}
		else{
			this.equiped = false; 
			removeItemEffects(profile);
		}
	}
	public String itemName(){
		return this.name;
	}
	/*Override in all extended classes!*/
	public void applyItemEffects(CharacterProfile profile){
		System.err.println("method applyItemEffects need override!");
	}
	public void removeItemEffects(CharacterProfile profile){
		System.err.println("method removeItemEffects need override!");	
	}
	
	/*getters for fields*/
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
