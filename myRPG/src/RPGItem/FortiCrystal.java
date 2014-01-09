package RPGItem;

import java.util.ArrayList;

import RPGelements.CharacterProfile;

public class FortiCrystal extends Item {
	private int bonusHp;
	public FortiCrystal() {
		this.bonusHp = 25;
		this.equiped = false;
		this.goldValue = 40;
		this.totalCost = 40;
		this.upgradeCost = 0;
		this.buildsTo = new ArrayList<Item>();
		this.buildsFrom = new ArrayList<String>();
		this.buildsTo.add(new WardensGem());
		this.name = "FortiCrystal";
	}
	@Override
	public void applyItemEffects(CharacterProfile profile){;
		profile.updateMaxHp(this.bonusHp);
		profile.updateHp(this.bonusHp);
	}
	@Override
	public void removeItemEffects(CharacterProfile profile){
		profile.updateMaxHp(-this.bonusHp);
		profile.updateHp(-this.bonusHp);
	}
	
	public static void main(String[] args) {
	}

}
