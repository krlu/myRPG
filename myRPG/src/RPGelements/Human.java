package RPGelements;

public class Human extends CharacterProfile{
	
	public Human(String name, String month, int date, int year, String race,
			String profession, String faction) {
		super(name, month, date, year, race, profession, faction);
	}
	
	/* TODO: list of statistical bonuses humans get 
	 * TODO: list of unique abilities humans get
	 *  
	 */
	public void applyHumanBonuses(){
		
	}
	public static void main(String[] args) {
		Human me = new Human("Kenny", "August", 30, 1991, "human", "merchant", "");			
		System.out.println(me.getName() + "\n" + me.getDOB());
	}

}
