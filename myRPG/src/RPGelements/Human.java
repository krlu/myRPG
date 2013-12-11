package RPGelements;

public class Human extends CharacterProfile{
	
	public Human(String name, String month, int date, int year, String race,
			String profession, String faction) {
		super(name, month, date, year, "Human", profession, faction);
		applyBaseStats();
	}
	
	/* TODO: apply scaling stats for humans
	 * TODO: unique human abilities
	 */
	private void applyBaseStats(){
		this.hp = 50; 
		this.attackDamage = 7; 
		this.attackSpeed = 0.50;
		this.moveSpeed = 10; 
		this.magicResistance = 3;
		this.attackRange = 0;
		this.fervor = 0;
		this.mana = 25; 
		this.bonusMagic = 0;
	}	
	public void uniqueAbilities(){
		
	}
	public static void main(String[] args) {
		Human me = new Human("Kenny", "August", 30, 1991, "asdfasdf", "merchant", "");			
		System.out.println(me.getName() + "\n" + me.getDOB() + "\n" + me.getRace());
	}

}
