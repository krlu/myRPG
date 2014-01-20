package StatusEffect;
import java.util.ArrayList;
import RPGelements.CharacterProfile;
/*
 * Basic class for stuns, roots, taunts, fears, slows, speedups...etc.
 * 
 */
public class StatusEffect {
	
	protected int effectTime; 
	
	public StatusEffect(){
		
	}
	
	public void applyEffect(CharacterProfile target){
		if(effectTime > 0){
			applyEffectHelper();
		}
	}
	
	// to be overridden in subclasses
	public void applyEffectHelper(){
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
	public int remainingEffectTime(){
		return this.effectTime;
	}
}
