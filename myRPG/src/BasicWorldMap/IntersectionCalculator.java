package BasicWorldMap;

import java.awt.Rectangle;
import java.awt.geom.Line2D;

public class IntersectionCalculator {
	public static int mapWidth = 400;
	public static int mapHeight = 400;
	
	
	// TODO: make this work for general polygons!!
	public static boolean computeIntersectionCases(Rectangle A, Rectangle B){		
		// coordinates of rectangle B
		int[] xCoordsB = new int[4];
		int[] yCoordsB = new int[4];
		
		xCoordsB[0] = B.x; 
		xCoordsB[1] = B.x + B.width;
		xCoordsB[2] = B.x + B.width; 
		xCoordsB[3] = B.x;
		
		yCoordsB[0] = B.y; 
		yCoordsB[1] = B.y;
		yCoordsB[2] = B.y + B.height;
		yCoordsB[3] = B.y + B.height;
		
		boolean intersected = false;
		int side1 = numIntersectionsWithPolygon(xCoordsB[0], yCoordsB[0], xCoordsB[1], yCoordsB[1], A);
		int side2 = numIntersectionsWithPolygon(xCoordsB[1], yCoordsB[1], xCoordsB[2], yCoordsB[2], A);
		int side3 = numIntersectionsWithPolygon(xCoordsB[2], yCoordsB[2], xCoordsB[3], yCoordsB[3], A);
		int side4 = numIntersectionsWithPolygon(xCoordsB[3], yCoordsB[3], xCoordsB[0], yCoordsB[0], A);
		System.out.println(side1 + "  " + side2 + "   " + side3 + "  " + side4);
		if(side2 == 1 && side1 == 0 && side3 == 0 &&side4 == 1 && aabbContainsSegment (xCoordsB[0], yCoordsB[0], xCoordsB[1], yCoordsB[1], A.x, A.y, A.x + A.width, A.y + A.height)){
		//	System.out.println("side1: " + numIntersectionsWithPolygon(xCoordsB[0], yCoordsB[0], xCoordsB[1], yCoordsB[1], A));
			A.y = B.y - A.height;
			intersected = true;
		}
		if(side2 == 2 || (side1 == 1 && side2 == 1) || (side3 == 1 && side2 == 1)){
		//	System.out.println("side2: " + numIntersectionsWithPolygon(xCoordsB[1], yCoordsB[1], xCoordsB[2], yCoordsB[2], A));
			A.x = B.x + B.width;
			intersected = true;
		}
		if(side2 == 1 && side4 == 1 && aabbContainsSegment (xCoordsB[2], yCoordsB[2], xCoordsB[3], yCoordsB[3], A.x, A.y, A.x + A.width, A.y + A.height)){
		//	System.out.println("side3!: " + numIntersectionsWithPolygon(xCoordsB[2], yCoordsB[2], xCoordsB[3], yCoordsB[3], A));
			A.y = B.y + B.height;
			intersected = true;
		}
		if(side4 == 2 || (side1 == 1 && side4 == 1) || (side3 == 1 && side4 == 1)){
		//	System.out.println("side4! " + numIntersectionsWithPolygon(xCoordsB[3], yCoordsB[3], xCoordsB[0], yCoordsB[0], A));
			A.x = B.x - A.width;
			intersected = true;
		}		

		return intersected;
	}
	
	public static int numIntersectionsWithPolygon(float x1, float y1, float x2, float y2, Rectangle A){
		int[] xCoordsA = new int[4];
		int[] yCoordsA = new int[4];
		int numIntersections = 0;
		
		xCoordsA[0] = A.x; 
		xCoordsA[1] = A.x + A.width;
		xCoordsA[2] = A.x + A.width; 
		xCoordsA[3] = A.x;
		
		yCoordsA[0] = A.y; 
		yCoordsA[1] = A.y;
		yCoordsA[2] = A.y + A.height;
		yCoordsA[3] = A.y + A.height;
		
		Line2D l1 = new Line2D.Float(x1,y1,x2,y2);
		for(int i=0;i<4;i++){
			Line2D l2 = new Line2D.Float(xCoordsA[i], yCoordsA[i], xCoordsA[(i+1)%4], yCoordsA[(i+1)%4]);
			if( aabbContainsSegment (x1,y1,x2,y2, A.x, A.y, A.x + A.width, A.y + A.height) && l2.intersectsLine(l1)){
				numIntersections++;
			}
		}		
		return numIntersections;
	}

	public static boolean aabbContainsSegment (float x1, float y1, float x2, float y2, float minX, float minY, float maxX, float maxY) {  
	    // Completely outside.
	    if ((x1 <= minX && x2 <= minX) || (y1 <= minY && y2 <= minY) || (x1 >= maxX && x2 >= maxX) || (y1 >= maxY && y2 >= maxY))
	        return false;

	    float m = (y2 - y1) / (x2 - x1);

	    float y = m * (minX - x1) + y1;
	    if (y > minY && y < maxY) return true;

	    y = m * (maxX - x1) + y1;
	    if (y > minY && y < maxY) return true;

	    float x = (minY - y1) / m + x1;
	    if (x > minX && x < maxX) return true;

	    x = (maxY - y1) / m + x1;
	    if (x > minX && x < maxX) return true;

	    return false;
	}
}
