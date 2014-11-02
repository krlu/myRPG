package RPGelements;

public class Liche extends CharacterProfile {

	/*********
	 * Liche seek hegemony and absolute control over all other dominions.  
	 * Many are willing to pay the ultimate price to attain the power needed 
	 * to do this. Thus they are endowed with the ability to sacrifice a portion 
	 * of their health in exchange for drastically increased damaging skills. 
	 * Damage bonus scales with levels
	 */
	public Liche(String name, String month, int date, int year, String profession, String faction) {
		super(name, month, date, year, "Liche", profession, faction);
		applyBaseStats();
	}
	
	private void applyBaseStats(){
		this.healthRegen = 4;
		this.maxhp = 64;
		this.hp = 64; 
		this.healthRegen = 3; 
		this.attackDamage = 10; 
		this.attackSpeed = 0.45;
		this.moveSpeed = 8; 
		this.magicResistance = 3;
		this.attackRange = 0;
		this.fervor = 1;
		this.mana = 24; 
		this.bonusMagic = 0;
		this.manaRegen = 2;
		this.gatherSpeed = 4;
		this.buildSpeed = 2;
	}	
	
	/*passive name: Blood Price*/
	public int applyDamageBonus(){
		double percenthp = Math.max(2, this.hp * (1/50));
		this.hp -= percenthp; 
		return 3 + 2*getLevel();
	}
	
	public static void main(String[] args) {

	}

}
