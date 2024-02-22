import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;


public class QueriesMain {
	
	
	
	public static void main(String[] args) throws IOException {

		String pathGridDir = "grid.dir";
		LoaderCsv loader = new LoaderCsv(pathGridDir);
		String delimiter = " ";
		loader.loadGridDir(delimiter);
		

		String pathGridGdr = "grid.grd";
		loader.setReadFile(pathGridGdr);
		loader.loadGridGrd(",");
		int start = 0;
	
		
		
		int position =0;
		ArrayList<Integer> numberRecordsInChellList = loader.getGridChell().getNumrecordsInChellList();
		for (Map.Entry<Chell, List<Record>> entry : loader.getGridChell().getMapPositionByChell().entrySet()) {
			
			int numberOfRecordInsdideChell= numberRecordsInChellList.get(position);
			if (numberOfRecordInsdideChell == 0) {
				Chell chell = entry.getKey();
				loader.getGridChell().getMapPositionByChell().put(chell, new ArrayList<Record>());
				position++;
				continue;
			}
			
			for (Record record : loader.getRecordManager().getRecordsList().subList(start, start + numberOfRecordInsdideChell)) {
				
				Chell chell = entry.getKey();
				chell.addRecordInChellList(record);
				loader.getGridChell().getMapPositionByChell().put(chell, chell.getRecordsInChell());
			}
			start = start + numberOfRecordInsdideChell;
			position++;
			
		}
		
		
		
		
		String path = "queries.txt";
		loader.setReadFile(path);
		loader.loadQueries();
		ArrayList<String> quiries = loader.getQuiries();
		
		WindowsQueries win = new WindowsQueries(quiries, loader.getRecordManager(),loader.getGridChell());
		win.filterQueries();
		System.out.println("############################################################");
		win.refinementQueries();
		


	}

}
