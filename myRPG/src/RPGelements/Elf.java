package RPGelements;

public class Elf extends CharacterProfile {
	/**********************************************************************
	 * Unique Attributes: A race as a old as the world itself Elves draw 
	 * much of their power from organic matter thus, they receive increased 
	 * regenerative numbers when near water or forests. 
	 **********************************************************************/
	public Elf(String name, String month, int date, int year, String profession, String faction) {
		super(name, month, date, year, "Elf",profession, faction);
		applyBaseStats();
	}
	private void applyBaseStats(){
		this.hp = 46; 
		this.healthRegen = 5; 
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
	
	public int applyRegenerativeBonuses(String terrain){
		int regenBonus = 0; 
		if(terrain.equals("Water") || terrain.equals("Forest")){
			regenBonus = 2; 
		}
		return regenBonus; 
	}
	public static void main (String[] args){
		
	}
}
