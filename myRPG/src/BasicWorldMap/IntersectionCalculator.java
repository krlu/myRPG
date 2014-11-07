package BasicWorldMap;

import java.awt.Rectangle;

public class IntersectionCalculator {
	public static int mapWidth = 400;
	public static int mapHeight = 400;
	
	public static boolean computeIntersectionCases(Rectangle A, Rectangle B){
		
		boolean xIntersection1 = B.x < A.x && A.x < B.x + B.width;		
		boolean xIntersection2 = B.x < A.x + A.width && A.x + A.width < B.x + B.width;
		
		boolean yIntersection1 = B.y < A.y && A.y < B.y + B.height;		
		boolean yIntersection2 = B.y < A.y + A.height && A.y + A.height < B.y + B.height;
		
		if((xIntersection1  || xIntersection2) && (yIntersection1 || yIntersection2)){
			System.out.println("X");
			computeIntersectionCasesX(A,B);
			//computeIntersectionCasesY(A,B);
			return true;
		}
		return false;
	}

	// assumes A and B intersect along x coordinates
	private static void computeIntersectionCasesX(Rectangle A, Rectangle B){
		
		double AcenterX = A.x + ((double) A.width)/2;
		double BcenterX = B.x + ((double) B.width)/2;
		
		// position x coordinates;
		if(AcenterX < BcenterX){
			if(B.x == 0){
				A.x = B.x + B.width;
			}
			else{
				A.x = B.x; 
			}
		}
		else{
			if(B.x + B.width == mapWidth){
				A.x = B.x;
			}
			else{
				A.x = B.x + B.width;
			}
		}		

	
	}
	// do the same thing for y coordinates
	private static void computeIntersectionCasesY(Rectangle A, Rectangle B){
		double AcenterY = A.y + ((double) A.height)/2;
		double BcenterY = B.y + ((double) B.height)/2;
		if(AcenterY < BcenterY){
			if(B.y == 0){
				A.y = B.y + B.height;
			}
			else{
				A.y = B.y; 
			}
		}
		else{
			if(B.y + B.height == mapHeight){
				A.y = B.y;
			}
			else{
				A.y = B.y + B.height;
			}
		}	
	}

}
