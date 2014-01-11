package ConsumablesAndBuffs;
import RPGelements.CharacterProfile;
import RPGelements.Dwarf;
import java.util.Timer;
import java.util.TimerTask;

/* *****************************************
 * Heals user for 50 hp over 10 seconds
 * TODO: NOT BOTHERING WITH OVER TIME !!!
 ******************************************/
public class HealthPotion extends Consumable {
	protected int heal;
	protected int totalHeal;
	public HealthPotion() {
		this.totalHeal = 0;
		this.heal = 50;
		this.amountOfEffectTime = 10;
		this.remainingEffectTime = 10;
		this.numCopies = 0;
		this.goldValue = 5;
		this.name = "HealthPotion";
	}
	
	@Override
	public void applyConsumableEffects(CharacterProfile profile){
		if(this.remainingEffectTime > 0){
			profile.updateHp(this.heal/this.amountOfEffectTime);
			totalHeal += this.heal/this.amountOfEffectTime;
			this.remainingEffectTime--;
		}
	}
	public int totalHeal(){
		return this.totalHeal;
	}
	public int remainingTime(){
		return this.remainingEffectTime;
	}
	public static void main(String[] argv) {		
		Timer timer = new Timer();
		timer.schedule( new TimerTask() {
			CharacterProfile me = new Dwarf("Kenny", "August", 30, 1991, "merchant", "");	
			HealthPotion hp = new HealthPotion();
			ManaPotion mp = new ManaPotion();
			int i=0;
		    public void run() {
		    	if(i >= 2){
		    		mp.applyConsumableEffects(me);
		    	}
		    	hp.applyConsumableEffects(me);
		    	System.out.println(i + " , " + hp.totalHeal() + " " + hp.remainingTime() + " , " + mp.totalManaRegened() + " " + mp.remainingTime());
		    	i++;
		    }
		 }, 1000, 1000);
	} 
}
