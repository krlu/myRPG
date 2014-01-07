package WorldAndUniverse;

/***************************************************
 * A map Albion's metropolitan and surrounding areas
 * Terrain and building types represented as strings 
 * TODO: this will take a while to complete....
 */
public class AlbionMap extends WorldMap {
	
	/* ******************************************
	 * various types of buildings within the city
	 * TODO: make available in other cities!!
	 * */
	public static final String CAPTIALBUILDING = "Capital Building";
	public static final String BARRACKS = "Barracks";	
	public static final String BLACKSMITH = "Blacksmith";
	public static final String ARMORY = "Amory";
	public static final String FACTORY = "FACTORY";
	public static final String ARCANELAB = "Arcane Laboratory";
	public static final String SCHOOL = "School";
	public static final String INFIRMARY = "Infirmary";
	public static final String Tree = "Tree";
	public static final String HOUSE = "House";
	public static final String BrickRoad = "BrickRoad";
	
	public AlbionMap(){
		this.terrain = new String[1000][1000];
	}
}
