package StatusEffect;
import RPGelements.CharacterProfile;
/*
 * Basic class for stuns, roots, taunts, fears, slows, speedups...etc.
 * 
 */
public class StatusEffect {
	
	protected double effectTime; 
	protected String name;
	public StatusEffect(double effectTime){
		this.effectTime = effectTime;
	}
	
	public void applyEffect(CharacterProfile target){
		if(effectTime > 0){
			applyEffectHelper(target);
		}
		else if (effectTime == 0){
			removeEffects(target);
		}
		else{
			System.err.println("time is NEGATIVE VALUE!!");
		}
	}
	public String getName(){
		return this.name;
	}
	// to be overridden in subclasses
	public void removeEffects(CharacterProfile target){
		System.err.println("needs override!!");
	}
	public void applyEffectHelper(CharacterProfile target){
		System.err.println("needs override!!");
	}
	
	public void updateEffectTime(){
		if(this.effectTime > 0){
			this.effectTime--;
		}
		else{
			System.out.println("effect has worn out!");
		}
	}
	public double remainingEffectTime(){
		return this.effectTime;
	}
}
