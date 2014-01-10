package ConsumablesAndBuffs;
import RPGelements.CharacterProfile;


/* *****************************************
 * Heals user for 50 hp over 10 seconds
 * TODO: NOT BOTHERING WITH OVER TIME !!!
 ******************************************/
public class HealthPotion extends Consumable {
	protected int heal;
	public HealthPotion() {
		this.heal = 50;
		this.amountOfEffectTime = 10;
		this.numCopies = 0;
		this.goldValue = 5;
		this.name = "HealthPotion";
	}
	
	@Override
	public void applyConsumableEffects(CharacterProfile profile){
		profile.updateHp(this.heal);
	}
}
