package RPGItemTree;
import RPGelements.CharacterProfile;
import RPGelements.Dwarf;

import java.util.ArrayList;

public class ShortSword extends Item {

	private int attackBonus; 
	//TODO: RESOLVE RECURSION BETWEEN SHORT AND BROAD SWORD!!
	public ShortSword() {
		constructorHelper();
		this.buildsTo.add(new BroadSword(this.name));
	}
	public ShortSword(String s) {
		constructorHelper();
	}
	private void constructorHelper(){
		this.attackBonus = 3;
		this.goldValue = 50;
		this.upgradeCost = 0;
		this.buildsTo = new ArrayList<Item>();
		this.buildsFrom = new ArrayList<Item>();
		this.name = "ShortSword";
	}
	
	@Override
	public void applyItemEffects(CharacterProfile profile){
		profile.updateAttack(this.attackBonus);
	}
	@Override
	public void removeItemEffects(CharacterProfile profile){
		profile.updateAttack(-1 * this.attackBonus);
	}
	
	public void printStats() {
		System.out.println("Attack Damage: + 3");
	}
	public static void main(String[] args){
		CharacterProfile me = new Dwarf("Kenny", "August", 30, 1991, "merchant", "");
		ShortSword item = new ShortSword();
		System.out.println("my item list:" + me.getEquipedItems().size());
		System.out.println("builds to: " + item.buildsTo.get(0).itemName());
		item.equipItem(me);
		me.addToEquipedItems(item);
		System.out.println("my item list:" + me.getEquipedItems().size());
		me.printEquipedItems();
	}
}
