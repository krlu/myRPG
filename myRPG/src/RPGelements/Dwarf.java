package RPGelements;

public class Dwarf extends CharacterProfile{
	/***********************************************************************************
	 *  Unique Attributes: Dwarfs are descendants of an old race, and the nearest DNA
	 *  cousins of humans. They excel in mountainous terrain and are excellent craftsman
	 *  and diggers. Thus they have bonus build speed on mountainous terrain. 
	 *  These bonuses scale with levels.
	 ***********************************************************************************/
	
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
	public int applyTerrainBuildBonus(String terrain){
		int buildBonus = 0;
		if(terrain.equals("Mountain") || terrain.equals("Foothills")){
			buildBonus = (getLevel()/6);
		}
		return buildBonus;
	}
	public static void main(String[] args) {
		CharacterProfile me = new Dwarf("Kenny", "August", 30, 1991, "asdfasdf", "merchant", "");			
		System.out.println(me.getName() + "\n" + me.getDOB() + "\n" + me.getRace());
	}
}
