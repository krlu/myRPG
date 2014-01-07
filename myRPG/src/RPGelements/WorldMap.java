package RPGelements;
import FundamentalStructures.Tuple;


/**************************************************
 * A class devoted to the rendering and processing 
 * of a information on the game map. Keeps track of 
 * a character's position on the map while display 
 * different types of terrain.
 **************************************************/

public class WorldMap {
	protected CharacterProfile[][] unitsOnMap; // keeps track of all players on map
	protected int[][] grid;
	protected String[][] mapData;
	protected String[][] terrain; /*Mountain, Hill, Water, Grass, Forest, Sand*/
	/*returns position of input user 
	 */
	public static boolean notOutOfBounds(int[][] grid, int x, int y) {
		return (0 <= x && x < grid.length && 0 < y && y < grid[0].length);
	}
	public Tuple<Integer,Integer>getPosition(CharacterProfile profile){
		if(notOutOfBounds(this.grid,profile.coordinatePosition.r, profile.coordinatePosition.l)){
			return profile.coordinatePosition;
		}
		return null;
	}

	/* *******************************
	 * Loads the map when game starts
	 * TODO: doesn't do anything yet!!
	 *********************************/
	public void loadMap(String[][] mapData){
		
	}
	public int[][] getGrid(){
		return this.grid;
	}
	public CharacterProfile getUnitOnMap(int x, int y){
		return this.unitsOnMap[y][x];
	}
}
