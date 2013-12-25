package RPGItemTree;
import RPGelements.CharacterProfile;
import java.util.ArrayList;

public class ShortSword implements Item {
	private boolean equiped; 
	private int attackBonus; 
	private int upgradeCost;
	private int goldValue;
	private ArrayList<Item> buildsFrom;
	private ArrayList<Item> buildsTo;
	
	public ShortSword() {
		this.goldValue = 50;
		this.upgradeCost = 0;
		this.attackBonus = 3;
		this.buildsFrom = new ArrayList<Item>(); 
		this.buildsTo = new ArrayList<Item>();
		this.buildsTo.add(new BroadSword());
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
		System.out.println("Attack Damage: + 3");
	}

	/*this is a basic item*/
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
