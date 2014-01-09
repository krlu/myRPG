package RPGItem;

import java.util.ArrayList;

import RPGelements.CharacterProfile;

/*******************
 * Stats granted: 
 * +6 attack damage
 *******************/
public class BroadSword extends Item {
	 
	private int attackBonus; 
	
	public BroadSword() {
		this.equiped = false;
		this.attackBonus = 6;
		this.goldValue = 0;
		this.upgradeCost = 50;
		this.totalCost = 100;
		this.buildsTo = new ArrayList<Item>();
		this.buildsFrom = new ArrayList<String>();
		this.name = "BroadSword";
		this.buildsFrom.add("ShortSword");
		this.buildsTo.add(new EmeraldSword());
	}

	@Override
	public void applyItemEffects(CharacterProfile profile){
		profile.updateAttack(this.attackBonus);
	}
	@Override
	public void removeItemEffects(CharacterProfile profile){
		profile.updateAttack(-1 * this.attackBonus);
	}
	@Override
	public void printStats() {
		System.out.println("Attack Damage: + 6");
	}
	
	
	public static void main(String[] args){
		Item item = new BroadSword();
		System.out.println(item.totalCost());
	}
}
