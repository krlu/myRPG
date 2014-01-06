package SkillsAndAttributes;

import RPGelements.CharacterProfile;
import RPGelements.Dwarf;

import java.util.ArrayList;

public class Skill {	
	protected String name;
	protected double coolDown; /*measured in seconds*/
	protected int skillPoints;
	protected int castRange;
	protected int manaCost;
	protected int level1Cap; 
	protected int level2Cap; 
	protected int level3Cap; 
	protected int level4Cap; 
	protected int level5Cap; 

	/*TODO: all abilities are single target right now!!*/
	protected int maxTargets;
	protected boolean skillShot;
	protected boolean effectRadius;
	
	/*for single target, list will be size 1*/
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
		if( targets != null && targets.size() > this.maxTargets){
			System.err.println("TOO MANY TARGETS!!");
			return 0;
		}
		
		this.targets = targets;
		if(this.skillPoints == this.level1Cap){
			return level1Effect(profile);
		}
		else if(this.skillPoints == this.level2Cap){
			return level2Effect(profile);
		}
		else if(this.skillPoints == this.level3Cap){
			return level3Effect(profile);
		}		
		else if(this.skillPoints == this.level4Cap){
			return level4Effect(profile);
		}
		else if(this.skillPoints == this.level5Cap){
			return level5Effect(profile);
		}
		else{
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
	public int level1Effect(CharacterProfile profile){
		unimplementedPrint();
		return 0;
	}	
	public int level2Effect(CharacterProfile profile){
		unimplementedPrint();
		return 0;
	}
	public int level3Effect(CharacterProfile profile){
		unimplementedPrint();
		return 0;
	}
	public int level4Effect(CharacterProfile profile){
		unimplementedPrint();
		return 0;
	}
	public int level5Effect(CharacterProfile profile){
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
		CharacterProfile me = new Dwarf("Kenny", "August", 30, 1991, "merchant", "");	
		me.setLevel(25);
		me.updateBonusMagic(10);
		me.updateAttack(6);
		Skill arcaneFire = new ArcaneFire();
		Skill vitality = new Vitality();
		Skill empoweredStrike = new EmpoweredStrike();
		
		System.out.println(arcaneFire.applyEffect(me,null) + " , " + empoweredStrike.applyEffect(me,null) + " , " + vitality.applyEffect(me,null));
		
		me.updateBonusMagic(50);
		me.updateAttack(30);
		arcaneFire.updateSkillPoints(1,me);
		vitality.updateSkillPoints(1,me);
		empoweredStrike.updateSkillPoints(1,me);
		System.out.println(arcaneFire.applyEffect(me,null) + " , " + empoweredStrike.applyEffect(me,null) + " , " + vitality.applyEffect(me,null));
		
		me.updateBonusMagic(100);
		me.updateAttack(54);
		arcaneFire.updateSkillPoints(1,me);
		vitality.updateSkillPoints(1,me);
		empoweredStrike.updateSkillPoints(1,me);
		System.out.println(arcaneFire.applyEffect(me, null) + " , " + empoweredStrike.applyEffect(me,null) + " , " + vitality.applyEffect(me, null));
		
		me.updateBonusMagic(140);
		me.updateAttack(60);
		arcaneFire.updateSkillPoints(1,me);
		vitality.updateSkillPoints(1,me);
		empoweredStrike.updateSkillPoints(1,me);
		System.out.println(arcaneFire.applyEffect(me, null) + " , " + empoweredStrike.applyEffect(me, null) + " , " + vitality.applyEffect(me, null));
		
		me.updateBonusMagic(160);
		me.updateAttack(80);
		arcaneFire.updateSkillPoints(1,me);
		vitality.updateSkillPoints(1,me);
		empoweredStrike.updateSkillPoints(1,me);
		System.out.println(arcaneFire.applyEffect(me, null) + " , " + empoweredStrike.applyEffect(me, null) + " , " + vitality.applyEffect(me, null));
	
	}
}

