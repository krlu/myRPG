package RPGelements;
import java.util.ArrayList;
import java.util.Arrays;
public class Human extends CharacterProfile{
	
	/***********************************************************************************
	 *  Unique Attributes: Humans are industrial consumers, and guzzle resources quickly 
	 *  thus they are the only race that receives special bonuses in gathering
	 *  stone, iron, and oil. Scales with player level
	 ***********************************************************************************/
	
	private ArrayList<String> amplifiedResources = new ArrayList<String>(Arrays.asList("Stone", "Iron", "Oil"));
	public Human(String name, String month, int date, int year, String race,
			String profession, String faction) {
		super(name, month, date, year, "Human", profession, faction);
		applyBaseStats();
	}
	
	private void applyBaseStats(){
		this.healthRegen = 1;
		this.hp = 50; 
		this.attackDamage = 7; 
		this.attackSpeed = 0.50;
		this.moveSpeed = 10; 
		this.magicResistance = 3;
		this.attackRange = 0;
		this.fervor = 0;
		this.mana = 25; 
		this.bonusMagic = 0;
		this.manaRegen = 2;
		this.gatherSpeed = 5;
		this.buildSpeed = 1;
	}	
	public int applyGatheringBonus(String resource){
		int gatherBonus = 0;
		if(this.amplifiedResources.contains(resource)){
			gatherBonus = (getLevel()/4);
		}
		return gatherBonus;
	}
	public static void main(String[] args) {
		Human me = new Human("Kenny", "August", 30, 1991, "asdfasdf", "merchant", "");			
		System.out.println(me.getName() + "\n" + me.getDOB() + "\n" + me.getRace());
		System.out.println(30/4);
	}

}
