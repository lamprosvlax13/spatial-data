import java.util.ArrayList;
import java.util.List;
//lampros vlahopoulos 2948
public class Record {
	
	private int idintifier;
	private List<Point> listMaxMinXYInRecord; 
	private List<Point> pointsList;
	
	


	public Record(int idintifier, String[] XYPerLine ) {
		super();
		this.idintifier = idintifier;
		this.pointsList = new ArrayList<>();
		this.listMaxMinXYInRecord= new ArrayList<>();
		for(String XY:XYPerLine) {
			String[] XYtable = XY.split(" ");
			double x = Double.parseDouble(XYtable[0]);
			double y = Double.parseDouble(XYtable[1]);
			Point point= new Point(x,y);
			this.pointsList.add(point);
		}
		
	}

	public void  FindMaxMinXYPerRecord() {
		
		double Xmin = 10000000.0;
		double Ymin = 10000000.0;
		double Xmax = -1000000.0;
		double Ymax = -1000000.0;
		
		for(Point point :pointsList ) {
			double x= point.getX();
			double y = point.getY();
			
			if(x<=Xmin) {
				Xmin=x;
			}
			if(y<=Ymin) {
				Ymin=y;
			}
			if(x>=Xmax) {
				Xmax=x;
			}
			if(y>=Ymax) {
				Ymax=y;
			}
			
		}
		
		Point minPoint = new Point(Xmin,Ymin);
		Point maxPoint = new Point(Xmax,Ymax);
		listMaxMinXYInRecord.add(minPoint);
		listMaxMinXYInRecord.add(maxPoint);
	}
	public List<Point> getListMaxMinXYInRecord() {
		return listMaxMinXYInRecord;
	}


	public void setListMaxMinXYInRecord(List<Point> listMaxMinXYInRecord) {
		this.listMaxMinXYInRecord = listMaxMinXYInRecord;
	}
	
	public void setPointsList(List<Point> pointsList) {
		this.pointsList = pointsList;
	}
	
	public int getIdintifier() {
		return idintifier;
	}
	public List<Point> getPointsList() {
		return pointsList;
	}


}
