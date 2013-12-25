package RPGItemTree;

import java.util.ArrayList;
import RPGelements.Inventory;
import RPGelements.CharacterProfile;
import java.util.HashMap;

/**************************************************************************
 * Class devoted to combining component items and/or upgrading single items
 * 
 */
public class ItemCombiner {

	public ItemCombiner() {

	}

	public static Item combineItems(ArrayList<Item> components, Item product, CharacterProfile profile) {
		if(hasAllComponents(product, components) && profile.getGold() >= product.upgradeCost()){
			profile.updateGold(product.upgradeCost());
			return product;
		}
		return null;
	}

	/*
	 * returns all possible builds given a list of items.
	 * TODO: not sure if this is necessary yet
	 */
	public static ArrayList<Item> possibleBuilds(ArrayList<Item> components) {
		HashMap<Item, Integer> tempMap = new HashMap<Item, Integer>();
		ArrayList<Item> builds = new ArrayList<Item>();
		for (Item i : components) {
			for (Item item : i.buildsInto()) {
				if(tempMap.containsKey(item)){
					int tempInt = tempMap.get(item);
					tempInt ++; 
					tempMap.put(item, tempInt);
				}
				else{
					tempMap.put(item, 1);
				}
			}
		}
		for(Item item : tempMap.keySet()){
			if(tempMap.get(item) == components.size()){
				builds.add(item);
			}
		}
		return builds;
	}

	private static boolean hasAllComponents(Item product, ArrayList<Item> components) {
		for (Item i : product.buildsFrom()) {
			if (!components.contains(i)) {
				return false;
			}
		}
		return true;
	}
}
