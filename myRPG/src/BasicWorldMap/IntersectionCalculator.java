package BasicWorldMap;

import java.awt.Rectangle;

public class IntersectionCalculator {
	public static int mapWidth = 400;
	public static int mapHeight = 400;
	
	
	// TODO: make this work for general polygons!!
	public static boolean computeIntersectionCases(Rectangle A, Rectangle B){		
		// coordinates of rectangle B
		int[] xCoords = new int[4];
		int[] yCoords = new int[4];
		
		xCoords[0] = B.x; 
		xCoords[1] = B.x + B.width;
		xCoords[2] = B.x + B.width; 
		xCoords[3] = B.x;
		
		yCoords[0] = B.y; 
		yCoords[1] = B.y;
		yCoords[2] = B.y + B.height;
		yCoords[3] = B.y + B.height;
		boolean intersected = false;
		if(aabbContainsSegment(xCoords[0], yCoords[0], xCoords[1], yCoords[1], A.x,A.y,A.x + A.width, A.y + A.height)){
			System.out.println("segment 1 intersection!");
			A.y = yCoords[0] - A.height;
			intersected = true;
		}
		if(aabbContainsSegment(xCoords[1], yCoords[1], xCoords[2], yCoords[2], A.x,A.y,A.x + A.width, A.y + A.height)){
			System.out.println("segment 2 intersection!");
		//	A.x = xCoords[1];
			intersected = true;
		}
		if(aabbContainsSegment(xCoords[2], yCoords[2], xCoords[3], yCoords[3], A.x,A.y,A.x + A.width, A.y + A.height)){
			System.out.println("segment 3 intersection!");
			A.y = yCoords[2];
			intersected = true;
		}
		if(aabbContainsSegment(xCoords[3], yCoords[3], xCoords[0], yCoords[0], A.x,A.y,A.x + A.width, A.y + A.height)){
			System.out.println("segment 4 intersection!");
			A.x = xCoords[3] - A.width;
			intersected = true;
		}
		
		return intersected;
	}
	
	// check for corner intersections
	public static boolean CornerIntersection(int x, int y, Rectangle A){
		return x > A.x && x < A.x + A.width && y > A.y && y < A.y + A.width;
	}
	
	public static boolean aabbContainsSegment (float x1, float y1, float x2, float y2, 
											   float minX, float minY, float maxX, float maxY) {  
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
