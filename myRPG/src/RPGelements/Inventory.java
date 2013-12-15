package RPGelements;

/**************************************************************
 * This class serves to book-keep all items acquired and stored 
 * by a particular player. Right now the association between 
 * player and inventory is given by a CharacterProfile field 
 * within this class. 
 * TODO: setup relational database for inventory and profile
 *************************************************************/
public class Inventory {

	// private int characterId /*so we know whose inventory this is */
	protected String[][] smallItems;
	protected String[][] largeItems;
	protected CharacterProfile user; 

	public Inventory(CharacterProfile user) {
		this.user = user;
		smallItems = new String[1][10];
		largeItems = new String[1][3];
	}
	
	/* Simple helper method for the acquiring and removing of items */
	private boolean notOutOfBounds(String[][] grid, int row, int col) {
		return (0 <= row && row < grid.length && 0 < col && col < grid[0].length);
	}

	public void addSmallItem(String item, int row, int col) {
		if (notOutOfBounds(this.smallItems, row, col)) {
			if (this.smallItems[row][col] == null) {
				this.smallItems[row][col] = item;
			} else {
				System.out.println("move this to another slot please!");
			}
		} else {
			System.err.println("Out of bounds for Small Item");
		}
	}
	
	/* ***********************************************
	 * Generic add and remove methods
	 * implemented for both small and large item slots
	 *************************************************/
	public void removeSmallItem(String item, int row, int col) {
		if (notOutOfBounds(this.smallItems, row, col)) {
			if (this.smallItems[row][col] != null) {
				this.smallItems[row][col] = null;
			} else {
				System.out.println("nothing to remove!");
			}
		} else {
			System.err.println("Out of bounds for Small Item");
		}
	}

	public void addLargeItem(String item, int row, int col) {
		if (notOutOfBounds(this.largeItems, row, col)) {
			if (this.largeItems[row][col] == null) {
				this.largeItems[row][col] = item;
			} else {
				System.out.println("move this to another slot please!");
			}
		} else {
			System.err.println("Out of bounds for Large Item");
		}
	}
	public void removeLargeItem(String item, int row, int col) {
		if (notOutOfBounds(this.largeItems, row, col)) {
			if (this.largeItems[row][col] != null) {
				this.largeItems[row][col] = null;
			} else {
				System.out.println("nothing to remove!");
			}
		} else {
			System.err.println("Out of bounds for large Item");
		}
	}
	public void expandSmallItemSlot(int rows, int cols){
		String[][] newSmallItemSlots = new String[rows][cols];
		if(rows <= 0 || cols <= 0){
			System.err.println("Row or Col is negative value");
		}
		else{
			copyOver(this.smallItems, newSmallItemSlots);
			this.smallItems = newSmallItemSlots;
		}
	}
	public void expandLargeItemSlot(int rows, int cols){
		String[][] newlargeItemSlots = new String[rows][cols];
		if(rows <= 0 || cols <= 0){
			System.err.println("Rows or Cols is negative value");
		}
		else{
			copyOver(this.largeItems, newlargeItemSlots);
			this.largeItems = newlargeItemSlots;
		}
	}
	public void copyOver(String[][] grid1, String[][] grid2){
		for(int i=0 ; i < grid1.length ; i++){
			for(int j=0 ; j<grid1[0].length ; j++){
				grid2[i][j] = grid1[i][j]; // hooray immutablility!!
			}
		}
	}
}



















