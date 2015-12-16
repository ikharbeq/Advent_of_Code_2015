import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Calendar7 {
	//solved with array and map just because I wanted to
	//each output a -> 1 up to zz -> 26*27+26+1 has one string that determines it's input
	static String[] inputfor=new String[26*27+26+1];
	static String[] inputforb;
	static Map<String,String> inputmap=new HashMap<String,String>();
	static Map<String,String> inputmapb;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	FileInputStream fstream;
						
	try {
		fstream = new FileInputStream("src/calendar7input.txt");
		
	  	
		DataInputStream in = new DataInputStream(fstream);
	    BufferedReader br = new BufferedReader(new InputStreamReader(in));
	    String strLine;	        
	        
	    //Read File Line By Line
	    while ((strLine = br.readLine()) != null)   {
	    	//split line around " -> " save the input part of the string to the correct variable for later
	        String[] temp=strLine.split("\\s->\\s");
	        inputfor[convertToNumber(temp[1])]=temp[0];	      
	        inputmap.put(temp[1], temp[0]);
	        }
	    br.close();
	} 
	catch (Exception e) {
		e.printStackTrace();
	}
	long startTime = System.currentTimeMillis();
	inputforb=inputfor.clone();
	//now figure out what is a:
	System.out.println("in the end a is: "+whatis(1));
	System.out.println("calc done in "+ (System.currentTimeMillis()-startTime)+"ms");
	//reset system for b) then add the new value for b and recalculate a
	startTime = System.currentTimeMillis();
	String temp=inputfor[1];
	inputfor=inputforb;
	inputfor[2]=temp;	
	System.out.println("in the end for b) a is: "+whatis(1));
	System.out.println("calc done in "+ (System.currentTimeMillis()-startTime)+"ms");
	
	//same as above but with map instead of array to see how much faster it is
	startTime = System.currentTimeMillis();
	inputmapb=new HashMap<String,String>(inputmap);
	System.out.println("in the end a is: "+mapWhatIs("a"));
	System.out.println("calc done in "+ (System.currentTimeMillis()-startTime)+"ms");
	//part b
	startTime = System.currentTimeMillis();
	String temp2=inputmap.get("a");
	inputmap=inputmapb;
	inputmap.put("b",temp2);
	System.out.println("in the end for b) a is: "+mapWhatIs("a"));
	System.out.println("calc done in "+ (System.currentTimeMillis()-startTime)+"ms");
	
	
		
		
		
	}
	static String mapWhatIs(String wire)
	{
		//System.out.println("what is: "+wire+"? figure out: "+inputmap.get(wire));
		//input is just a number? -> done
		if(inputmap.get(wire).matches("\\d+"))
		{
			return inputmap.get(wire);
		}
		//NOT something?
		if(inputmap.get(wire).contains("NOT"))
		{
			//invert and limit to max 16 bit
			String result=""+((~(Integer.parseInt(mapWhatIs(inputmap.get(wire).substring(4))))) & 0xFFFF);
			//System.out.println(wire+" is "+result);
			inputmap.put(wire,result);
			return result;
		}
		//RSHIFT
		if(inputmap.get(wire).contains("RSHIFT"))
		{
			String[] temp=inputmap.get(wire).split("\\sRSHIFT\\s");		
			String result=""+(Integer.parseInt(mapWhatIs(temp[0])) >> Integer.parseInt(temp[1]));
			//System.out.println(wire+" is "+result);
			inputmap.put(wire,result);
			return result;
		}
		//LSHIFT
		if(inputmap.get(wire).contains("LSHIFT"))
		{
			String[] temp=inputmap.get(wire).split("\\sLSHIFT\\s");			
			//shift left and limit to 16bit
			String result=""+(Integer.parseInt(mapWhatIs(temp[0])) << Integer.parseInt(temp[1]) & 0xFFFF);
			//System.out.println(wire+" is "+result);
			inputmap.put(wire,result);
			return result;
		}
		//AND
		if(inputmap.get(wire).contains("AND"))
		{
			String[] temp=inputmap.get(wire).split("\\sAND\\s");			
			//what is part 1 & what is part 2 - skip the what is if it is just a number
			String result= ""+(
					(temp[0].matches("\\d+")?
							Integer.parseInt(temp[0])
							:Integer.parseInt(mapWhatIs(temp[0])))
					&
					(temp[1].matches("\\d+")?
							Integer.parseInt(temp[1])
							:Integer.parseInt(mapWhatIs(temp[1]))							)
					);
			//System.out.println(wire+" is "+result);
			inputmap.put(wire,result);
			return result;
		}
		//OR
		if(inputmap.get(wire).contains("OR"))
		{
			String[] temp=inputmap.get(wire).split("\\sOR\\s");			
			//what is part 1 | what is part 2 - skip the what is if it is just a number
			String result= ""+(
					(temp[0].matches("\\d+")?
							Integer.parseInt(temp[0])
							:Integer.parseInt(mapWhatIs(temp[0])))
					|
					(temp[1].matches("\\d+")?
							Integer.parseInt(temp[1])
							:Integer.parseInt(mapWhatIs(temp[1]))							)
					);
			//System.out.println(wire+" is "+result);
			inputmap.put(wire,result);
			return result;
		}
		//input wire -> output wire
		if(inputmap.get(wire).length()<3)
		{
			String result= mapWhatIs(inputmap.get(wire));
			//System.out.println(wire+" is "+result);
			inputmap.put(wire,result);
			return result;
		}
		System.out.println("couldn't figure out what to do with: what is: "+wire);
		return "";
	}
	static int whatis(int wire)
	{
		//System.out.println("what is: "+inputwirename(wire)+"? figure out: "+inputfor[wire]);
		//input is just a number? -> done
		if(inputfor[wire].matches("\\d+"))
		{
			return Integer.parseInt(inputfor[wire]);
		}
		//NOT something?
		if(inputfor[wire].contains("NOT"))
		{
			//invert and shorten down to max 16 bit
			int result=(~(whatis(convertToNumber(inputfor[wire].substring(4))))) & 0xFFFF;
			//System.out.println(inputwirename(wire)+" is "+result);
			inputfor[wire]=result+"";
			return result;
		}
		//RSHIFT
		if(inputfor[wire].contains("RSHIFT"))
		{
			String[] temp=inputfor[wire].split("\\sRSHIFT\\s");		
			int result=whatis(convertToNumber(temp[0])) >> Integer.parseInt(temp[1]);
			//System.out.println(inputwirename(wire)+" is "+result);
			inputfor[wire]=result+"";
			return result;
		}
		//LSHIFT
		if(inputfor[wire].contains("LSHIFT"))
		{
			String[] temp=inputfor[wire].split("\\sLSHIFT\\s");			
			//shift left and limit to 16bit
			int result=(whatis(convertToNumber(temp[0])) << Integer.parseInt(temp[1])) & 0xFFFF;
			//System.out.println(inputwirename(wire)+" is "+result);
			inputfor[wire]=result+"";
			return result;
		}
		//AND
		if(inputfor[wire].contains("AND"))
		{
			String[] temp=inputfor[wire].split("\\sAND\\s");			
			//what is part 1 & what is part 2 - skip the what is if it is just a number
			int result= (
					(temp[0].matches("\\d+")?
							Integer.parseInt(temp[0])
							:whatis(convertToNumber(temp[0])))
					&
					(temp[1].matches("\\d+")?
							Integer.parseInt(temp[1])
							:whatis(convertToNumber(temp[1])))
					);
			//System.out.println(inputwirename(wire)+" is "+result);
			inputfor[wire]=result+"";
			return result;
		}
		//OR
		if(inputfor[wire].contains("OR"))
		{
			String[] temp=inputfor[wire].split("\\sOR\\s");			
			//what is part 1 | what is part 2 - skip the what is if it is just a number
			int result=(
					(temp[0].matches("\\d+")?
							Integer.parseInt(temp[0])
							:whatis(convertToNumber(temp[0])))
					|
					(temp[1].matches("\\d+")?
							Integer.parseInt(temp[1])
							:whatis(convertToNumber(temp[1])))
					);
			//System.out.println(inputwirename(wire)+" is "+result);
			inputfor[wire]=result+"";
			return result;
		}
		//input wire -> output wire
		if(inputfor[wire].length()<3)
		{
			int result= whatis(convertToNumber(inputfor[wire]));
			//System.out.println(inputwirename(wire)+" is "+result);
			inputfor[wire]=result+"";
			return result;
		}
		System.out.println("couldn't figure out what to do with: what is: "+inputfor[wire]);
		return 0;
		
	}
	//for debug
	static String inputwirename(int wire)
	{
		if(wire<27)
		{
			char temp=(char) wire;
			temp+=96;
			return temp+"";
		}
		else
		{
			int tempint=wire/27;
			char temph=(char) tempint;
			temph+=96;
			tempint=wire%27;
			char templ=(char) tempint;
			templ+=96;
			return ""+temph+templ;
		}
	}
	
	
	//use a-z as 1-26 in base 27 with a missing char for 0 but with the input it isn't required
	static int convertToNumber(String out)
	{		
		if(out.length()<2)
		{					
			return (out.charAt(0)-96);
		}
		else
		{			
			return (out.charAt(0)-96)*27+(out.charAt(1)-96);
		}		
	}

}
