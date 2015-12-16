import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.*;

public class Calendar5 {

	public static void main(String[] args) {
		FileInputStream fstream;
		int goodstrings=0;
		int goodstringsb=0;
		
		//a) (input all lower case) 'good' strings contain: 3 vowels + at least one double char but never ab, cd, pq, or xy
		Pattern p1 = Pattern.compile("(.*[aeiou].*){3,}"); 				//contains at least 3 vowels 
		Pattern p2= Pattern.compile("(\\w)\\1+"); 						//contains at least 1 double char
		Pattern p3= Pattern.compile("(ab)|(cd)|(pq)|(xy)");				//contains no ab, cd, pq, or xy
		//b) 'good': any followed by any followed by repetition of first any AND any 2 letter combo twice (no overlap)
		Pattern p4=Pattern.compile("(\\w)(\\w)\\1+"); 					//any followed by any followed by repetition of first any
		Pattern p5=Pattern.compile("(\\w\\w).*\\1+"); 					//any 2 letter combo twice (no overlap)
		
		
		Matcher m;	
						
		try {
			fstream = new FileInputStream("src/calendar5input.txt");
		
	  	
			DataInputStream in = new DataInputStream(fstream);
	        BufferedReader br = new BufferedReader(new InputStreamReader(in));
	        String strLine;
	        //Read File Line By Line
	        while ((strLine = br.readLine()) != null)   {
	        	//b
	        	m = p4.matcher(strLine);
	        	if(m.find())
	        	{
	        		m=p5.matcher(strLine);
	        		if(m.find())
	        		{
	        			goodstringsb++;
	        		}
	        	}
	        	//a
	        	m = p3.matcher(strLine);
	        	if(m.find())
	        	{
	        		continue;
	        	}	
	        	m = p2.matcher(strLine);
	        	if(m.find())
	        	{
	        		m=p1.matcher(strLine);
	        		if(m.find())
	        		{
	        			goodstrings++;
	        		}	
	        	}	
	        	 
	        }
	        br.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("goodstrings: "+goodstrings);
		System.out.println("goodstringsb: "+goodstringsb);
	}

}
