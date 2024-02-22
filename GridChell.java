import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
//lampros vlahopoulos 2948
public class GridChell {

	private int gridSize;
	private List<Point> listMaxMinXYInSet;
	private LinkedHashMap<Chell, List<Record>> mapPositionByChell = new LinkedHashMap<Chell, List<Record>>();
	private LinkedHashMap<String, Chell> mapPositionByChellSearching = new LinkedHashMap<String, Chell>();
	
	private ArrayList<Integer> numrecordsInChellList = new ArrayList<Integer>(); 
	private double xCellSize;
	private double yCellSize;
	
	public GridChell(int gridSize,List<Point> MaxMinXYInSet ) {
		this.gridSize=gridSize;
		this.listMaxMinXYInSet=MaxMinXYInSet;
	
		createGrid();
	}
	public GridChell() {
		//default
	}
	
	
	

	public void createGrid() {
		Point minPoint = listMaxMinXYInSet.get(0);
		Point maxPoint = listMaxMinXYInSet.get(1);
		double Xmin = minPoint.getX();
		double Ymin = minPoint.getY();
		double Xmax = maxPoint.getX();
		double Ymax = maxPoint.getY();
		for(int i=0; i<this.gridSize; i++) {
			for(int j=0; j<this.gridSize; j++) {
				
				double xmin =Xmin + (i*(Xmax-Xmin))/this.gridSize;
				double xmax =Xmin + ((i+1)*(Xmax-Xmin))/this.gridSize;
				double ymin =Ymin + (j*(Ymax-Ymin))/this.gridSize;
				double ymax =Ymin +  ((j+1)*(Ymax-Ymin))/this.gridSize;
				Chell chell =new Chell (i,j,xmin,ymin,xmax,ymax);
				mapPositionByChell.put(chell, null);
				String key = i+","+j;
				mapPositionByChellSearching.put(key, chell);
				;
			}	
		}
		double xmin =Xmin;
		double xmax =Xmin + ((Xmax-Xmin))/this.gridSize;
		double ymin =Ymin;
		double ymax =Ymin + ((Ymax-Ymin))/this.gridSize;
		xCellSize = xmax-xmin;
		yCellSize = ymax- ymin;
		
	}
	
	//RecordManager pairnw eisodo
	public void assignToCell(HashMap<Integer, Record> recordsMapIdLinestring) {
		for (Map.Entry<Integer, Record> entry : recordsMapIdLinestring.entrySet()) {
			//Integer intedifier = entry.getKey();
			Record record = entry.getValue();
			List<Point> minMaxPointInRecord = record.getListMaxMinXYInRecord();
			Point minPointInRecord = minMaxPointInRecord.get(0);
			Point maxPointInRecord = minMaxPointInRecord.get(1);
			double xmin = minPointInRecord.getX();
			double ymin = minPointInRecord.getY();
			double xmax = maxPointInRecord.getX();
			double ymax =maxPointInRecord.getY();
			Point minPointInSet = listMaxMinXYInSet.get(0);
			double Xmin = minPointInSet.getX();
			double Ymin = minPointInSet.getY();

			int xMinCell = (int) Math.floor((xmin - Xmin) / xCellSize); // mporei na mh 9elei floor
			int yMinCell = (int) Math.floor((ymin - Ymin) / yCellSize);
			int xMaxCell = (int) Math.floor((xmax - Xmin) / xCellSize);
			int yMaxCell = (int) Math.floor((ymax - Ymin) / yCellSize);

			// int xMinCell = (int) ((xmin - Xmin) / xCellSize); // 
			// int yMinCell = (int) ( (ymin - Ymin) / yCellSize);
			// int xMaxCell = (int) ( (xmax - Xmin) / xCellSize);
			// int yMaxCell = (int) ((ymax - Ymin) / yCellSize);
			
			
			if(xMinCell== gridSize) {
     			xMinCell--;
     		}
     		if(yMinCell== gridSize) {
     			yMinCell--;
     		}
     		
     		if(xMaxCell== gridSize) {
     			xMaxCell--;
     		}
     		
     		if(yMaxCell== gridSize) {
     			yMaxCell--;
     		}
			
			
			
			for (int i = xMinCell; i <= xMaxCell; i++) {
				for (int j = yMinCell; j <= yMaxCell; j++) {
					
					Chell chell = mapPositionByChellSearching.get(i+","+j);
					chell.addRecordInChellList(record);
					/*
					for (Entry<Chell, List<Record>> entryChells : mapPositionByChell.entrySet()) {
						Chell chell = entryChells.getKey();
						if (i == chell.getI() && j == chell.getJ()) {
							chell.addRecordInChellList(record);
							break;
						}

					}*/

				}
			}

		}
		initialzieMapRecords();
	}
	
	public void addChellInMap(Chell chell) {
		
		mapPositionByChell.put(chell,null);
	}

	private void initialzieMapRecords() {
		for (Entry<Chell, List<Record>> entryChells : mapPositionByChell.entrySet()) {
			Chell chell = entryChells.getKey();
			List<Record> recordsInsideChell = chell.getRecordsInChell();
			mapPositionByChell.put(chell, recordsInsideChell);
			
			
		}
		
	}
	
	
	public LinkedHashMap<String, Chell> getMapPositionByChellSearching() {
		return mapPositionByChellSearching;
	}
	public LinkedHashMap<Chell, List<Record>> getMapPositionByChell() {
		return mapPositionByChell;
	}
	public void setListMaxMinXYInSet(List<Point> listMaxMinXYInSet) {
		this.listMaxMinXYInSet = listMaxMinXYInSet;
	}
	public void setGridSize(int gridSize) {
		this.gridSize = gridSize;
	}
	public ArrayList<Integer> getNumrecordsInChellList() {
		return numrecordsInChellList;
	}
	public void setNumrecordsInChellList(ArrayList<Integer> numrecordsInChellList) {
		this.numrecordsInChellList = numrecordsInChellList;
	}
	public double getxCellSize() {
		return xCellSize;
	}
	public double getyCellSize() {
		return yCellSize;
	}
	public int getGridSize() {
		return gridSize;
	}
	
}
