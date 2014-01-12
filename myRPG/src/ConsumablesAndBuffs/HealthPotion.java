package ConsumablesAndBuffs;
import RPGelements.CharacterProfile;

/* *****************************************
 * Heals user for 50 + 10*(level) hp over 10 seconds
 * TODO: NOT BOTHERING WITH OVER TIME !!!
 ******************************************/
public class HealthPotion extends Consumable {
	protected int baseHeal;
	protected int totalHeal;
	public HealthPotion() {
		this.totalHeal = 0;
		this.baseHeal = 50;
		this.amountOfEffectTime = 10;
		this.remainingEffectTime = 10;
		this.numCopies = 0;
		this.goldValue = 5;
		this.name = "HealthPotion";
	}
	
	@Override
	public void applyConsumableEffects(CharacterProfile profile){
		int  totalHeal = this.baseHeal + 5*profile.getLevel();
		if(this.remainingEffectTime > 0){
			profile.updateHp(totalHeal/this.amountOfEffectTime);
			totalHeal += totalHeal/this.amountOfEffectTime;
			this.remainingEffectTime--;
		}
	}
	//for print outs 
	public int totalHeal(){
		return this.totalHeal;
	}
	public int remainingTime(){
		return this.remainingEffectTime;
	}
	public static void main(String[] argv) {		
	} 
}
