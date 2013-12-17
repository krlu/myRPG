package FundamentalStructures;

/* Simple tuple class for future uses. 
 */

public class Tuple<L,R> {
	public L l;
	public R r; 
	
	public Tuple(L l, R r){
		this.l = l;
		this.r = r;
	}
	
	
	public boolean equals(Tuple<L,R> p){
		if(this.l.equals(p.l) && this.r.equals(p.r)){
			return true;
		}
		return false;
	}
	public static void main(String args[]){
		
	}
}
