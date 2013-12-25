package RPGelements;

public class Elf extends CharacterProfile {
	/***********************************
	 * TODO: Elf special bonuses needed
	 **********************************/
	public Elf(String name, String month, int date, int year, String profession, String faction) {
		super(name, month, date, year, "Elf",profession, faction);
	}
	private void applyBaseStats(){
		this.hp = 46; 
		this.attackDamage = 6; 
		this.attackSpeed = 0.52;
		this.moveSpeed = 9; 
		this.magicResistance = 5;
		this.attackRange = 0;
		this.fervor = 3;
		this.mana = 32; 
		this.bonusMagic = 0;
		this.manaRegen = 3;
		this.gatherSpeed = 4;
		this.buildSpeed = 2;
	}
	public static void main (String[] args){
		
	}
}
