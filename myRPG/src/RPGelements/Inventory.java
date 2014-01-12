package RPGelements;
import RPGItem.Item;
import ConsumablesAndBuffs.Consumable;
import FundamentalStructures.Tuple;
/**************************************************************
 * This class serves to book-keep all items acquired and stored 
 * by a particular player. Right now the association between 
 * player and inventory is given by a CharacterProfile field 
 * within this class. 
 * TODO: setup relational database for inventory and profile........eventually -_-
 *************************************************************/
public class Inventory {

	// private int characterId /*so we know whose inventory this is */
	protected Consumable[][] consumables; //consumables
	protected Item[][] items;
	protected CharacterProfile user; 

	public Inventory(CharacterProfile user) {
		this.user = user;
		consumables = new Consumable[2][6];
		items = new Item[2][5];
	}
	
	/* consumables can only be used on self */
	public void useConsumable(int row, int col){
		this.consumables[row][col].applyConsumableEffects(this.user);
		removeConsumableFromPosition(row, col);
	}
	public void equipSelectedItem(int row, int col){
		if(!this.items[row][col].isEquipped() && user.addToEquipedItems(this.items[row][col])){
			this.items[row][col].equipItem(user);
		}
	}
	
	/* Simple helper methods for the acquiring and removing of items */
	private boolean notOutOfBounds(Item[][] grid, int row, int col) {
		return (0 <= row && row < grid.length && 0 < col && col < grid[0].length);
	}
	private boolean notOutOfBoundsConsumables(Consumable[][] grid, int row, int col) {
		return (0 <= row && row < grid.length && 0 < col && col < grid[0].length);
	}
	

	/* ******************************************
	 * Generic add and remove methods implemented 
	 * separately for consumables and items
	 ********************************************/
	public boolean addConsumable(Consumable consumable){
		// if already contains copy, try to add to position of copy
		Tuple<Integer,Integer> position = containsConsumable(consumable);
		if(position != null){
			if(addConsumableToPosition(consumable,position.l, position.r)){
				return true;
			}
		}
		//otherwise add to top-left most open position
		for(int i=0; i< this.consumables.length; i++){
			for(int j=0;j < this.consumables[i].length;j++){
				if(addConsumableToPosition(consumable,i,j)){
					return true;
				}
			}
		}
		return false;
	}	
	public Tuple<Integer,Integer> containsConsumable(Consumable c){
		for(int i=0; i< this.consumables.length; i++){
			for(int j=0;j < this.consumables[i].length;j++){
				if(this.consumables[i][j] != null
						&& this.consumables[i][j].equals(c)){
					return new Tuple<Integer,Integer>(i,j);
				}
			}
		}
		return null;
	}
	public boolean addConsumableToPosition(Consumable consumable, int row, int col) {
		if (notOutOfBoundsConsumables(this.consumables, row, col)) {
			Consumable ctemp = this.consumables[row][col];
			if (ctemp == null) {
				this.consumables[row][col] = consumable;
				consumable.updateInventoryPosition(row, col);
				return true;
			} 
			else if(ctemp.equals(consumable) && ctemp.getNumCopies() < Consumable.maxCopies){
				this.consumables[row][col].updateNumCopies(1);
				consumable.updateInventoryPosition(row, col);
				return true;
			}
			else{
				System.out.println("move this to another slot please!");
			}
		} else {
			System.err.println("Out of bounds for consumable");
		}
		return false;
	}
	
	public void removeConsumableFromPosition(int row, int col) {
		if (notOutOfBoundsConsumables(this.consumables, row, col)) {
			Consumable ctemp = this.consumables[row][col];
			if (ctemp == null) {
				System.out.println("nothing to remove!");
			} 
			else if(ctemp.getNumCopies() > 1){
				this.consumables[row][col].updateNumCopies(-1);
			}
			else {
				this.consumables[row][col] = null;
			}
		} else {
			System.err.println("Out of bounds for Small Item");
		}
	}

	
	
	/* *******************************************************
	 * Add and remove methods for items. Note that items are
	 * much simpler to add, since copies don't share positions
	 *********************************************************/
	public boolean addItem(Item item){
		for(int i=0; i< this.items.length; i++){
			for(int j=0;j < this.items[i].length;j++){
				if(addItemToPosition(item,i,j)){
					return true;
				}
			}
		}
		return false;
	}
	public boolean addItemToPosition(Item item, int row, int col) {
		if (notOutOfBounds(this.items, row, col)) {
			if (this.items[row][col] == null) {
				this.items[row][col] = item;
				item.updateInventoryPosition(row, col);
				return true;
			} else {
				System.out.println("move this to another slot please!");
			}
		} else {
			System.err.println("Out of bounds for Large Item");
		}
		return false;
	}
	public boolean removeItemFromPosition(Item item, int row, int col) {
		if (notOutOfBounds(this.items, row, col)) {
			if (this.items[row][col] != null) {
				this.items[row][col] = null;
				return true;
			} else {
				System.out.println("nothing to remove!");
			}
		} else {
			System.err.println("Out of bounds for Item");
		}
		return false;
	}
	
	
	/* **************************************
	 * As you level up your inventory expands 
	 ****************************************/
	public void expandConsumableslot(int rows, int cols){
		Consumable[][] newConsumableslots = new Consumable[rows][cols];
		if(rows <= 0 || cols <= 0){
			System.err.println("Row or Col is negative value");
		}
		else{
			copyOverConsumables(this.consumables, newConsumableslots);
			this.consumables = newConsumableslots;
		}
	}
	public void expandItemSlot(int rows, int cols){
		Item[][] newitemslots = new Item[rows][cols];
		if(rows <= 0 || cols <= 0){
			System.err.println("Rows or Cols is negative value");
		}
		else{
			copyOverItems(this.items, newitemslots);
			this.items = newitemslots;
		}
	}
	public void copyOverItems(Item[][] grid1, Item[][] grid2){
		for(int i=0 ; i < grid1.length ; i++){
			for(int j=0 ; j<grid1[0].length ; j++){
				grid2[i][j] = grid1[i][j]; // hooray immutablility!!
			}
		}
	}
	public void copyOverConsumables(Consumable[][] grid1, Consumable[][] grid2){
		for(int i=0 ; i < grid1.length ; i++){
			for(int j=0 ; j<grid1[0].length ; j++){
				grid2[i][j] = grid1[i][j]; // hooray immutablility!!
			}
		}
	}
}



















