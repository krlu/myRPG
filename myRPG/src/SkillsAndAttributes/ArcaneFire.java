package SkillsAndAttributes;
import  RPGelements.CharacterProfile;

/******************************************
 * Basic damage ability with medium range  
 * scales with bonus magic and skill points
 * strong at high levels due to decreased 
 * cool-downs and since mana costs remain 
 * static even as damage amplifies. 
 ******************************************/

public class ArcaneFire extends Skill {

	private double damageRatio;
	public ArcaneFire() {
		this.amountOfEffectTime = 0;
		this.effectRadius = 0;
		this.maxTargets = 2;
		this.damageRatio = 0.5;
		this.skillPoints = 0;
		this.coolDown = 12.0;
		this.castRange = 4;
		this.manaCost = 15;
		this.name = "ArcaneFire";
		this.level1Cap = 0; 
		this.level2Cap = 1;
		this.level3Cap = 2;
		this.level4Cap = 3;
		this.level5Cap = 4;
	}
	/* *************************************************
	 * 1 skill point to level, 
	 * higher level skills requires higher level profile
	 ***************************************************/
	@Override
	public int level1Effect(CharacterProfile profile, CharacterProfile target){
		return effectHelper(10, profile, target);
	}
	@Override
	public int level2Effect(CharacterProfile profile,CharacterProfile target){
		return effectHelper(20, profile,target);
	}
	@Override
	/*requires level 15*/
	public int level3Effect(CharacterProfile profile,CharacterProfile target){
		return effectHelper(35, profile,target);
	}
	@Override
	/*requires level 20*/
	public int level4Effect(CharacterProfile profile,CharacterProfile target){
		return effectHelper(70, profile,target);
	}
	@Override
	/*requires level 25*/
	public int level5Effect(CharacterProfile profile,CharacterProfile target){
		return effectHelper(120, profile,target);
	}
	private int effectHelper(int baseDamage, CharacterProfile profile,CharacterProfile target){
		int damage =  baseDamage + (int)(this.damageRatio * profile.getBonusMagic());
		target.updateMagicDamageReceived(damage);
		return damage;
	}
	
	@Override
	public boolean requirementsBeforeSkillPoints(CharacterProfile profile){
		if(this.skillPoints < this.level2Cap){
			return true;
		}
		else if(this.skillPoints == this.level2Cap){
			return profile.getLevel() >= 15;
		}
		else if(this.skillPoints == this.level3Cap){
			return profile.getLevel() >= 20;
		}
		else if(this.skillPoints == this.level4Cap){
			return profile.getLevel() >= 25;
		}
		else{
			return false;
		}
	}
	/*additional effects upon leveling up*/
	@Override
	public void attainingLevel2(){
		this.manaCost = 20;
	}
	@Override
	public void attainingLevel3(){
		this.coolDown = 11.0;
		this.manaCost = 30;
	}
	@Override
	public void attainingLevel4(){
		this.manaCost = 45;
		this.coolDown = 9.0;
	}
	@Override
	public void attainingLevel5(){
		this.coolDown = 7.0;
	}
	
	public static void main(String [] args){
	}
}
