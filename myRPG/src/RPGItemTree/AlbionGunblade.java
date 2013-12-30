package RPGItemTree;

import java.util.ArrayList;

import RPGelements.CharacterProfile;

public class AlbionGunblade extends Item {
	
	private double attackSpeedBonus;
	private int bonusMagic;
	private int manaBonus;
	private int attackBonus; 
	public AlbionGunblade() {
		this.equiped = false;
		this.attackSpeedBonus = 0.20;
		this.bonusMagic = 20;
		this.manaBonus = 20;
		this.attackBonus = 10;
		
		this.goldValue = 0;
		this.totalCost = 470;
		this.upgradeCost = 85;
		this.buildsTo = new ArrayList<Item>();
		this.buildsFrom = new ArrayList<String>();
		this.buildsFrom.add("AlbionRifle");
		this.buildsFrom.add("EmeraldSword");
		this.name = "AlbionGunblade";
	}
	@Override
	public void applyItemEffects(CharacterProfile profile){
		profile.updateMana(this.manaBonus);
		profile.updateAttack(this.attackBonus);
		profile.updateAttackSpeed(this.attackSpeedBonus);
		profile.updateBonusMagic(this.bonusMagic);
	}
	@Override
	public void removeItemEffects(CharacterProfile profile){
		profile.updateMana(-1 * this.manaBonus);
		profile.updateAttack(-1*this.attackBonus);
		profile.updateAttackSpeed(-1 * this.attackSpeedBonus);	
		profile.updateBonusMagic(-1 * this.bonusMagic);
	}


}
