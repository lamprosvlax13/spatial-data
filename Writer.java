import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Writer {
	
	private String path;
	private FileWriter fileWriter;
	private BufferedWriter bufferedWriter;

	
	public Writer(String pathFile) {
		this.path=pathFile;
		
	}
	

	public void write(String headerLine,ArrayList<Chell> data) {
	
		 try {
	         fileWriter = new FileWriter(this.path);
	         bufferedWriter = new BufferedWriter(fileWriter);

	            bufferedWriter.write(headerLine);
	            
	            for(Chell chell :data) {
	            	
	            	bufferedWriter.write(chell.getI()+" "+chell.getJ()+" "+chell.getRecordsInChell().size()+"\n");
	            	//bufferedWriter.write(chell.getI()+" "+chell.getJ()+" "+chell.getListWithRecordsInChell()+"\n");
	            	
	            }
	            
	            
	            bufferedWriter.close();
	            fileWriter.close();
	            
	            System.out.println("Το αρχειο γραφτηκε επιτυχως!!");

	        } catch (IOException e) {
	            System.out.println("Προβλημα στο γραψιμο ");
	            e.printStackTrace();
	        }
	    }
		
	public void writeGRD(String headerLine,List<String> data) {
		 try {
	         fileWriter = new FileWriter(this.path);
	         bufferedWriter = new BufferedWriter(fileWriter);
	            for(String chell :data) {
	            	
	            	bufferedWriter.write(chell);
	            }
	            
	            
	            bufferedWriter.close();
	            fileWriter.close();

	            System.out.println("Το αρχειο γραφτηκε επιτυχως!!");

	        } catch (IOException e) {
	        	 System.out.println("Προβλημα στο γραψιμο ");
	            e.printStackTrace();
	        }
	    }
		
		
}
	

