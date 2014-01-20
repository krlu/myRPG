package StatusEffect;
import RPGelements.CharacterProfile;


/************************************************* 
 * Reduces movement speed and attack speed to zero. 
 * Affected target also cannot cast spells for the 
 * duration of the stun.
 *************************************************/
public class Stun extends StatusEffect {
	private final double ZERO = 0.0;
	private double originalAttackSpeed; 
	private int originalMoveSpeed;
	public Stun(int effectTime) {
		super(effectTime);
		this.originalMoveSpeed = 0;
	}
	
	@Override
	public void applyEffectHelper(CharacterProfile target){
		this.originalMoveSpeed = target.getMovementSpeed();
		this.originalAttackSpeed = target.getAttackDamage();
		target.setAttackSpeed(ZERO);
		target.updateMoveSpeed(-this.originalMoveSpeed);	
	}
	@Override
	public void removeEffects(CharacterProfile target){
		target.setAttackSpeed(this.originalAttackSpeed);
		target.updateMoveSpeed(this.originalMoveSpeed);
	}
}
