package RPGItemTree;
import RPGelements.CharacterProfile;
import java.util.ArrayList;

public class ShortSword implements Item {
	private boolean equiped; 
	private int attackBonus; 
	private int upgradeCost;
	private int goldValue;
	public ShortSword() {
		this.goldValue = 50;
		this.upgradeCost = 0;
		this.attackBonus = 3;
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
		return null;
	}
	public ArrayList<Item> buildsInto() {
		return null;
	}

	public int goldValue() {
		return this.goldValue;
	}

	public int upgradeCost() {
		return this.upgradeCost;
	}
	public int totalCost(){
		return this.goldValue + this.upgradeCost;
	}

}
