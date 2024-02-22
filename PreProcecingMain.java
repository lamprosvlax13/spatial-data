	
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;


public class PreProcecingMain {
	// lampros vlachpoulos , AM 2948

	public static void main(String args[]) throws IOException {

		// load File
		String pathFile = "tiger_roads.csv";
		LoaderCsv loader = new LoaderCsv(pathFile);
		String delimiter = ",";
		loader.load(delimiter);
		

		// create CridChell
		int gridSize= 10;
		RecordManager recordManager = loader.getRecordManager();
		recordManager.findMaxMinXYInSet();
		List<Point> MaxMinInSet = recordManager.getMaxMinPointsInSet();
		
		GridChell grid = new GridChell(gridSize, MaxMinInSet);
		grid.assignToCell(recordManager.getRecordsMapIdLinestring());

		// ############################## write Grid.Dir #############################
		//ArrayList<Chell> chellsList = grid.getListChells();
		List<Point> MaxMixXYinSet = recordManager.getMaxMinPointsInSet();
		Point minPoint = MaxMixXYinSet.get(0);
		Point maxPoint = MaxMixXYinSet.get(1);
		double Xmin = minPoint.getX();
		double Ymin = minPoint.getY();
		double Xmax = maxPoint.getX();
		double Ymax = maxPoint.getY();
		String fileNameGridDir = "grid.dir";
		String headerGridDir = Xmin+ " " + Xmax + " " + Ymin + " "
				+ Ymax + "\n";
		Writer writerGridDir = new Writer(fileNameGridDir);
		ArrayList<Chell> listChells = new ArrayList<Chell>();
		HashMap<Chell, List<Record>> mapPositionByChell = grid.getMapPositionByChell();
	
		
		for (Entry<Chell, List<Record>> entryChells : mapPositionByChell.entrySet()) {
		
			Chell chell = entryChells.getKey();
			listChells.add(chell);
	
			
		}
		writerGridDir.write(headerGridDir, listChells);
		
	
		// #################################### write Grid.grd
		// #######################################################
				
		ArrayList<String> data = new ArrayList<String>();
		String fileName = "grid.grd";
		//String header = "identifier MBRminX MBRminYMBRmaxX MBRmaxY\n";
		Writer writer = new Writer(fileName);
		for (Entry<Chell, List<Record>> entryChells : mapPositionByChell.entrySet()) {
			List<Record> recordList = entryChells.getValue();
			String line = "";
			for(Record record :recordList ) {
				List<Point> minMaxPointInRecord = record.getListMaxMinXYInRecord();
				Point minPointInRecord = minMaxPointInRecord.get(0);
				Point maxPointInRecord = minMaxPointInRecord.get(1);
				double xmin = minPointInRecord.getX();
				double ymin = minPointInRecord.getY();
				double xmax = maxPointInRecord.getX();
				double ymax =maxPointInRecord.getY();	
				
				
				String	head= record.getIdintifier()+","+xmin+" "+ymin+","+xmax+" "+ymax+",";
				List<Point> pointsByRecord = record.getPointsList();
				String lineString ="";
				int i = 0;
				while (i < pointsByRecord.size()) {
					if (i == pointsByRecord.size() - 1) {
						lineString +=pointsByRecord.get(i).toString();
						break;
					}
					lineString +=pointsByRecord.get(i).toString() + ",";
					i++;
				}
				line=head+lineString+"\n";
				data.add(line);
			}
			
		}
		
		 writer.writeGRD("", data);

	}
}