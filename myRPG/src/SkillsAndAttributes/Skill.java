package SkillsAndAttributes;

import RPGelements.CharacterProfile;
import RPGelements.DamageCalculator;
import RPGelements.Dwarf;
import RPGelements.Human;
import WorldAndUniverse.WorldMap;

import java.util.ArrayList;

public class Skill {
	protected final int MAGIC = 0;
	protected final int PHYSICAL = 1;
	protected final int TRUE = 2;
	protected final int NONDAMAGE = -1;
	
	protected String name;
	protected double coolDown; /*measured in seconds*/
	protected int skillPoints;
	protected int damageType;
	protected int castRange;
	protected int manaCost;
	protected int level1Cap; 
	protected int level2Cap; 
	protected int level3Cap; 
	protected int level4Cap; 
	protected int level5Cap; 
	
	protected int maxTargets;
	protected int effectRadius;
	protected boolean skillShot;
	
	public Skill() {
	}
	
	/*CDR measured in percentage (0,1)*/
	public void updateCoolDown(double CDR){
		double finalCD = this.coolDown - CDR;	
		this.coolDown = (finalCD > 0)? finalCD : 0;
	}
	public void updateSkillPoints(int points, CharacterProfile profile){
		if(points == 0){
			System.err.println("requires non-zero input for updateskillpoints!");
			return;
		}
		if(!requirementsBeforeSkillPoints(profile)){
			System.out.println("not enough levels");
			return;
		}
		if(points < -(this.skillPoints)){
			System.err.println("negative skill points!");
			return ;
		}else{
			int sum = this.skillPoints + points;
			this.skillPoints = (sum > 0) ?  sum : 0; 
		}		
		if(this.skillPoints == this.level2Cap){
			this.attainingLevel2();
		}
		if(this.skillPoints == this.level3Cap){
			this.attainingLevel3();
		}
		if(this.skillPoints == this.level4Cap){
			this.attainingLevel4();
		}
		if(this.skillPoints == this.level5Cap){
			this.attainingLevel5();
		}		
	}
	
	/* ************************************************
	 * For all area of effects and skill shot abilities
	 * instead targets a square on the world-map, effect
	 * simply spreads to surrounding squares depending 
	 * on effect radius, takes in target location and map
	 * as parameters in addition to the user profile
	 ****************************************************/
	public void applyNonTargetedEffect(CharacterProfile profile, WorldMap map, int xcoor, int ycoor){
		int top = ycoor + this.effectRadius;
		int bottom = ycoor + this.effectRadius; // row
		int left = xcoor - this.effectRadius;
		int right = xcoor + this.effectRadius; // col
		for(int x=left; x < right; x++){
			for(int y = bottom; y < top; y++){
				if(WorldMap.notOutOfBounds(map.getGrid(), x, y)){
					CharacterProfile target = map.getUnitOnMap(x, y);
					if(target != null){
						applyEffectHelper(profile, target);
					}
				}
			}
		}
	}
	
	/* *************************************************
	 * targets size is bounded by number of maxTargets
	 * returns amount of damage dealt to last target 
	 * TODO: might make method void, unused return value
	 ***************************************************/
	public int applyEffect(CharacterProfile profile, ArrayList<CharacterProfile> targets){
		int damage = 0;
		if( targets != null && targets.size() > this.maxTargets){
			System.err.println("TOO MANY TARGETS!!");
			return 0;
		}	
		for(CharacterProfile target : targets){
		    damage = applyEffectHelper(profile, target);
		}
		return damage;
	}
	/*applies the effects of a skill to 1 target*/
	public int applyEffectHelper(CharacterProfile profile, CharacterProfile target){
		
		if(this.skillPoints == this.level1Cap){
			return level1Effect(profile, target);
		}
		else if(this.skillPoints == this.level2Cap){
			return level2Effect(profile, target);
		}
		else if(this.skillPoints == this.level3Cap){
			return level3Effect(profile, target);
		}		
		else if(this.skillPoints == this.level4Cap){
			return level4Effect(profile, target);
		}
		else if(this.skillPoints == this.level5Cap){
			return level5Effect(profile, target);
		}
		else{
			System.err.println("error in skill points!!");
			return 0;
		}
	}
	
	/*getters*/
	public double getCoolDown(){
		return this.coolDown;
	}
	public int getRequiredSkillPoints(){
		return this.skillPoints;
	}
	public int getCastRange(){
		return this.castRange;
	}
	public int getManaCost(){
		return this.manaCost;
	}
	public String getName(){
		return this.name;
	}
	
	/* all methods below are overridden in sub-classes.*/
	public boolean requirementsBeforeSkillPoints(CharacterProfile profile){
		unimplementedPrint();
		return false;
	}
	private void applyDebuffs(ArrayList<CharacterProfile> targets){
		unimplementedPrint();
	}
	public int level1Effect(CharacterProfile profile, CharacterProfile target){
		unimplementedPrint();
		return 0;
	}	
	public int level2Effect(CharacterProfile profile, CharacterProfile target){
		unimplementedPrint();
		return 0;
	}
	public int level3Effect(CharacterProfile profile, CharacterProfile target){
		unimplementedPrint();
		return 0;
	}
	public int level4Effect(CharacterProfile profile, CharacterProfile target){
		unimplementedPrint();
		return 0;
	}
	public int level5Effect(CharacterProfile profile, CharacterProfile target){
		unimplementedPrint();
		return 0;
	}
	public void attainingLevel2(){
		unimplementedPrint();
	}
	public void attainingLevel3(){	
		unimplementedPrint();
	}
	public void attainingLevel4(){
		unimplementedPrint();
	}
	public void attainingLevel5(){
		unimplementedPrint();
	}
	private void unimplementedPrint(){
		System.err.println("method override needed!");
	}
	
	
	/*******************************
	 * Test center for all skills!!!
	 *******************************/
	public static void main(String [] args){
		testScenario2();
	}
	public static void testScenario2(){
		CharacterProfile me = new Dwarf("Kenny", "August", 30, 1991, "merchant", "");	
		CharacterProfile target = new Human("Kenny", "August", 30, 1991, "merchant", "");
		ArrayList<CharacterProfile> targets = new ArrayList<CharacterProfile>();
		targets.add(target);
		me.setLevel(25);
		me.updateBonusMagic(10);
		me.updateAttack(6);
		target.updateMaxHp(20);
		target.updateHp(20);
		target.updateMagicResist(4);
		target.updateArmor(4);
		Skill GI = new GaiaIgnius();
		GI.updateSkillPoints(1, me);
		GI.applyEffect(me, targets);
		System.out.println(target.getMaxHp());
		System.out.println(target.getCurrentHp());
		System.out.println("Gaia Ignius: "+ target.totalEffectiveDamageReceived());
		
		target.resetDamageReceived();
		Skill arcaneFire = new ArcaneFire();
	//	arcaneFire.updateSkillPoints(2,me);
		arcaneFire.applyEffect(me, targets);
		System.out.println("Arcane Fire: " + target.totalEffectiveDamageReceived());
		
		target.resetDamageReceived();
		Skill empoweredStrike = new EmpoweredStrike();
	//	empoweredStrike.updateSkillPoints(2,me);
		empoweredStrike.applyEffect(me,targets);
		System.out.println("Empowered Strike: " + target.totalEffectiveDamageReceived());
	}
	public static void testScenario1(){
		CharacterProfile me = new Dwarf("Kenny", "August", 30, 1991, "merchant", "");	
		CharacterProfile target = new Human("Kenny", "August", 30, 1991, "merchant", "");
		System.out.println(target.getArmor());
		ArrayList<CharacterProfile> targets = new ArrayList<CharacterProfile>();
		targets.add(target);
		me.setLevel(25);
		me.updateBonusMagic(10);
		me.updateAttack(6);
		Skill arcaneFire = new ArcaneFire();
		Skill vitality = new Vitality();
		Skill empoweredStrike = new EmpoweredStrike();
		System.out.println(arcaneFire.applyEffect(me,targets) + " , " + empoweredStrike.applyEffect(me,targets) + " , " + vitality.applyEffect(me,targets));
		
		me.updateBonusMagic(50);
		me.updateAttack(30);
		arcaneFire.updateSkillPoints(1,me);
		vitality.updateSkillPoints(1,me);
		empoweredStrike.updateSkillPoints(1,me);
		System.out.println(arcaneFire.applyEffect(me,targets) + " , " + empoweredStrike.applyEffect(me,targets) + " , " + vitality.applyEffect(me,targets));
		
		me.updateBonusMagic(100);
		me.updateAttack(54);
		arcaneFire.updateSkillPoints(1,me);
		vitality.updateSkillPoints(1,me);
		empoweredStrike.updateSkillPoints(1,me);
		System.out.println(arcaneFire.applyEffect(me, targets) + " , " + empoweredStrike.applyEffect(me,targets) + " , " + vitality.applyEffect(me, targets));
		
		me.updateBonusMagic(140);
		me.updateAttack(60);
		arcaneFire.updateSkillPoints(1,me);
		vitality.updateSkillPoints(1,me);
		empoweredStrike.updateSkillPoints(1,me);
		System.out.println(arcaneFire.applyEffect(me, targets) + " , " + empoweredStrike.applyEffect(me, targets) + " , " + vitality.applyEffect(me, targets));
		
		me.updateBonusMagic(160);
		me.updateAttack(80);
		arcaneFire.updateSkillPoints(1,me);
		vitality.updateSkillPoints(1,me);
		empoweredStrike.updateSkillPoints(1,me);
		System.out.println(DamageCalculator.effectiveDamage(arcaneFire.applyEffect(me, targets), 0) + " , " + DamageCalculator.effectiveDamage(empoweredStrike.applyEffect(me, targets),0 ) + " , " + vitality.applyEffect(me, targets));
		
	}
}

