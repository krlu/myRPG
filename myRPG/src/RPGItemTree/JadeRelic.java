package RPGItemTree;

import java.util.ArrayList;

import RPGelements.CharacterProfile;

public class JadeRelic extends Item{

	private int bonusMagic; 
	public JadeRelic() {
		this.equiped = false;
		this.bonusMagic = 10;
		this.goldValue = 50;
		this.totalCost = 0;
		this.upgradeCost = 0;
		this.buildsTo = new ArrayList<Item>();
		this.buildsFrom = new ArrayList<String>();
		this.buildsTo.add(new AlbionRifle());
		this.name = "JadeRelic";
	}
	@Override
	public void applyItemEffects(CharacterProfile profile){;
		profile.updateBonusMagic(this.bonusMagic);
	}
	@Override
	public void removeItemEffects(CharacterProfile profile){
		profile.updateBonusMagic(-1 * this.bonusMagic);
	}

}
