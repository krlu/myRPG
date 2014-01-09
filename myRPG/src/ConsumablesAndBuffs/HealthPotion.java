package ConsumablesAndBuffs;
import RPGelements.CharacterProfile;


/* *****************************************
 * Heals user for 50 hp over 10 seconds
 * TODO: I DON'T KNOW HOW TO DO OVER TIME EFFECTS!!! FUCKKKKKK!!!!
 ******************************************/
public class HealthPotion extends Consumable {
	protected int heal;
	public HealthPotion() {
		this.heal = 50;
		this.amountOfEffectTime = 10;
	}
	
	@Override
	public void applyConsumableEffects(CharacterProfile profile){
		profile.updateHp(this.heal);
	}
}
