package RPGelements;

public class Dwarf extends CharacterProfile{

	public Dwarf(String name, String month, int date, int year, String race,
			String profession, String faction) {
		super(name, month, date, year, "Dwarf", profession, faction);
		applyBaseStats();
	}
	private void applyBaseStats(){
		this.hp = 50; 
		this.attackDamage = 9; 
		this.attackSpeed = 0.45;
		this.moveSpeed = 8; 
		this.magicResistance = 4;
		this.attackRange = 0;
		this.fervor = 3;
		this.mana = 28; 
		this.bonusMagic = 0;
		this.manaRegen = 2;
		this.gatherSpeed = 5;
		this.buildSpeed = 2;
	}	
	public static void main(String[] args) {
		Human me = new Human("Kenny", "August", 30, 1991, "asdfasdf", "merchant", "");			
		System.out.println(me.getName() + "\n" + me.getDOB() + "\n" + me.getRace());
	}
}
