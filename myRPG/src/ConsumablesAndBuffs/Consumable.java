package ConsumablesAndBuffs;
import java.util.Timer;
import java.util.TimerTask;

import FundamentalStructures.Tuple;
import RPGelements.CharacterProfile;
import RPGelements.Dwarf;

/* ******************************************************************
 * Consumables behave like items in that they are stored in inventory 
 * and their primary effects do not have a cooldown, but they behave 
 * like skills in that their effects are only temporary and require 
 * activation from the user.
 ********************************************************************/

public class Consumable {
	public static final int maxCopies = 5;
	protected Tuple<Integer,Integer> inventoryPosition;
	protected int numCopies;
	protected int goldValue;
	protected String name;
	protected int amountOfEffectTime; // for over time effects
	protected int remainingEffectTime;
	public Consumable() {
		this.inventoryPosition = new Tuple<Integer,Integer>(0,0);
	}
	
	
	public void updateInventoryPosition(int row, int col){
		this.inventoryPosition.l = row;
		this.inventoryPosition.r = col;
	}
	public void updateNumCopies(int amount){
		int sum = this.numCopies + amount; 
		this.numCopies = (sum > 0) ? sum : 0;
	}
	public int getNumCopies(){
		return this.numCopies;
	}
	public String getName(){
		return this.name;
	}
	public int getAmountOfEffectTime(){
		return this.amountOfEffectTime;
	}
	public int getGoldValue(){
		return this.goldValue;
	}
	public Tuple<Integer,Integer> getInventoryPosition(){
		return this.inventoryPosition;
	}
	/*comparator between consumables*/
	public boolean equals(Consumable c){
		return this.name.equals(c.getName());
	}
	/*to be overridden*/
	public void applyConsumableEffects(CharacterProfile profile){
		System.err.println("method applyConsumableEffects need override!");
	}
	

	public static void main(String[] args) {
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
		    	if(i < 12){
		    		System.out.println(i + " , " + hp.totalHeal() + " " + hp.remainingTime() + " , " + mp.totalManaRegened() + " " + mp.remainingTime());
		    	}
		    	else{
		    		System.out.println(i);
		    	}
		    	i++;
		    }
		 }, 1000, 1000);
	}
}
