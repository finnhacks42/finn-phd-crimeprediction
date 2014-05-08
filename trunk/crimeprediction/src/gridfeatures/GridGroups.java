package gridfeatures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridGroups {
	/*** Makes the assumption that our cells represent a grid with ids starting from 0, labeled left to right and then top to botom. ***/
	public static Map<String,Map<Integer,List<Integer>>> buildGridGroups(int cellsWide, int cellsHigh, boolean ninePatch, boolean twentyfivePatch) {
		Map<String,Map<Integer,List<Integer>>> groups = new HashMap<String,Map<Integer,List<Integer>>>();
		Map<Integer,List<Integer>> a1 = new HashMap<Integer,List<Integer>>();
		Map<Integer,List<Integer>> a9 = new HashMap<Integer,List<Integer>>();
		Map<Integer,List<Integer>> a25 = new HashMap<Integer,List<Integer>>();
		groups.put("a1",a1);
		if (ninePatch) {
			groups.put("a9", a9);
		}
		if (twentyfivePatch) {
			groups.put("a25", a25);
		}
		for (int x = 0; x < cellsWide; x++) {
			for (int y = 0; y < cellsHigh; y++) {
				int cell = cell(x,y,cellsWide);
				List<Integer> alist = new ArrayList<Integer>();
				List<Integer> a9list = new ArrayList<Integer>();
				List<Integer> a25list = new ArrayList<Integer>();
				alist.add(cell);
				
				int[] diff = {-2,-1,0,1,2};
				for (int dx: diff) {
					for (int dy: diff) {
						int nearCell = nearCell(x, y, dx, dy, cellsWide, cellsHigh);
						a25list.add(nearCell);
						if (Math.abs(dx) <= 1 && Math.abs(dy) <= 1) {
							a9list.add(nearCell);
						}
					}
				}
				a1.put(cell,alist);
				a9.put(cell, a9list);
				a25.put(cell, a25list);
			}
		}	
		return groups;
	}
	
	/*** returns the cell the specified number of steps to the left/right and above/below the specified cell. If the cell would be out of bounds, it reflects back***/
	private static int nearCell(int x, int y,int dx, int dy, int wide, int high) {
		int rx = x+dx;
		int ry = y+dy;
		if (rx < 0 || rx > wide) {rx = x-dx;}
		if (ry < 0 || ry > high) {ry = y - dy;}
		return cell(rx,ry,wide);
	}
	
	private static int cell(int x, int y, int wide) {
		return y*wide+x;
	}
	
	public static void main(String[] args) {
		
		Map<String,Map<Integer,List<Integer>>> groups = buildGridGroups(5, 5,true,true);
		System.out.println(groups.get("a9").get(12));
	}

}
