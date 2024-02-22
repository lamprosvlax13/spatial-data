import java.util.ArrayList;


//lampros vlahopoulos 2948
public class Chell {
	
	private int i;
	private int j;
	private double xmin;
	private double ymin;
	private double xmax;
	private double ymax;
	private ArrayList<Record> RecordsInChell;
	
	
	public Chell(int i, int j, double xmin, double ymin, double xmax, double ymax) {
		super();
		this.i = i;
		this.j = j;
		this.xmin = xmin;
		this.ymin = ymin;
		this.xmax = xmax;
		this.ymax = ymax;
		this.RecordsInChell=new ArrayList<Record>();

	}
	
	//Load Dir
	public Chell(int i, int j) {
		super();
		this.i = i;
		this.j = j;
	}
	
	public void  addRecordInChellList(Record record) {
		this.RecordsInChell.add(record);
	}


	
	
	public int getI() {
		return i;
	}


	public int getJ() {
		return j;
	}

	public double getXmin() {
		return xmin;
	}

	public double getYmin() {
		return ymin;
	}

	public double getXmax() {
		return xmax;
	}

	public double getYmax() {
		return ymax;

	
	}
	public void setXmin(double xmin) {
		this.xmin = xmin;
	}


	public void setYmin(double ymin) {
		this.ymin = ymin;
	}


	public void setXmax(double xmax) {
		this.xmax = xmax;
	}


	public void setYmax(double ymax) {
		this.ymax = ymax;
	}
	
	public ArrayList<Record> getRecordsInChell() {
		return RecordsInChell;
	}


	public void setRecordsInChell(ArrayList<Record> recordsInChell) {
		RecordsInChell = recordsInChell;
	}

}
