package RPGelements;


public class CharacterProfile{
	
	//private int characterID; /*auto-assign within database*/
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
	/* user profile material*/
	private String name;
	private String month; 
	private int date; 
	private int year; 
	
	/* general stats*/
	private int exp;
	private int totalExp;
	private int level;    /*0 - 30*/
	private String faction; /*4 major factions: Zaft, Kami, Follen, Xan-Ishrin*/
	private String profession;  /* thief, spy, vassal, merchant, mercenary, sovereign, official, viceroy, architect*/
	private String race;  /*elf, dwarf, human, liche */
	private String classes[]; /*Warrior, Mage, Assassin, Archer, Priest*/
	/* combat stats 
	 *   
	 * TODO: base stats will vary based on race and classes
	 * 
	 * */
	protected int hp;
	protected int attackDamage; 
	protected double attackSpeed; 
	protected int attackRange; 
	protected int moveSpeed;
	protected int armor; 
	protected int magicResistance;
	protected int fervor; /* crowd control reduction stat*/
	
	public CharacterProfile(String name, String month, int date, int year, String race, String profession, String faction){
		this.name = name;
		this.month = month;
		this.date = date;
		this.year = year;
		this.race = race; 
		this.profession = (profession != null  && !profession.equals("")) ? profession : "Unemployed"; 
		this.faction = (profession != null  && !profession.equals("")) ? profession : "Unaffiliated"; 
		this.level = 0; 
		this.exp = 0;
		this.totalExp = 0;
	}
	
	/*setters*/

	public void updateExp(int expDiff){
		this.totalExp += expDiff;
		this.exp = Math.max(this.exp + expDiff, 0); 
		
	}
	public void levelUp(){
		if(this.exp >= this.expThresholds[this.level]){
			this.level += 1; 
			this.exp = 0;
		}
	}
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
	
	/*getters*/
	public String getName(){
		return this.name;
	}
	public String getDOB(){
		return this.month + " " + this.date + " " + this.year;
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
	
	
	
	
	
	public static void main(String[] args){
		CharacterProfile me = new CharacterProfile("Kenny", "August", 30, 1991, "human", "merchant", "");			
		me.updateDOB(null, 0,0); 
		System.out.println(me.getDOB()); 
		me.updateDOB("july", 4, 1453); 
		System.out.println(me.getDOB());
	}
}
