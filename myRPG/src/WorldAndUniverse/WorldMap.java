package WorldAndUniverse;
import FundamentalStructures.Tuple;
import RPGelements.CharacterProfile;


/**************************************************
 * A class devoted to the rendering and processing 
 * of a information on the game map. Keeps track of 
 * a character's position on the map while display 
 * different types of terrain.
 **************************************************/

public class WorldMap {
	protected CharacterProfile[][] unitsOnMap; // keeps track of all players on map
	protected int[][] grid;
	protected String[][] terrain; /*Mountain, Hill, Water, Grass, Forest, Sand, various types of buildings*/
	public static final String WATER = "Water";
	public static final String HILL = "Hill";	
	public static final String SAND = "Sand";

	public WorldMap(){
	}
	
	public static boolean notOutOfBounds(int[][] grid, int x, int y) {
		return (0 <= x && x < grid.length && 0 < y && y < grid[0].length);
	}
	
	/* ***********************************
	 * Loads the position of player on map
	 * Called again to updates position 
	 * as player moves. Returns null if 
	 * out of bounds. Form is (x,y) 
	 *************************************/
	public Tuple<Integer,Integer>loadCharacterPosition(CharacterProfile profile){
		int x = profile.getCoordinatePosition().l;
		int y = profile.getCoordinatePosition().r;
		if(notOutOfBounds(this.grid, x, y) && unitsOnMap[y][x] == null){
			unitsOnMap[y][x] = profile;
			return profile.getCoordinatePosition();
		}
		return null;
	}

	/* *******************************
	 * Loads the map when game starts
	 * TODO: doesn't do anything yet!!
	 *********************************/
	public void loadMap(String[][] mapData){		
	}
	
	/*getters*/
	public int[][] getGrid(){
		return this.grid;
	}
	public CharacterProfile getUnitOnMap(int x, int y){
		return this.unitsOnMap[y][x];
	}
	/* ***********************************************
	 * every terrain has a basic effect on each player
	 * depending on race, class, items, and skills 
	 * different terrain can also proc other effects 
	 ************************************************/
	public void applyTerrainEffect(CharacterProfile profile){
		
	}
}
