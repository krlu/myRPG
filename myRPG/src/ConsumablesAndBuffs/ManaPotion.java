package ConsumablesAndBuffs;
import RPGelements.CharacterProfile;

/* ******************************************************
 * restores user for 50 + 10*(level) mana over 10 seconds
 * TODO: NOT BOTHERING WITH OVER TIME !!!
 ********************************************************/

public class ManaPotion extends Consumable {
	
	protected int baseMana;
	protected int totalManaRegened;
	public ManaPotion() {
		this.totalManaRegened = 0;
		this.baseMana = 50;
		this.amountOfEffectTime = 10;
		this.remainingEffectTime = 10;
		this.numCopies = 0;
		this.goldValue = 5;
		this.name = "manathPotion";
	}
	//for print outs 
	public int totalManaRegened(){
		return this.totalManaRegened;
	}
	public int remainingTime(){
		return this.remainingEffectTime;
	}
	@Override
	public void applyConsumableEffects(CharacterProfile profile){
		int totalMana = this.baseMana + 5*profile.getLevel();
		if(this.remainingEffectTime > 0){
			profile.updateMana(totalMana/this.amountOfEffectTime);
			this.totalManaRegened += totalMana/this.amountOfEffectTime;	
			this.remainingEffectTime--;
		}
	}
}
