package SkillsAndAttributes;

import RPGelements.CharacterProfile;
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

	protected boolean tagetedAbility; 
	protected boolean skillShot;
	protected boolean effectRadius;
	
	public Skill() {
	}
	
	/*CDR measured in percentage (0,1)*/
	public void updateCoolDown(double CDR){
		double finalCD = this.coolDown * (1.0 - CDR);
		this.coolDown = finalCD;
	}
	public void updateSkillPoints(int points){
		if(points < -(this.skillPoints)){
			System.err.println("negative skill points!");
			
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
	public int applyEffect(CharacterProfile profile, ArrayList<CharacterProfile> targets){
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
	private void applyDebuffs(ArrayList<CharacterProfile> targets){
		
	}
	public int level1Effect(CharacterProfile profile){
		return 0;
	}	
	public int level2Effect(CharacterProfile profile){
		return 0;
	}
	public int level3Effect(CharacterProfile profile){
		return 0;
	}
	public int level4Effect(CharacterProfile profile){
		return 0;
	}
	public int level5Effect(CharacterProfile profile){
		return 0;
	}
	public void attainingLevel2(){
		
	}
	public void attainingLevel3(){
		
	}
	public void attainingLevel4(){
		
	}
	public void attainingLevel5(){
		
	}
}

