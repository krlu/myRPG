package RPGItemTree;
import RPGelements.CharacterProfile;
import RPGelements.Dwarf;

import java.util.ArrayList;

/*******************
 * Stats granted: 
 * +3 attack damage
 *******************/
public class ShortSword extends Item {

	private int attackBonus; 
	
	public ShortSword() {
		this.equiped = false;
		this.attackBonus = 3;
		this.goldValue = 50;
		this.totalCost = 50;
		this.upgradeCost = 0;
		this.buildsTo = new ArrayList<Item>();
		this.buildsFrom = new ArrayList<String>();
		this.name = "ShortSword";
		this.buildsTo.add(new BroadSword());
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
		System.out.println("builds to: " + item.buildsTo.get(0).getName());
		
		//TODO: find better way to equip item!!
		item.equipItem(me);
		me.addToEquipedItems(item);
		
		System.out.println("my item list:" + me.getEquipedItems().size());
		me.printEquipedItems();
	}
}
