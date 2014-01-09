package RPGItem;

import java.util.ArrayList;
import RPGelements.CharacterProfile;

/****************************************
 * Reduces cooldowns of all skills by 10%
 ****************************************/
public class WardensGem extends Item {
	private int bonusHp;
	private double cdr;
	public WardensGem() {
		this.cdr = 0.10;
		this.bonusHp = 30;
		this.equiped = false;
		this.goldValue = 40;
		this.totalCost = 75;
		this.upgradeCost = 35;
		this.buildsTo = new ArrayList<Item>();
		this.buildsFrom = new ArrayList<String>();
		this.buildsFrom.add("FortiCrystal");
		this.name = "FortiCrystal";
	}
	@Override
	public void applyItemEffects(CharacterProfile profile){;
		profile.updateMaxHp(this.bonusHp);
		profile.updateHp(this.bonusHp);
		profile.updateSkillsCoolDownByPercentage(this.cdr);
	}
	@Override
	public void removeItemEffects(CharacterProfile profile){
		double inverseCDR = cdr/(1-cdr);
		profile.updateMaxHp(-this.bonusHp);
		profile.updateHp(-this.bonusHp);
		profile.updateSkillsCoolDownByPercentage(-inverseCDR);
	}

}
