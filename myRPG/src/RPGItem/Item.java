package RPGItem;
import java.util.ArrayList;

import RPGelements.CharacterProfile;
import FundamentalStructures.Tuple;

/************************************************************************
 *  An outline of an Item. All additional objects created in this package
 *  will extend this class. Note that the methods and fields within this 
 *  class reflect basic functionality of an equipment item. Gold values 
 *  for this class are hard-coded to save computation.
 ***********************************************************************/


public class Item{
	//private int itemID; 
	public boolean equiped; 
	protected Tuple<Integer,Integer> inventoryPosition;
	protected int upgradeCost;
	protected int goldValue;
	protected int totalCost; 
	protected String name;
	
	/* ****************************************************** 
	 * SUPER IMPORTANT!!! buildsFrom are just strings
	 * whereas buildsTo are actual Item objects. We do this 
	 * to prevent backwards reference, which would result in
	 * a stack overflow. Moreover, when combining items, it 
	 * is not necessary to refer to buildsFrom as Items since 
	 * presumably user needs to have component items already
	 ********************************************************/
	protected ArrayList<String> buildsFrom;
	protected ArrayList<Item> buildsTo;
	public Item(){
		this.inventoryPosition = new Tuple<Integer,Integer>(0,0);
	}
	
	public void equipItem(CharacterProfile profile) {
		if(this.equiped == true){
			System.err.println("already equiped " + this.name + "!!");
		}
		else if (profile.getEquipedItems().size() > CharacterProfile.EquipItemThreshold){
			System.err.println("at equipe threshold, cannot equip more items!!");
		}
		else if(equipeConditions(profile)){
				
			this.equiped = true; 
			applyItemEffects(profile);
		}else{
			System.err.println("cannot equip " + this.name + "!!");
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
	public void updateInventoryPosition(int row, int col){
		this.inventoryPosition.l = row;
		this.inventoryPosition.r = col;
	}
	/*Override in all extended classes!*/
	public boolean equipeConditions(CharacterProfile profile){
		System.err.println("method applyItemEffects need override!");
		return true;
	}
	public void applyItemEffects(CharacterProfile profile){
		System.err.println("method applyItemEffects need override!");
	}
	public void removeItemEffects(CharacterProfile profile){
		System.err.println("method removeItemEffects need override!");	
	}
	public void printStats(){
		
	}
	/*getters for fields*/
	public String getName(){
		return this.name;
	}
	public ArrayList<String> buildsFrom() {
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
		return this.totalCost;
	}
	public boolean isEquipped(){
		return this.equiped;
	}
	public Tuple<Integer,Integer> getInventoryPosition(){
		return this.inventoryPosition;
	}
	/*comparator between items*/
	public boolean equals(Item item){
		return this.name.equals(item.getName());
	}
	
	public static void main(String[] args){
		Item item = new AlbionRifle();
		item.updateInventoryPosition(3, 4);
		System.out.println(item.getInventoryPosition().l);
	}
}
