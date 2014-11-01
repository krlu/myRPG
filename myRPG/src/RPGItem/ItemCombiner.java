package RPGItem;

import java.util.ArrayList;

import RPGelements.CharacterProfile;
import RPGelements.Dwarf;

import java.util.HashMap;

/***********************************************************************
 * Class devoted to combining component items or upgrading single items
 * 
 */
public class ItemCombiner {

	public static Item combineItems(ArrayList<Item> components, Item product, CharacterProfile profile) {
		if(hasAllComponents(product, components) && profile.getGold() >= product.upgradeCost()){
			profile.updateGold(-product.upgradeCost());
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

	/*Simply checks required items against list of component items
	 * TODO: decide if this should be optimized with HashMap*/
	private static boolean hasAllComponents(Item product, ArrayList<Item> components) {
		ArrayList<String> itemNames = justItemNames(components);
		if(itemNames.size() < product.buildsFrom().size()){
			System.out.println("missing components!!");
			return false;
		}
		if(itemNames.size() > product.buildsFrom().size()){
			System.out.println("too many components!!");
			return false;
		}
		for (String s : product.buildsFrom()) {
			if (!itemNames.contains(s)) {
				System.out.println("wrong components");
				return false;
			}
		}
		return true;
	}
	private static ArrayList<String> justItemNames(ArrayList<Item> items){
		ArrayList<String> itemNames = new ArrayList<String>();
		for(Item item : items){
			itemNames.add(item.getName());
		}
		return itemNames;
	}
	
	public static void main(String[] args){
		CharacterProfile me = new Dwarf("Kenny", "August", 30, 1991, "merchant", "");
		me.updateGold(1000);
		Item shortSword = new ShortSword(); 
		Item waterCrystal = new WaterCrystal();
		ArrayList<Item> components = new ArrayList<Item>();
		components.add(shortSword);
		Item broadSword = ItemCombiner.combineItems(components, new BroadSword(), me);
		broadSword.printStats();
		components.remove(0);
		components.add(broadSword);
		components.add(new BroadSword());
	//	Item emeraldSword1 = ItemCombiner.combineItems(components, new EmeraldSword(), me);
		components.remove(1);
		components.add(waterCrystal);
		EmeraldSword emeraldSword = (EmeraldSword) ItemCombiner.combineItems(components, new EmeraldSword(), me);
		System.out.println("************");
		emeraldSword.printStats();
		System.out.println("************");
		System.out.println(me.getGold());
	}
}
