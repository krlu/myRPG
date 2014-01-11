package ConsumablesAndBuffs;

import RPGelements.CharacterProfile;

public class ManaPotion extends Consumable {

	protected int mana;
	protected int totalManaRegened;
	public ManaPotion() {
		this.totalManaRegened = 0;
		this.mana = 50;
		this.amountOfEffectTime = 10;
		this.remainingEffectTime = 10;
		this.numCopies = 0;
		this.goldValue = 5;
		this.name = "manathPotion";
	}
	public int totalManaRegened(){
		return this.totalManaRegened;
	}
	public int remainingTime(){
		return this.remainingEffectTime;
	}
	@Override
	public void applyConsumableEffects(CharacterProfile profile){
		if(this.remainingEffectTime > 0){
			profile.updateMana(this.mana/this.amountOfEffectTime);
			this.totalManaRegened += this.mana/this.amountOfEffectTime;	
			this.remainingEffectTime--;
		}
	}
}
