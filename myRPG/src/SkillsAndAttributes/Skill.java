package SkillsAndAttributes;

import RPGelements.CharacterProfile;

public class Skill {
	protected double coolDown; /*measured in seconds*/
	protected int skillPoints;
	protected int castRange;
	protected int manaCost;
	protected String name;
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
			this.skillPoints = (sum > 0) ? 0 : sum; 
		}
	}
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
}
