package RPGelements;

/*********************************************************************************************
 * This class contains methods for assigning each player the CharacterClass of their choosing
 * Each class has their own strengths and weaknesses with respect to the other 4 classes. 
 * Ideal balance would be for each class to be strong vs 2 and weak vs 2 of the other classes
 * TODO: eventually....check/test balance numbers across classes (along with item builds)
 * TODO: removal methods yet to have practical use, will determine purpose later
 ********************************************************************************************/
public class CharacterAssignment {
	
    /* ********************************************************
     * Class I : Warrior
     * Tank/Bruiser, high utility and moderate base damage
     * Builds: CC, health, resistances, melee damage
     * Strong vs Assassins, Mage. Weak vs Marksman, Priest
     *********************************************************/	
	public static void assignWarriorClass(CharacterProfile profile){
		if(!profile.characterclasses.contains("Warrior")){
			profile.characterclasses.add("Warrior");
			/*assign warrior base stats*/
			profile.armor += 4;
			profile.moveSpeed += 2; 
			profile.hp += 20; 
			profile.attackDamage += 3;
			profile.luck += 0.01;
			profile.fervor += 3;
		}
	}
	/*apply this function every time a CharacterProfile levels up*/
	public static void scaleWarriorStats(CharacterProfile profile){
		if(profile.characterclasses.contains("Warrior")){
			profile.hp += 15;
			profile.attackDamage += 3;
			profile.armor += 3;
			profile.mana += 5;
			profile.magicResistance += 3;
		}
	}
	public static void removeWarriorClass(CharacterProfile profile){
		if(profile.characterclasses.contains("Warrior")){
			/*remove warrior base stats*/
			profile.armor -= 4;
			profile.characterclasses.remove("Warrior");
			profile.moveSpeed -= 2; 
			profile.hp -= 18;
			profile.attackDamage -= 4;
			profile.luck -= 0.01;
			profile.fervor -= 3;
		}
	}
	
	
	/* *******************************************************
	 * Class II: Mage
	 * High magic damage. Burst or DPS. Requires mana
	 * Builds: ranged magic dmg, CC, AOE dmg, stack mana
	 * Strong vs Warrior, Marksman. Weak vs Priest, Assassin
	 ********************************************************/
	public static void assignMageClass(CharacterProfile profile){
		if(!profile.characterclasses.contains("Mage")){
			profile.characterclasses.add("Mage");
			/*assign Mage base stats*/ 
			profile.hp += 8; 
			profile.mana += 20;
			profile.bonusMagic += 5;
			profile.attackRange += 3;
			profile.luck += 0.03;
			profile.fervor += 1;
		}
	}
	/*apply this function every time a CharacterProfile levels up*/
	public static void scaleMageStats(CharacterProfile profile){
		if(profile.characterclasses.contains("Mage")){
			profile.hp += 7;
			profile.attackDamage += 1;
			profile.bonusMagic += 6;
			profile.mana += 10;
			profile.magicResistance += 1;
			profile.armor += 1;
		}
	}
	public static void removeMageClass(CharacterProfile profile){
		if(profile.characterclasses.contains("Mage")){
			/*remove Mage base stats*/ 
			profile.hp -= 8; 
			profile.mana -= 20;
			profile.bonusMagic -= 5;
			profile.attackRange -= 3;
			profile.luck -= 0.03;
			profile.fervor -= 1;
		}
	}	
	
	
	/* ********************************************************
	 * Class III: Assassin
	 * Melee fighter. High burst/mobile physical dmg w/ high AS
	 * Builds: mobility, CC/CC-reduction, melee physical dmg/AS 
	 * Strong vs Mage, Marksman. Weak vs Priest, Warrior 
	 **********************************************************/
	public static void assignAssassinClass(CharacterProfile profile){
		if(!profile.characterclasses.contains("Assassin")){
			profile.characterclasses.add("Assassin");
			/*assign Assassin base stats*/
			profile.moveSpeed += 6; 
			profile.hp += 5; 
			profile.attackDamage += 8;
			profile.attackRange += 1;
			profile.luck += 0.05;
		}
	}
	/*apply this function every time a CharacterProfile levels up*/
	public static void scaleAssassinStats(CharacterProfile profile){
		if(profile.characterclasses.contains("Assassin")){
			profile.hp += 10;
			profile.attackDamage += 5;
			profile.attackSpeed += 0.005;
			profile.mana += 3;
			profile.magicResistance += 1;
			profile.armor += 1;
		}
	}
	public static void removeAssassinClass(CharacterProfile profile){
		if(profile.characterclasses.contains("Assassin")){
			profile.characterclasses.remove("Assassin");
			/*Remove Assassin base stats*/
			profile.moveSpeed -= 6; 
			profile.hp -= 2; 
			profile.attackDamage -= 8;
			profile.attackRange -= 1;
			profile.luck -= 0.05;
		}
	}	
	
	
	/* ****************************************************
	 * Class IV: Priest
	 * Healer, mid-range, high utility, moderate base dmg
	 * Builds:CC, CDR, resistance, aura utility, stack mana 
	 * Strong vs Assassin, Mage. Weak vs Warrior, Marksman
	 ******************************************************/
	public static void assignPriestClass(CharacterProfile profile){
		if(!profile.characterclasses.contains("Priest")){
			profile.characterclasses.add("Priest");
			/*assign Priest base stats*/
			profile.bonusMagic += 2;
			profile.magicResistance += 3;
			profile.mana += 10;
			profile.moveSpeed += 1; 
			profile.hp += 15; 
			profile.attackRange +=2;
			profile.luck += 0.03;
		
		}
	}
	/*apply this function every time a CharacterProfile levels up*/
	public static void scalePriestStats(CharacterProfile profile){
		if(profile.characterclasses.contains("Priest")){
			profile.attackDamage += 1;
			profile.hp += 12;
			profile.bonusMagic += 2;
			profile.mana += 14;
			profile.armor += 2;
			profile.magicResistance += 2;
		}
	}
	public static void removePriestClass(CharacterProfile profile){
		if(profile.characterclasses.contains("Priest")){			
			profile.characterclasses.remove("Priest");
			/*remove Priest base stats*/
			profile.bonusMagic -= 2;
			profile.magicResistance -= 3;
			profile.mana -= 10;
			profile.moveSpeed -= 1; 
			profile.hp -= 12; 
			profile.attackRange -=2;
			profile.luck -= 0.03;
		}
	}
	
	
	/* **************************************************
	 * Class V: Marksman
	 * Ranged DPS, high physical dmg, long base range
	 * Builds: Ranged physical dmg/AS, ranged utility/CC
	 * Strong vs: Warrior, Priest. Weak vs Mage, Assassin
	 ****************************************************/
	public static void assignMarksmanClass(CharacterProfile profile){
		if(!profile.characterclasses.contains("Marksman")){
			profile.characterclasses.add("Marksman");
			/*assign Marksman base stats*/
			profile.moveSpeed += 2; 
			profile.hp += 10; 
			profile.attackRange += 5;
			profile.attackDamage += 5;
			profile.armor += 2;
			profile.luck += 0.02;
		}
	}
	/*apply this function every time a CharacterProfile levels up*/
	public static void scaleMarksmanStats(CharacterProfile profile){
		if(profile.characterclasses.contains("Marksman")){
			profile.hp += 9;
			profile.attackDamage += 4;
			profile.attackSpeed += 0.003;
			profile.mana += 5;
			profile.armor += 1;
			profile.magicResistance += 1;
		}
	}
	public static void removeMarksmanClass(CharacterProfile profile){
		if(profile.characterclasses.contains("Marksman")){
			profile.characterclasses.remove("Marksman");
			/*Remove Marksman base stats*/
			profile.moveSpeed -= 2; 
			profile.hp -= 10; 
			profile.attackRange -= 5;
			profile.attackDamage -= 5;
			profile.armor -= 2;
			profile.luck -= 0.02;
		}
	}
	
	
	public static void main(String[] args){
		
	}
}
