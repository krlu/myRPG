package RPGelements;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

import BasicWorldMap.MovePane.Direction;
import FundamentalStructures.Tuple;
import RPGItem.Item;
import SkillsAndAttributes.Skill;
import StatusEffect.StatusEffect;

public class CharacterProfile{
	
	//private int characterID; /*auto-assign within database*/
	public static final int EquipItemThreshold = 6; 
	private int[] expThresholds = {
			 50 ,	
		 	 90	, 
		 	 98	,  
		 	 105,		
		 	 113,		
		 	 448,	
		 	 476,		
		 	 504,	  
		 	 532,	
		 	 560,	 
		 	 1050,	 
		 	 1100,	 
		 	 1150,	
			 1200,	 
		 	 1250,
		 	 1300,	 
		 	 1350,	 
		 	 1400,	
			 1450,	 
			 1500,	
			 2131,	
		 	 2200,	
		 	 2269,	
		 	 2338,	
			 2406,	 
		 	 2475,	
		 	 2544,	
		 	 2613,	 
		 	 2681,	
			 2750};	 
	private final int MAXEXP = 9999;
	private final int MAXLVL = 30;
	private final int MAXSKILLS = 5;
	private final int MAXITEMS = 6;
	
	/* user profile material*/
	private int date; 
	private String month; 
	private String name;
	private int year; 
	
	/* location and orientation in space*/
	protected Tuple<Integer,Integer> coordinatePosition; /*<x coordinate, y coordinate>*/
	protected Tuple<Double,Double> directionVector;    /*current unit vector at which player is facing*/ 
	protected Direction 			 currentDirection = Direction.None;
	
	/* general stats*/
	protected ArrayList<String> characterclasses; /*Warrior, Mage, Assassin, Marksman, Priest*/	
	protected ArrayList<Item> 	equipedItems; /* can equip max of 6 items*/
	private int 				exp;	
	private String 				faction; /*4 major factions: Zaft, Kami, Follen, Xan-Ishrin*/
	protected int 				gold;
	private int 				level;    /*0 - 30*/
	private String 				profession;  /* thief, spy, vassal, merchant, mercenary, sovereign, official, viceroy, architect*/
	private String 				race;  /*elf, dwarf, human, liche, azealin */	
	protected int 				skillPoints;
	protected ArrayList<Skill> 	skills;	/*maximum of 5 skills*/
	private int 				totalExp;
	
	
	/* basic combat stats */
	protected int 	 armor; 
	protected int 	 attackCounter;
	protected int 	 attackDamage; 
	protected int 	 attackRange;
	protected double attackSpeed; 
	protected int 	 bonusMagic;
	protected double coolDownReduction; 
	protected int 	 fervor;  /* crowd control reduction stat*/
	protected int 	 hp;
	protected double lifeSteal; 	
	protected int 	 maxhp;	 
	protected int 	 moveSpeed;	
	protected int 	 magicResistance;	
	protected int 	 mana; 
	protected int    maxMana;
	protected double spellVamp; 	
	protected ArrayList<StatusEffect> statusEffects;
	
	/*over time stats in terms of per-second*/
	protected int buildSpeed;
	protected int gatherSpeed; 
	protected int healthRegen; // per 5 seconds
	protected int manaRegen;
	
	/*miscellaneous stats*/
	protected double luck;
	
	/*damaged received during combat phase*/
	protected int magicDamageReceived;
	protected int physicalDamageReceived; 
	protected int trueDamageReceived;
	
	
	/*character graphical appearance on UI*/
	public JPanel avatar;
	
	public CharacterProfile(String name, String month, int date, int year, String race, String profession, String faction){
		
		/*stats for character profile*/
		this.coolDownReduction = 0.0;
		this.name = name;
		this.month = month;
		this.date = date;
		this.year = year;
		this.race = race; 
		this.level = 0; 
		this.exp = 0;
		this.totalExp = 0;
		this.luck = 0.000001;
		this.lifeSteal = 0.0;
		this.spellVamp = 0.0;
		this.skillPoints = 0;
		this.attackCounter = 0;
		this.trueDamageReceived = 0;
		this.magicDamageReceived = 0;
		this.physicalDamageReceived = 0;
		this.skills = new ArrayList<Skill>(MAXSKILLS);
		this.equipedItems = new ArrayList<Item>(MAXITEMS);
		this.statusEffects = new ArrayList<StatusEffect>();
		this.faction = (profession != null  && !profession.equals("")) ? profession : "Unaffiliated"; 
		this.profession = (profession != null  && !profession.equals("")) ? profession : "Unemployed"; 
		this.directionVector = new Tuple<Double, Double>(0.0,0.0);
		/* Sets appearance on UI of character profile
		 * TODO: We can make this waaaay better!!!!
		 * */
        JPanel mobby = new JPanel();
        mobby.setBackground(Color.RED); 
        mobby.setSize(50, 50);
        this.avatar = mobby;
	}
	
	/* **********************************************
	 * setters and update methods.Have subsections 
	 * for different fields, as some will be updated 
	 * more frequently and differently than others
	 * *********************************************/
	public void setDirection(double x, double y){
		this.directionVector.l = x;
		this.directionVector.r = y; 
	}
	public Tuple<Double,Double> getDirectionVector(){
		return this.directionVector;
	}
	public void setPosition(int x, int y){
		this.coordinatePosition = new Tuple<Integer, Integer>(x,y);
	}
	public void updateExp(int expDiff){
		this.totalExp += expDiff;
		this.exp = Math.max(this.exp + expDiff, 0); 
		this.exp = Math.min(MAXEXP, this.exp);
	}
	public boolean levelUp(){
		if(this.level >= MAXLVL){
			return false;
		}
		if(this.exp >= this.expThresholds[this.level]){
			this.level += 1; 
			this.exp = 0;
			return true;
		}
		return false;
	}
	
	/* ********************************************** 
	 *  Updates the most basic character fields. 
	 *  These are the least frequently updated fields 
	 *  over the course of a game.
	 ************************************************/
	public void updateProfession(String profession){
		this.profession = (profession != null && !profession.equals("")) ? profession : this.profession;
	}
	public void updateFaction(String faction){
		this.faction = (faction != null && !faction.equals("")) ? faction : this.faction;

	}
	public void updateName(String name){
		this.name = ((name != null) && !name.equals("")) ? name : this.name; 
	}
	public void updateDOB(String month, int date, int year){
		this.month = (month != null && !month.equals(""))? month : this.month; 
		this.date = (date > 0) ? date : this.date; 
		this.year = (year > 0) ? year : this.year;
	}
	// TODO: for testing only!!
	public void setLevel(int level){
		this.level = level;
	}
	/* *****************************************************
	 * These are the most frequently updated fields, due to 
	 * the prevalence of equip items debuffs, and various 
	 * abilities. Buying/Selling changes gold frequently too
	 * TODO: there will be many more to implement
	 *******************************************************/
	public void addSkill(Skill s){
		this.skills.add(s);
	}
	public void updateSkillsCoolDownByPercentage(double cdr){
		for(Skill skill : this.skills){
			double percentage;
			percentage = skill.getCoolDown() * (cdr);
			skill.updateCoolDown(percentage);
		}
	}
	public void setAttackSpeed(double value){
		this.attackSpeed = value;
	}
	public void updateAttackSpeed(double bonus){
		double sum = this.attackSpeed * (1 + bonus);
		this.attackSpeed = (sum > 0)? sum : 0;
	}
	public void updateHp(int bonus){
		int sum = this.hp + bonus;
		if(sum > this.maxhp){ 
			this.hp = this.maxhp;
		}
		else if(sum < 0){
			this.hp = 0;
		}
		else{
			this.hp = sum;
		}
	}
	public void updateMaxHp(int bonus){
		int sum = this.maxhp + bonus;
		this.maxhp = (sum > 0) ? sum : 0;
	}
	public void updateAttack(int bonus){
		int sum = this.attackDamage + bonus;
		this.attackDamage = (sum > 0) ?  sum : 0;
	}
	public void updateBonusMagic(int bonus){
		int sum = this.bonusMagic + bonus;
		this.bonusMagic = (sum > 0) ? sum : 0;
	}
	public void updateLifeSteal(double bonus){
		double sum = this.lifeSteal + bonus;
		this.lifeSteal = (sum > 0.0) ? sum : 0.0 ;
	}
	public void updateSpellVamp(double bonus){
		double sum = this.spellVamp + bonus;
		this.spellVamp = (sum > 0.0) ? sum : 0.0;
	}
	public void updateMana(int bonus){
		int sum = this.mana + bonus;
		if(sum > this.maxMana){
			this.mana = this.maxMana;
			return;
		}
		else if(sum < 0){
			System.err.println("Mana is negative!!!");
		}
		else{
			this.mana = sum;
		}
	}
	public void updateArmor(int bonus){
		int sum = this.armor+ bonus;
		this.armor = (sum > 0) ?  sum : 0;
	}
	public void updateMagicResist(int bonus){
		int sum = this.magicResistance + bonus; 
		this.magicResistance = (sum > 0) ?  sum : 0;
	}
	public void updateCDR(int bonus){
		double sum = this.coolDownReduction + bonus;
		this.coolDownReduction = (sum > 0) ? sum : 0; 
	}
	public void updateMoveSpeed(int bonus){
		int sum = this.moveSpeed + bonus;
		this.moveSpeed = (sum > 0) ? sum : 0; 
	}
	public void updateGold(int amount){
		if(this.gold + amount > 0){
			this.gold += amount;
		}
	}
	/* **************************************************
	 * Compute all status effect from self and outside
	 * Compute all damage received, magic, physical, true 
	 * Armor and Magic Resistance applied here!! 
	 ****************************************************/
	public void addStatusEffect(StatusEffect effect){
		this.statusEffects.add(effect);
	}
	// once effect wears off, remove from list
	public void checkStatusEffects(){
		ArrayList<StatusEffect> tempList = new ArrayList<StatusEffect>();
		for(StatusEffect effect : this.statusEffects){
			if(effect.remainingEffectTime() != 0){
				effect.updateEffectTime();
				tempList.add(effect);
			}
		}
		this.statusEffects = tempList;
	}
	
	public void resetDamageReceived(){
		this.magicDamageReceived = 0;
		this.physicalDamageReceived = 0;
		this.trueDamageReceived = 0;
	}
	public void updateMagicDamageReceived(int damage){
		int sum = this.magicDamageReceived + damage;
		this.magicDamageReceived = (sum > 0) ? sum : 0;
	}
	public void updatePhysicalDamageReceived(int damage){
		int sum = this.physicalDamageReceived + damage;
		this.physicalDamageReceived = (sum > 0) ? sum : 0;
	}
	public void updateTrueDamageReceived(int damage){
		int sum = this.trueDamageReceived + damage;
		this.trueDamageReceived = (sum > 0) ? sum : 0;
	}
	/*added either through leveling or by removing a skill*/
	public void addSkillPoints(int amount){
		if(amount < 0){
			System.err.print("adding negative amount of skillpoints!");
		}
		else{
			this.skillPoints += amount;
		}
	}
	public int useSkillPoints(int amountToUse){
		if(amountToUse > this.skillPoints){
			System.err.println("not enough skillpoints!");
			return 0;
		}
		else{
			this.skillPoints -= amountToUse; 
			return amountToUse;
		}
	}
	// TODO: find better way to equip item!!
	public boolean addToEquipedItems(Item item){
		if(this.equipedItems.size() > EquipItemThreshold){
			System.err.println("at equipment threshold, cannot equip more items!!");
			return false;
		}
		else{
			this.equipedItems.add(item);
			return true;
		}
	}
	// returns the removed item so that you can put it back in your inventory!!
	public Item removeFromEquipedItems(int index){
		Item item = this.equipedItems.get(index);
		if(item != null){
			this.equipedItems.remove(item);
		}
		else{
			System.err.println("item not found!");
		}
		return item;
	}
	
	/* *********
	 * getters
	 ***********/
	public int getMaxHp(){
		return this.maxhp;
	}
	public int getCurrentHp(){
		return this.hp;
	}
	public int getHpRegen(){
		return this.healthRegen;
	}
	public int getMaxMana(){
		return this.maxMana;
	}
	public int getCurrentMana(){
		return this.mana;
	}
	public int getManaRegen(){
		return this.manaRegen;
	}
	public String getName(){
		return this.name;
	}
	public String getDOB(){
		return this.month + " " + this.date + " " + this.year;
	}
	public double getLifeSteal(){
		return this.lifeSteal;
	}
	public double getSpellVamp(){
		return this.spellVamp;
	}
	public double getCDR(){
		return this.coolDownReduction;
	}
	public int getBonusMagic(){
		return this.bonusMagic;
	}
	public int getLevel(){
		return this.level;
	}
	public int getExp(){
		return this.exp;
	}
	public int getThreshold(){
		return this.expThresholds[this.level];
	}
	public String getRace(){
		return this.race;
	}
	public int getTotalExp(){
		return this.totalExp;
	}
	public ArrayList<Item> getEquipedItems(){
		return this.equipedItems;
	}
	public int getGold(){
		return this.gold;
	}
	public int getSkillPoints(){
		return this.skillPoints;
	}
	public int getAttackCounter(){
		return this.attackCounter;
	}
	public int getAttackDamage(){
		return this.attackDamage;
	}
	public int getMovementSpeed(){
		return this.moveSpeed;
	}
	public ArrayList<Skill> getSkills(){
		return this.skills;
	}
	public double getAttackSpeed(){
		return this.attackSpeed;
	}
	public int getAttackRange(){
		return this.attackRange;
	}
	public int getArmor(){
		return this.armor;
	}
	public int getMagicResist(){
		return this.magicResistance;
	}
	public int totalEffectiveDamageReceived(){
		int appliedWithMR = DamageCalculator.effectiveDamage(this.magicDamageReceived, this.magicResistance);
		int appliedWithArmor = DamageCalculator.effectiveDamage(this.physicalDamageReceived, this.armor);
		return this.trueDamageReceived + appliedWithMR + appliedWithArmor; 
	}
	public int magicDamageReceived(){
		return this.magicDamageReceived;
	}
	public int physicalDamageReceived(){
		return this.physicalDamageReceived;
	}
	public int trueDamageReceived(){
		return this.trueDamageReceived;
	}
	public Tuple<Integer, Integer> getCoordinatePosition(){
		return this.coordinatePosition;
	}
	/* **********************************************
	 * movement, ability usage, basic attack methods
	 * TODO: still a lot yet  to implement here!!
	 * *********************************************/
	public void applyLifeSteal(CharacterProfile target){
		int effectiveDamage = DamageCalculator.effectiveDamage(this.attackDamage, target.getArmor());
		int sum = this.hp + (int) (effectiveDamage * this.lifeSteal);
		this.hp = (sum > this.hp) ? this.maxhp : sum;
	}
	public void applySpellVamp(CharacterProfile target){
		int effectiveDamage = DamageCalculator.effectiveDamage(this.magicDamageReceived, target.getMagicResist());
		int sum = this.hp + (int) (effectiveDamage * this.spellVamp);
		this.hp = (sum > this.hp) ? this.maxhp : sum;
	}
	public void moveUp(int distance){
		this.coordinatePosition.r += distance;
	}
	public void moveDown(int distance){
		this.coordinatePosition.r -= distance;
	}
	public void moveLeft(int distance){
		this.coordinatePosition.l += distance;
	}
	public void moveRight(int distance){
		this.coordinatePosition.l -= distance;
	}
	
	
	// TODO: will need to implement future procs from auto-attack
	public int basicAttack(){
		this.attackCounter += 1;
		this.attackCounter = this.attackCounter % 1000;
		return this.attackDamage;
	}
	
	
	
	/***************************
	 * Test bench down here!!
	 * 
	 */
	public static void main(String[] args){
		CharacterProfile me = new CharacterProfile("Kenny", "August", 30, 1991, "human", "merchant", "");			
		me.updateDOB(null, 0,0); 
		System.out.println(me.getDOB()); 
		me.updateDOB("july", 4, 1453); 
		System.out.println(me.getDOB());
	}
	
	public void printEquipedItems(){
		System.out.print("[");
		for(Item item : this.equipedItems){
			System.out.print(" " + item.getName() + "  ");
		}
		System.out.println("]");
	}
}












