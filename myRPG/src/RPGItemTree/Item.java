package RPGItemTree;
import java.util.ArrayList;
import RPGelements.CharacterProfile;

public interface Item{
		public void equipItem(CharacterProfile profile);
		public void unequipItem(CharacterProfile profile);
		public void printStats();
		public ArrayList<Item> buildsFrom();
		public ArrayList<Item> buildsInto();
		public int goldValue();
		public int upgradeCost();
		public int totalCost();
}
