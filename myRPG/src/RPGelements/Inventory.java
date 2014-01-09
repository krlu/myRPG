package RPGelements;
import RPGItem.*;

/**************************************************************
 * This class serves to book-keep all items acquired and stored 
 * by a particular player. Right now the association between 
 * player and inventory is given by a CharacterProfile field 
 * within this class. 
 * TODO: setup relational database for inventory and profile........eventually -_-
 *************************************************************/
public class Inventory {

	// private int characterId /*so we know whose inventory this is */
	protected Item[][] consumables; //consumables
	protected Item[][] largeItems;
	protected CharacterProfile user; 

	public Inventory(CharacterProfile user) {
		this.user = user;
		consumables = new Item[2][6];
		largeItems = new Item[2][5];
	}
	
	public void equipSelectedItem(int row, int col){
		if(!this.consumables[row][col].isEquipped() && user.addToEquipedItems(this.consumables[row][col])){
			this.consumables[row][col].equipItem(user);
		}
	}
	
	/* Simple helper method for the acquiring and removing of items */
	private boolean notOutOfBounds(Item[][] grid, int row, int col) {
		return (0 <= row && row < grid.length && 0 < col && col < grid[0].length);
	}

	/* ***********************************************
	 * Generic add and remove methods
	 * implemented for both small and large item slots
	 *************************************************/
	public void addSmallItem(Item item, int row, int col) {
		if (notOutOfBounds(this.consumables, row, col)) {
			if (this.consumables[row][col] == null) {
				this.consumables[row][col] = item;
			} else {
				System.out.println("move this to another slot please!");
			}
		} else {
			System.err.println("Out of bounds for Small Item");
		}
	}
	
	public void removeSmallItem(Item item, int row, int col) {
		if (notOutOfBounds(this.consumables, row, col)) {
			if (this.consumables[row][col] != null) {
				this.consumables[row][col] = null;
			} else {
				System.out.println("nothing to remove!");
			}
		} else {
			System.err.println("Out of bounds for Small Item");
		}
	}

	public void addLargeItem(Item item, int row, int col) {
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
	public void removeLargeItem(Item item, int row, int col) {
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
	public void expandConsumableslot(int rows, int cols){
		Item[][] newConsumableslots = new Item[rows][cols];
		if(rows <= 0 || cols <= 0){
			System.err.println("Row or Col is negative value");
		}
		else{
			copyOver(this.consumables, newConsumableslots);
			this.consumables = newConsumableslots;
		}
	}
	public void expandLargeItemSlot(int rows, int cols){
		Item[][] newlargeItemSlots = new Item[rows][cols];
		if(rows <= 0 || cols <= 0){
			System.err.println("Rows or Cols is negative value");
		}
		else{
			copyOver(this.largeItems, newlargeItemSlots);
			this.largeItems = newlargeItemSlots;
		}
	}
	public void copyOver(Item[][] grid1, Item[][] grid2){
		for(int i=0 ; i < grid1.length ; i++){
			for(int j=0 ; j<grid1[0].length ; j++){
				grid2[i][j] = grid1[i][j]; // hooray immutablility!!
			}
		}
	}
}



















