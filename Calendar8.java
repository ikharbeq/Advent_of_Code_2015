import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Calendar8 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	FileInputStream fstream;
	int totalchars=0;
	int stringchars=0;	
	int bstringchars=0;
	try {
		fstream = new FileInputStream("src/calendar8input.txt");
		
	  	
		DataInputStream in = new DataInputStream(fstream);
	    BufferedReader br = new BufferedReader(new InputStreamReader(in));
	    String strLine;	        
	    String bLine;
	    
	    //Read File Line By Line
	    while ((strLine = br.readLine()) != null )   
	    {
	    	totalchars+=strLine.length();	
	    	bLine=strLine; //copy for b
	    	
	    	//replace all \ chars with place holders
	    	bLine=bLine.replaceAll("\\\\\\\\", "BSBS"); 				//replace \\ with 4 chars
	    	bLine=bLine.replaceAll("\\\\x", "PHx"); 					//replace \x with 3 chars
	    	bLine=bLine.replaceAll("\\\\", "SGT"); 						//replace \ with 3 chars
	    	bstringchars+=(bLine.length()+4); 							//include chars added for replacing surrounding "" -> "\"\""
	    	
	    	
	    	strLine=strLine.replaceAll("\\\\\\\\", "p"); 				// replace \\ with 1 char
	    	strLine=strLine.replaceAll("\\\\x(\\d|[a-fA-F]){2}", "1"); 	//replace hex chars 1 char	   
	    	strLine=strLine.replaceAll("\\\\.", "V"); 					//replace single escaped chars with 1 char
	    	stringchars+=strLine.length()-2; 							//remove surrounding "" 
	    }
	    	

	    br.close();
	} 
	catch (Exception e) {
		e.printStackTrace();
	}
	System.out.println("total: "+totalchars);
	System.out.println("string: "+stringchars);
	System.out.println("stringb: "+bstringchars);
	System.out.println("total-stringchars "+(totalchars-stringchars));
	System.out.println("added to total in b "+(bstringchars-totalchars));
	
	
		
		
		
	}

}
