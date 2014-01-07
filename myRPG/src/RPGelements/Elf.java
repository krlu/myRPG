package RPGelements;

public class Elf extends CharacterProfile {
	/**********************************************************************
	 * Unique Attributes: A race as a old as the world itself Elves draw 
	 * much of their power from organic matter thus, they receive increased 
	 * regenerative numbers when near water or forests. Scales with levels
	 **********************************************************************/
	public Elf(String name, String month, int date, int year, String profession, String faction) {
		super(name, month, date, year, "Elf",profession, faction);
		applyBaseStats();
	}
	private void applyBaseStats(){
		this.maxhp = 56;
		this.hp = 56; 
		this.healthRegen = 3; 
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
	
	/*Passive name: Gaia's Blessing*/
	public void applyRegenerativeBonuses(String terrain){
		if(terrain.equals("Water") || terrain.equals("Forest")){
			this.healthRegen += 1 + (getLevel()/4); 
			this.manaRegen += 1 + (getLevel()/4); 
		}
	}
	
	public static void main (String[] args){
		
	}
}
