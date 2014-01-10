package ConsumablesAndBuffs;
import RPGelements.CharacterProfile;

/* ******************************************************************
 * Consumables behave like items in that they are stored in inventory 
 * and their primary effects do not have a cooldown, but they behave 
 * like skills in that their effects are only temporary and require 
 * activation from the user.
 ********************************************************************/

public class Consumable {
	public static final int maxCopies = 5;
	protected int numCopies;
	protected int goldValue;
	protected String name;
	protected int amountOfEffectTime; // for over time effects
	
	public Consumable() {
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
	/*comparator between consumables*/
	public boolean equals(Consumable c){
		return this.name.equals(c.getName());
	}
	/*to be overridden*/
	public void applyConsumableEffects(CharacterProfile profile){
		System.err.println("method applyConsumableEffects need override!");
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
