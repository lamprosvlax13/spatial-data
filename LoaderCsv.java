
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;



//lampros vlachpoulos , AM 2948
public class LoaderCsv {
	
	private File readFile;
	private RecordManager recordManager;
	private GridChell gridChell;
	private ArrayList<String> quiries = new ArrayList<String>();


	public LoaderCsv(String pathFile) {
		super();
		this.readFile = new File(pathFile);
		this.recordManager = new RecordManager();
		this.gridChell= new GridChell();
	}

	public void load(String delimiter) throws IOException {
		String path = readFile.getAbsolutePath();
		try {
			
			FileReader fileReader = new FileReader(path);
			BufferedReader br = new BufferedReader(fileReader);
			String line = br.readLine();
			int identifier =0;
			while (line != null) {
				line = br.readLine();
				identifier++;
				if (line == null) {
					break;
				}
				String[] XYPerLine = line.split(delimiter);
				Record record =new Record(identifier,XYPerLine);
				this.recordManager.mappingRecord(record);
				
				
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	

	public void loadGridDir(String delimiter) throws IOException {
		String path = readFile.getAbsolutePath();
		try {
			 
			FileReader fileReader = new FileReader(path);
			BufferedReader br = new BufferedReader(fileReader);
			String line = br.readLine();
			String[] minMaxLine= (line.split(delimiter));
			double minX = Double.parseDouble(minMaxLine[0]);
			double maxX = Double.parseDouble(minMaxLine[1]);
			double minY = Double.parseDouble(minMaxLine[2]);
			double maxY = Double.parseDouble(minMaxLine[3]);
			List<Point> maxMinPointsInSet = new ArrayList<Point>();
			Point minPointInSet = new Point(minX,minY);
			Point maxPointInSet = new Point(maxX,maxY);
			maxMinPointsInSet.add(minPointInSet);
			maxMinPointsInSet.add(maxPointInSet);
			
			gridChell.setListMaxMinXYInSet(maxMinPointsInSet);
			int gridChelSize = 0;
			ArrayList<Integer> numrecordsInChellList = new ArrayList<Integer>(); 
			while (line != null) {
				line = br.readLine();
				
				if (line == null) {
					break;
				}
				String[] chells= (line.split(delimiter));
				
				int numberOfRecordsInChell = Integer.parseInt(chells[2]);
				numrecordsInChellList.add(numberOfRecordsInChell);
				gridChelSize++;
			}
			int sizeGrid=(int) Math.pow(gridChelSize, 0.5);
			gridChell.setGridSize(sizeGrid);
			gridChell.setNumrecordsInChellList(numrecordsInChellList);
			gridChell.createGrid();
			
			
			
			br.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	public void loadGridGrd(String delimiter) throws IOException {
		String path = readFile.getAbsolutePath();
		try {
			FileReader fileReader = new FileReader(path);
			BufferedReader br = new BufferedReader(fileReader);
			String line = br.readLine();
			while (line != null) {
				
				String[] XYPerLine = line.split(delimiter);
				int identifier =Integer.parseInt(XYPerLine[0]);
				//System.out.println(identifier);
				String[] XminYmin = XYPerLine[1].split(" ");
				String[] XmaxYmax = XYPerLine[2].split(" ");
				double minX = Double.parseDouble(XminYmin[0]);
				double minY = Double.parseDouble(XminYmin[1]);
				double maxX = Double.parseDouble(XmaxYmax[0]);
				double maxY = Double.parseDouble(XmaxYmax[1]);
				List<Point> maxMinPointsInSet = new ArrayList<Point>();
				Point minPointInSet = new Point(minX,minY);
				Point maxPointInSet = new Point(maxX,maxY);
				maxMinPointsInSet.add(minPointInSet);
				maxMinPointsInSet.add(maxPointInSet);
				
				//
				int position = 3;
				int size = XYPerLine.length-3;
				String[] dataWithOut3firstfields = new String[size];
				int counter = 0;
				while(position< XYPerLine.length) {
					
					dataWithOut3firstfields[counter]=XYPerLine[position];
					counter++;
					position++;
					
				}
				
				Record record = new Record(identifier,dataWithOut3firstfields);
				record.setListMaxMinXYInRecord(maxMinPointsInSet);
				recordManager.mappingRecordGrd(record);
				line = br.readLine();
				
			}
			recordManager.findMaxMinXYInSet();
			//System.out.println(counter2);
			br.close();
		
			
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
	}
	
	public void loadQueries() throws IOException {
		String path = readFile.getAbsolutePath();
		try {
			FileReader fileReader = new FileReader(path);
			BufferedReader br = new BufferedReader(fileReader);
			String line = br.readLine();
			while (line != null) {
				//line = br.readLine();
				if(line.equals("")) {
					line = br.readLine();
					continue;
				}
				
				
				quiries.add(line);
				
				line = br.readLine();	
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		
	}
	public RecordManager getRecordManager() {
		return this.recordManager;
	}	

	public GridChell getGridChell() {
		return gridChell;
	}
	public void setReadFile(String path) {
		this.readFile =  new File(path);
	}
	public ArrayList<String> getQuiries() {
		return quiries;
	}
}
