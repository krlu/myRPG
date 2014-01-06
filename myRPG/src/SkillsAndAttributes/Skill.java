package SkillsAndAttributes;

import RPGelements.CharacterProfile;
import RPGelements.DamageCalculator;
import RPGelements.Dwarf;
import RPGelements.Human;

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
	protected boolean skillShot;
	protected boolean effectRadius;
	protected ArrayList<CharacterProfile> targets;
	
	public Skill() {
	}
	
	/*CDR measured in percentage (0,1)*/
	public void updateCoolDown(double CDR){
		double finalCD = this.coolDown * (1.0 - CDR);
		this.coolDown = finalCD;
	}
	public void updateSkillPoints(int points, CharacterProfile profile){
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
	/*targets size is bounded by number of maxTargets*/
	public int applyEffect(CharacterProfile profile, ArrayList<CharacterProfile> targets){
		// TODO: targets != null is ad-hoc check!! Will remove later!!!
		this.targets = targets;
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
		me.updateBonusMagic(50);
		me.updateBonusMagic(100);
		me.updateBonusMagic(140);
		me.updateBonusMagic(160);
		target.updateMaxHp(3000);
		target.updateHp(600);
		target.updateMagicResist(83);
		Skill GI = new GaiaIgnius();
		GI.updateSkillPoints(4, me);
		GI.applyEffect(me, targets);
		System.out.println(target.getMaxHp());
		System.out.println(target.getCurrentHp());
		System.out.println("Gaia Ignius: "+ target.totalEffectiveDamageReceived());
		target.resetDamageReceived();
		Skill arcaneFire = new ArcaneFire();
		arcaneFire.updateSkillPoints(4,me);
		arcaneFire.applyEffect(me, targets);
		System.out.println("Arcane Fire: " + target.totalEffectiveDamageReceived());
	}
	public static void testScenario1(){
		CharacterProfile me = new Dwarf("Kenny", "August", 30, 1991, "merchant", "");	
		CharacterProfile target = new Human("Kenny", "August", 30, 1991, "merchant", "");
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
		System.out.println(DamageCalculator.effectiveDamage(arcaneFire.applyEffect(me, targets), 0) + " , " + empoweredStrike.applyEffect(me, targets) + " , " + vitality.applyEffect(me, targets));
		
	}
}

