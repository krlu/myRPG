package RPGelements;
import java.util.ArrayList;
import FundamentalStructures.Tuple;
import RPGItemTree.Item;

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
	private String name;
	private String month; 
	private int date; 
	private int year; 
	
	/* general stats*/
	private int exp;
	private int totalExp;
	protected int skillPoints;
	private int level;    /*0 - 30*/
	private String faction; /*4 major factions: Zaft, Kami, Follen, Xan-Ishrin*/
	private String profession;  /* thief, spy, vassal, merchant, mercenary, sovereign, official, viceroy, architect*/
	private String race;  /*elf, dwarf, human, liche, azealin */
	protected ArrayList<String> characterclasses; /*Warrior, Mage, Assassin, Marksman, Priest*/
	protected ArrayList<String> skills;	/*maximum of 5 skills*/
	protected ArrayList<Item> equipedItems; /* can equip max of 6 items*/
	protected Tuple<Integer,Integer> coordinatePosition; /*<x coordinate, y coordinate>*/
	protected int gold;
	
	
	/* combat stats */
	protected double coolDownReduction; 
	protected double lifeSteal; 
	protected double spellVamp; 
	protected int maxhp;
	protected int hp;
	protected int attackDamage; 
	protected int attackCounter; 
	protected double attackSpeed; 
	protected int attackRange; 
	protected int moveSpeed;
	protected int armor; 
	protected int magicResistance;
	protected int fervor;  /* crowd control reduction stat*/
	protected int mana;  
	protected int bonusMagic;
	
	/*over time stats in terms of per-second*/
	protected int manaRegen;
	protected int gatherSpeed; 
	protected int buildSpeed;
	protected int healthRegen; // per 5 seconds 
	protected double luck;
	
	public CharacterProfile(String name, String month, int date, int year, String race, String profession, String faction){
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
		this.equipedItems = new ArrayList<Item>(MAXITEMS);
		this.skills = new ArrayList<String>(MAXSKILLS);
		this.profession = (profession != null  && !profession.equals("")) ? profession : "Unemployed"; 
		this.faction = (profession != null  && !profession.equals("")) ? profession : "Unaffiliated"; 
	}
	
	/* **********************************************
	 * setters and update methods.Have subsections 
	 * for different fields, as some will be updated 
	 * more frequently and differently than others
	 * *********************************************/
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
	
	/* *****************************************************
	 * These are the most frequently updated fields, due to 
	 * the prevalence of equip items debuffs, and various 
	 * abilities. Buying/Selling changes gold frequently too
	 * TODO: there will be many more to implement
	 *******************************************************/
	public void updateAttackSpeed(double bonus){
		double sum = this.attackSpeed * (1 + bonus);
		this.attackSpeed = (sum > 0)? sum : 0;
	}
	public void updateMaxHp(int heal){
		int sum = this.hp + heal;
		this.maxhp = (sum > 0) ? sum : 0;
	}
	public void updateHp(int bonus){
		int sum = this.hp + bonus;
		this.hp = (sum > this.hp) ? this.maxhp : sum;
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
		int sum = this.mana+ bonus;
		this.mana = (sum > 0) ?  sum : 0;
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
	public void addToEquipedItems(Item item){
		if(this.equipedItems.size() > EquipItemThreshold){
			System.err.println("at equipment threshold, cannot equip more items!!");
		}
		else{
			this.equipedItems.add(item);
		}
	}
	public void removeFromEquipedItems(Item item){
		if(this.equipedItems.contains(item)){
			this.equipedItems.remove(item);
		}
		else{
			System.err.println("item not found!");
		}
	}
	
	/*getters*/
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
	/* movement, ability usage, basic attack methods*/
	public void applyLifeSteal(){
		int sum = this.hp + (int) (this.attackDamage * this.lifeSteal);
		this.hp = (sum > this.hp) ? this.maxhp : sum;
	}
	public void applySpellVamp(int damage){
		int sum = this.hp + (int) (damage * this.spellVamp);
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
	// TODO: basicAttack does nothing yet!!
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












