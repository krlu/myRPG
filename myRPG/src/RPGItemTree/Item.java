package RPGItemTree;
import java.util.ArrayList;

import RPGelements.CharacterProfile;


/************************************************************************
 *  An outline of an Item. All additional objects created in this package
 *  will extend this class. Note that the methods and fields within this 
 *  class reflect basic functionality of an equipment item. Gold values 
 *  for this class are hard-coded to save computation.
 ***********************************************************************/


public class Item{
	//private int itemID; 
	public boolean equiped; 
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
	public String getName(){
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
}
