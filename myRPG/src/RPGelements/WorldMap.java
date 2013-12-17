package RPGelements;
import FundamentalStructures.Tuple;


/**************************************************
 * A class devoted to the rendering and processing 
 * of a information on the game map. Keeps track of 
 * a character's position on the map while display 
 * different types of terrain.
 **************************************************/

public class WorldMap {
	
	protected int[][] grid;
	protected String[][] mapData;
	protected String terrain; /*mountain, hill, water, grass, forest, sand*/
	/*returns position of input user 
	 */
	private boolean notOutOfBounds(int[][] grid, int row, int col) {
		return (0 <= row && row < grid.length && 0 < col && col < grid[0].length);
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
}
