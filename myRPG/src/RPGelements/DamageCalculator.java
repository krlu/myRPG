package RPGelements;

public class DamageCalculator {
	
	public int totalPhysicalDamage; /* attack damage, and various damage from physical items*/
	public int totalMagicDamage; 
	public int totalTrueDamage;
	
	public DamageCalculator(){
		
	}
	
	public static int effectiveDamage(int damage, int mitigator){
		double effectiveDamage = 0;
		if(mitigator >= 0){
			effectiveDamage = ((double) damage * 100.0)/(100.0 + (double)mitigator);
		}
		if(mitigator < 0){
			effectiveDamage = (double) damage *(2 - (100.0/(100.0 - (double) mitigator)));
		}
		return  (int) effectiveDamage;
	}
	
	public static void main(String[] args){;
		System.out.println(DamageCalculator.effectiveDamage(220,60) + " " + 425);
		System.out.println(DamageCalculator.effectiveDamage(320,90) + " " + 700);
		System.out.println(DamageCalculator.effectiveDamage(182,160) + " " + 950);
		System.out.println(DamageCalculator.effectiveDamage(288,200) + " " + 1200);
	}
}
