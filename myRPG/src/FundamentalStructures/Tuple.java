package FundamentalStructures;

/* Simple tuple class for future uses. 
 */

public class Tuple<L,R> {
	private L l;
	private R r; 
	
	public Tuple(L l, R r){
		this.l = l;
		this.r = r;
	}
	
	public L getL(){
		return l;
	}
	public R getR(){
		return r; 
	}
	
	public boolean equals(Tuple<L,R> p){
		if(this.l.equals(p.getL()) && this.r.equals(p.getR())){
			return true;
		}
		return false;
	}
	public static void main(String args[]){
		
	}
}
