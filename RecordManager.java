import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

//lampros vlahopoulos 2948
public class RecordManager {

	private HashMap <Integer,Record> recordsMapIdLinestring;
	private List<Point> maxMinPointsInSet;
	private ArrayList<Record> recordsList; //loadGrd
	




	public RecordManager() {
		this.recordsMapIdLinestring =new HashMap <Integer,Record>();
		this.maxMinPointsInSet= new ArrayList<>();
		this.recordsList= new ArrayList<Record>();
	}
	
	
	
	
	public void mappingRecord(Record record) {
		record.FindMaxMinXYPerRecord();
		recordsMapIdLinestring.put(record.getIdintifier(),record);
	}
	
	public void mappingRecordGrd(Record record) {
		recordsList.add(record);
		recordsMapIdLinestring.put(record.getIdintifier(),record);
	}
	
	/*
	public void addRecordWithoutFindMaxMin(Record record) {
		//
		LinkedHashMap<List<Double>,List<String>> mapPerRecord =new LinkedHashMap<List<Double>,List<String>>();
		List<Double> MaxMinXY =new ArrayList<Double>();
		List<String> linestring =record.getLineString();
		MaxMinXY.add(record.getMbrMinX());
		MaxMinXY.add(record.getMbrMinY());
		MaxMinXY.add(record.getMbrMaxX());
		MaxMinXY.add(record.getMbrMaxY());
		mapPerRecord.put(MaxMinXY, linestring);
		recordsMapIdLinestring.put(record.getIdintifier(),mapPerRecord);
		//
		
		
		//this.recordsList.add(record);
	}
*/





	public void  findMaxMinXYInSet() {
		
		double minX= 9999999.0;
	    double minY= 9999999.0;
	    double maxX= -9999999.0;
	    double maxY= -9999999.0;
	    
		for (Map.Entry<Integer,Record> entry : this.recordsMapIdLinestring.entrySet()) {
			
			Record record = entry.getValue();
			
			List<Point> minMaxPointInRecord = record.getListMaxMinXYInRecord();
			Point minPointInRecord = minMaxPointInRecord.get(0);
			Point maxPointInRecord = minMaxPointInRecord.get(1);
			double recordXmin = minPointInRecord.getX();
			double recordYmin = minPointInRecord.getY();
			double recordXmax = maxPointInRecord.getX();
			double recordYmax =maxPointInRecord.getY();
			
			
			
			
			if(minX >= recordXmin) {
	    		minX = recordXmin;
	    	}
	    	if(minY >= recordYmin) {
	    		minY = recordYmin;
	    	}
	    	if(maxX<= recordXmax) {
	    		maxX= recordXmax;
	    		
	    	}
	    	if(maxY<= recordYmax) {
	    		maxY= recordYmax;
	    		
	    	}
				
	
		}
	
		Point minPoint = new Point (minX,minY);
		Point maxPoint  = new Point (maxX,maxY);
		this.maxMinPointsInSet.add(minPoint);
		this.maxMinPointsInSet.add(maxPoint);
		
	}
	
	
	
	
	public HashMap <Integer, Record> getRecordsMapIdLinestring() {
		return this.recordsMapIdLinestring;
	}
	public List<Point> getMaxMinPointsInSet() {
		return maxMinPointsInSet;
	}

	public ArrayList<Record> getRecordsList() {
		return recordsList;
	}

}
