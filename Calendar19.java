import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calendar19 {
	static String[] in=new String[44];
	static String[] out=new String[44];
	static String start; 
	static ArrayList<String> results=new ArrayList<String>();
	
	public static void main(String[] args) {		
	  	try
	    {	
	    	Scanner scanner = new Scanner(new File("src/calendar19input.txt"));	    
	    	String strLine="";
	    	for(int line=0; scanner.hasNextLine(); line++)
	    	{	    		
	    		strLine=scanner.nextLine();
	    		if(line<43)
	    		{
	    			String[] temp=strLine.split(" => ");
	    			in[line]=temp[0];
	    			out[line]=temp[1];
	    		}
	    		if(!scanner.hasNextLine())
	    			start=strLine;
	    	}	
		    scanner.close();
		    
		    Pattern p;
		    Matcher m;
		    StringBuilder t;
		    int count=0;
		    for(int i=0;i<43;i++)
		    {		    	
		    	p=Pattern.compile(in[i]);
		    	m=p.matcher(start);
		    	while(m.find())
		    	{
		    		t=new StringBuilder();
		    		t.append(start.substring(0,m.start()));
		    		t.append(out[i]);
		    		t.append(start.substring(m.end()));
		    		if(!results.contains(t.toString()))
		    		{
		    			results.add(t.toString());
		    			count++;
		    		}
		    	}
		    }
		    System.out.println("1) "+count);
		    //part2 
		    count=0;
		    int max=0;
		    int maxat=0;
		    String detached;
		    while (start.length()>1)
		    {
		    	count++;
		    	max=0;
		    	maxat=0;
		    	detached=new String();
		    	
		    	while(max<1 && start.length()>0)
		    	{
		    		for(int i=0;i<43;i++)
			    	{
			    		if(start.endsWith(out[i]))
			    		{
			    			if(out[i].length()>max)
			    			{
			    				max=out[i].length();
			    				maxat=i;
			    			}
			    		}
			    	}
		    		if(max<1) //ignore last char -> check again
		    		{		    			
		    			detached=start.charAt(start.length()-1)+detached;
		    			start=start.substring(0, start.length()-1);
		    		}
		    	}	
		    	//System.out.println("replaced: "+out[maxat]+" with "+in[maxat]);	    	
		    	if(max<1)
		    	{
		    		System.out.println("doesn't match anything "+start+detached);
		    		break;
		    	}
		    	start=start.substring(0, start.length()-max)+in[maxat]+detached;
		    }		
		  	System.out.println("2) "+count);    
	    }
	    catch (Exception e) {e.printStackTrace();}
	}		
}
