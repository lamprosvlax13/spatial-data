import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

//lampros vlahopoulos 2948

public class WindowsQueries {
	
	private ArrayList<String> queries;
	private RecordManager recordManager;
	private GridChell gridChell;
	

	public WindowsQueries(ArrayList<String> queries, RecordManager recordManager, GridChell gridChell) {
		super();
		this.queries = queries;
		this.recordManager = recordManager;
		this.gridChell = gridChell;
	}
	
	public void filterQueries() {
		
		for(String query :queries ) {
			
			String[] parts = query.split(",");
	        String[] windowXY = parts[1].split(" ");
	      //window XY
            double x1 = Double.parseDouble(windowXY[0]); //katw aristera 
            double x2 = Double.parseDouble(windowXY[1]);
            double y1 = Double.parseDouble(windowXY[2]);
            double y2 = Double.parseDouble(windowXY[3]);
            //System.out.println(x1+" "+y1+" "+x2+" "+y2);
            List<Point> minMaxInSet = recordManager.getMaxMinPointsInSet();
            double Xmin=minMaxInSet.get(0).getX();
    		double Ymin=minMaxInSet.get(0).getY();
    		//System.out.println(minMaxInSet.get(0).toString());
    		double xCellSize = gridChell.getxCellSize();
            double yCellSize = gridChell.getyCellSize();
            //System.out.println(xCellSize+" "+ yCellSize);
        
            int xMinCell = (int) Math.floor((x1 - Xmin) / xCellSize); // ta kelia tou para9urou pou peftoun oi korufes
     		int yMinCell = (int) Math.floor((y1 - Ymin) / yCellSize);
     		int xMaxCell = (int) Math.floor((x2 - Xmin) / xCellSize);
     		int yMaxCell = (int) Math.floor((y2 - Ymin) / yCellSize);
     		
     		
     		int gridChellSize =  gridChell.getGridSize();
     		
     		if(xMinCell== gridChellSize) {
     			xMinCell--;
     		}
     		if(yMinCell== gridChellSize) {
     			yMinCell--;
     		}
     		
     		if(xMaxCell== gridChellSize) {
     			xMaxCell--;
     		}
     		
     		if(yMaxCell== gridChellSize) {
     			yMaxCell--;
     		}
     		LinkedHashMap<String, Chell> mapPositionByChellSearch = gridChell.getMapPositionByChellSearching();
     		List<Chell> listChells = new ArrayList<>();
     		for (int i = xMinCell; i <= xMaxCell; i++) {
    		    for (int j = yMinCell; j <= yMaxCell; j++) {
    		    	Chell chell = mapPositionByChellSearch.get(i+","+j);
    		    	if(chell.getRecordsInChell().size()!=0) {
    		    		listChells.add(chell);
    		    	}
    		    	
    		    	//System.out.println(chell.getI()+" "+chell.getJ());
    		    	//System.out.println(i+" "+j);
    		    }
    		}
     		ArrayList<Record> mathesRecord =new ArrayList<Record>();
     		for(Chell chell : listChells) {
     			//if(chell.getRecordsInChell().size()==0) {
 					//continue;
 				//}
     			//System.out.println(chell.getI()+" "+chell.getJ());
     			for(Record record : chell.getRecordsInChell() ) {
     				
     				//System.out.println(record.getListMaxMinXYInRecord());
     				List<Point> minMaxPoint= record.getListMaxMinXYInRecord();
     				Point minPointInRecord = minMaxPoint.get(0);
     				Point maxPointInRecord = minMaxPoint.get(1);
     				
     				double referenceX = Math.max(minPointInRecord.getX(), x1);
					double referenceY = Math.max(minPointInRecord.getY(), y1);
					//System.out.println(referenceX+" "+referenceY);
					
					if (minPointInRecord.getX() > x2 || maxPointInRecord.getX() < x1 || minPointInRecord.getY() > y2
							|| maxPointInRecord.getY() < y1) {

						// System.out.println("mphka");
					} else {
							
						if (referenceX <= chell.getXmax() && referenceY <= chell.getYmax()
								&& referenceX >= chell.getXmin() && referenceY >= chell.getYmin()) {
							//System.out.println(record.getIdintifier() + "    few " + chell.getI() + " " + chell.getJ());
							mathesRecord.add(record);
						} 
					}
     				
     				
     			}
     			
     			
     		}
     		
     		int queryNumber =Integer.parseInt(parts[0]);
			String resultString = "Query "+queryNumber+" results\n";
			String recordString ="";
			for(Record record : mathesRecord) {
				recordString+=record.getIdintifier()+" ";
			}
			resultString=resultString+recordString+"\n";
			int numberCells = listChells.size();
			String cellsString = "Cells: "+numberCells;
			resultString+=cellsString+"\n";
			int numberOfItemswhoIntersect = mathesRecord.size();
			String numberOfItemswhoIntersectResult = "Results: "+ numberOfItemswhoIntersect+"\n";
			resultString+=numberOfItemswhoIntersectResult+"\n";
			resultString+="----------------";
			System.out.println(resultString);
     		
		
		}
		
		
	}
	
	public void refinementQueries() {

		for(String query :queries ) {
			
			String[] parts = query.split(",");
	        String[] windowXY = parts[1].split(" ");
	      //window XY
            double x1 = Double.parseDouble(windowXY[0]); //katw aristera 
            double x2 = Double.parseDouble(windowXY[1]);
            double y1 = Double.parseDouble(windowXY[2]);
            double y2 = Double.parseDouble(windowXY[3]);
            //System.out.println(x1+" "+y1+" "+x2+" "+y2);
            List<Point> minMaxInSet = recordManager.getMaxMinPointsInSet();
            double Xmin=minMaxInSet.get(0).getX();
    		double Ymin=minMaxInSet.get(0).getY();
    		//System.out.println(minMaxInSet.get(0).toString());
    		double xCellSize = gridChell.getxCellSize();
            double yCellSize = gridChell.getyCellSize();
            //System.out.println(xCellSize+" "+ yCellSize);
        
            int xMinCell = (int) Math.floor((x1 - Xmin) / xCellSize); // ta kelia tou para9urou pou peftoun oi korufes
     		int yMinCell = (int) Math.floor((y1 - Ymin) / yCellSize);
     		int xMaxCell = (int) Math.floor((x2 - Xmin) / xCellSize);
     		int yMaxCell = (int) Math.floor((y2 - Ymin) / yCellSize);
     		
     		int gridChellSize =  gridChell.getGridSize();
     		
     		if(xMinCell== gridChellSize) {
     			xMinCell--;
     		}
     		if(yMinCell== gridChellSize) {
     			yMinCell--;
     		}
     		
     		if(xMaxCell== gridChellSize) {
     			xMaxCell--;
     		}
     		
     		if(yMaxCell== gridChellSize) {
     			yMaxCell--;
     		}
     		LinkedHashMap<String, Chell> mapPositionByChellSearch = gridChell.getMapPositionByChellSearching();
     		List<Chell> listChells = new ArrayList<>();
     		for (int i = xMinCell; i <= xMaxCell; i++) {
    		    for (int j = yMinCell; j <= yMaxCell; j++) {
    		    	Chell chell = mapPositionByChellSearch.get(i+","+j);
    		    	if(chell.getRecordsInChell().size()!=0) {
    		    		listChells.add(chell);
    		    	}
    		    	
    		    	
    		    }
    		}
     		ArrayList<Record> mathesRecord =new ArrayList<Record>();
     		for(Chell chell : listChells) {
     			//if(chell.getRecordsInChell().size()==0) {
 				//	continue;
 				//}
     			//System.out.println(chell.getI()+" "+chell.getJ());
     			for(Record record : chell.getRecordsInChell() ) {
     				//System.out.println(record.getListMaxMinXYInRecord());
     				List<Point> minMaxPoint= record.getListMaxMinXYInRecord();
     				Point minPointInRecord = minMaxPoint.get(0);
     				Point maxPointInRecord = minMaxPoint.get(1);
     				
     				double referenceX = Math.max(minPointInRecord.getX(), x1);
					double referenceY = Math.max(minPointInRecord.getY(), y1);
					//System.out.println(referenceX+" "+referenceY);
					
					if (minPointInRecord.getX() > x2 || maxPointInRecord.getX() < x1 || minPointInRecord.getY() > y2
							|| maxPointInRecord.getY() <y1) {

						// System.out.println("mphka");
					} else {

						if (referenceX <= chell.getXmax() && referenceY <= chell.getYmax()
								&& referenceX >= chell.getXmin() && referenceY >= chell.getYmin()) {
							//System.out.println(record.getIdintifier() + "    few " + chell.getI() + " " + chell.getJ());
							mathesRecord.add(record);
						} 
					}
     				
     				
     			}
     			
     			
     		}
     		ArrayList<Record> intersect = new ArrayList<Record>();
			for (Record record : mathesRecord) {
				
				List<Point> minMaxInRecord = record.getListMaxMinXYInRecord();
				double minX = minMaxInRecord.get(0).getX();
				double minY = minMaxInRecord.get(0).getY();
				double maxX = minMaxInRecord.get(1).getX();
				double maxY = minMaxInRecord.get(1).getY();

				// a3ona X
				if (minX >= x1 && maxX <= x2) {
					intersect.add(record);
					continue;
				}
				// a3ona Y
				else if (minY >= y1 && maxY <= y2) {
					intersect.add(record);
					continue;

				}
				//plhrhs epikalu4h
				else if(minX<=x1 & maxX>=x2 && maxY>=y1 && maxY<=y2){
					
					intersect.add(record);
					continue;
					
				}else {
					//List<String> lineString = record.getLineString();
					List<Point> listPointInRecordList = record.getPointsList();
				
					for (int point = 0; point < listPointInRecordList.size()-1; point++) {
						// an exei 1 shmeio mono vale to !!!

						double x1a = listPointInRecordList.get(point).getX();
						double y1a = listPointInRecordList.get(point).getY();
						// deutero
						double x1b = listPointInRecordList.get(point+1).getX();
						double y1b = listPointInRecordList.get(point+1).getY();

						Double[] pleura = new Double[4];
						pleura[0] = x1;
						pleura[1] = y1;
						pleura[2] = x2;
						pleura[3] = y1;
						Double[] lineStringXY = new Double[4];
						lineStringXY[0] = x1a;
						lineStringXY[1] = y1a;
						lineStringXY[2] = x1b;
						lineStringXY[3] = y1b;

						boolean result = haveIntersectionPoint(pleura, lineStringXY);
						if (result==true) {
							intersect.add(record);
							break;
							//continue;

						} else {
							//continue;
							
							// x panw
							
							pleura = new Double[4];
							pleura[0] = x1;
							pleura[1] = y2;
							pleura[2] = x2;
							pleura[3] = y2;
							lineStringXY = new Double[4];
							lineStringXY[0] = x1a;
							lineStringXY[1] = y1a;
							lineStringXY[2] = x1b;
							lineStringXY[3] = y1b;

							result = haveIntersectionPoint(pleura, lineStringXY);
							if (result==true) {
								intersect.add(record);
								break;
								//continue;

							} else {

								// y de3ia
								pleura = new Double[4];
								pleura[0] = x2;
								pleura[1] = y1;
								pleura[2] = x2;
								pleura[3] = y2;
								lineStringXY = new Double[4];
								lineStringXY[0] = x1a;
								lineStringXY[1] = y1a;
								lineStringXY[2] = x1b;
								lineStringXY[3] = y1b;

								result = haveIntersectionPoint(pleura, lineStringXY);
								if (result==true) {
									intersect.add(record);
									break;
									//continue;

								} else {
									// y aristera

									pleura = new Double[4];
									pleura[0] = x1;
									pleura[1] = y1;
									pleura[2] = x1;
									pleura[3] = y2;
									lineStringXY = new Double[4];
									lineStringXY[0] = x1a;
									lineStringXY[1] = y1a;
									lineStringXY[2] = x1b;
									lineStringXY[3] = y1b;
									result = haveIntersectionPoint(pleura, lineStringXY);
									if (result==true) {
										intersect.add(record);
										break;
									} else {

										continue;
									}

								}

							}
							
						}

					}

				}

			}
     		
			int queryNumber =Integer.parseInt(parts[0]);
			String resultString = "Query "+queryNumber+" results\n";
			String recordString ="";
			for(Record record : intersect) {
				recordString+=record.getIdintifier()+" ";
			}
			resultString=resultString+recordString+"\n";
			int numberCells = listChells.size();
			String cellsString = "Cells: "+numberCells;
			resultString+=cellsString+"\n";
			int numberOfItemswhoIntersect = intersect.size();
			String numberOfItemswhoIntersectResult = "Results: "+ numberOfItemswhoIntersect+"\n";
			resultString+=numberOfItemswhoIntersectResult+"\n";
			resultString+="----------------";
			System.out.println(resultString);
     		
		
		}
		
		
	}
	public boolean haveIntersectionPoint(Double[] pleuraXy, Double[] lineStringXY) {
		double x3 = pleuraXy[0];
		double y3 = pleuraXy[1];
		double x4 = pleuraXy[2];
		double y4 = pleuraXy[3];
		
		double x1 = lineStringXY[0];
		double y1 = lineStringXY[1];
		// deutero
		double x2 = lineStringXY[2];
		double y2 = lineStringXY[3];
		
		double t = (((x1-x3)*(y3-y4)) - ((y1-y3)*(x3-x4)) )/(((x1-x2)*(y3-y4)) - ((y1-y2)* (x3-x4)));
		double u = (((x1-x3)*(y1-y2)) - ((y1-y3)*(x1-x2)) )/(((x1-x2)*(y3-y4)) - ((y1-y2)* (x3-x4)));
		if(t>=0 && t<=1 && u>=0 && u<=1) {
			return true;
		}else {
			return false;
		}
		

		
		
	}


}
