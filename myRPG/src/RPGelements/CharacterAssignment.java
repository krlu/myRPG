package RPGelements;

/*
 * this class contains methods for assigning eachp player the CharacterClass of their choosing
 */
public class CharacterAssignment {

	public static void assignWarriorClass(CharacterProfile profile){
		if(!profile.characterclasses.contains("Warrior")){
			profile.characterclasses.add("Warrior");
			/*assign warrior base stats*/
			profile.armor += 4;
			profile.moveSpeed += 2; 
			profile.hp += 18; 
			profile.attackDamage += 4;
			profile.luck += 0.01;
			profile.fervor += 3;
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
	
	
	
	public static void assignAssasinClass(CharacterProfile profile){
		if(!profile.characterclasses.contains("Assasin")){
			profile.characterclasses.add("Assasin");
			/*assign Assasin base stats*/
			profile.moveSpeed += 6; 
			profile.hp += 5; 
			profile.attackDamage += 8;
			profile.attackRange += 1;
			profile.luck += 0.05;
		}
	}
	public static void removeAssasinClass(CharacterProfile profile){
		if(profile.characterclasses.contains("Assasin")){
			profile.characterclasses.remove("Assasin");
			/*Remove Assasin base stats*/
			profile.moveSpeed -= 6; 
			profile.hp -= 2; 
			profile.attackDamage -= 8;
			profile.attackRange -= 1;
			profile.luck -= 0.05;
		}
	}	
	
	
	
	public static void assignPriestClass(CharacterProfile profile){
		if(!profile.characterclasses.contains("Priest")){
			profile.characterclasses.add("Priest");
			/*assign Priest base stats*/
			profile.bonusMagic += 2;
			profile.magicResistance += 3;
			profile.mana += 10;
			profile.moveSpeed += 1; 
			profile.hp += 12; 
			profile.attackRange +=2;
			profile.luck += 0.03;
		
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
	
	
	
	public static void assignArcherClass(CharacterProfile profile){
		if(!profile.characterclasses.contains("Archer")){
			profile.characterclasses.add("Archer");
			/*assign Archer base stats*/
			profile.moveSpeed += 2; 
			profile.hp += 10; 
			profile.attackRange += 5;
			profile.attackDamage += 5;
			profile.armor += 2;
			profile.luck += 0.02;
		}
	}
	public static void removeArcherClass(CharacterProfile profile){
		if(profile.characterclasses.contains("Archer")){
			profile.characterclasses.remove("Archer");
			/*Remove Archer base stats*/
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
