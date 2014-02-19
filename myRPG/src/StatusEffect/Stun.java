package StatusEffect;
import RPGelements.CharacterProfile;
import SkillsAndAttributes.Skill;


/************************************************* 
 * Reduces movement speed and attack speed to zero. 
 * Affected target also cannot cast spells for the 
 * duration of the stun.
 * Stun is a combination of root and silence and 
 * reduces attack speed to zero.
 *************************************************/
public class Stun extends StatusEffect {
	private final double ZERO = 0.0;
	private double originalAttackSpeed; 
	private int originalMoveSpeed;
	public Stun(double effectTime) {
		super(effectTime);
		this.originalMoveSpeed = 0;
		this.name = "Stun";
	}
	
	@Override
	public void applyEffectHelper(CharacterProfile target){
		this.originalMoveSpeed = target.getMovementSpeed();
		this.originalAttackSpeed = target.getAttackDamage();
		target.setAttackSpeed(ZERO);
		target.updateMoveSpeed(-this.originalMoveSpeed);
		for(Skill s : target.getSkills()){
			s.silence();
		}
	}
	@Override
	public void removeEffects(CharacterProfile target){
		target.setAttackSpeed(this.originalAttackSpeed);
		target.updateMoveSpeed(this.originalMoveSpeed);
		for(Skill s : target.getSkills()){
			s.unsilence();
		}
	}
}
