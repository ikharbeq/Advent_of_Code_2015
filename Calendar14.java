import java.io.File;
import java.util.Scanner;



public class Calendar14 {
	static int[] travelspeed=new int[9];
	static int[] traveltime=new int[9];
	static int[] resttime=new int[9];	
	static int[] points=new int[9];
	
	public static void main(String[] args) {
		int comparetime=2503;
	  	try
	    {	  		
	    	Scanner scanner = new Scanner(new File("src/calendar14input.txt"));	    
	    	String strLine; 	
	    	for(int line=0; scanner.hasNextLine(); line++)
	    	{
	    		strLine=scanner.nextLine();
	    		String[] temp=strLine.split(" ");
	    		travelspeed[line]=Integer.parseInt(temp[3]);
	    		traveltime[line]=Integer.parseInt(temp[6]);
	    		resttime[line]=Integer.parseInt(temp[13]);
	    	}	
		    scanner.close();		    	        
		    System.out.println("max dist: " +distance(comparetime,false));	
		    //b) get distance for every second and add points to the leaders
		    for(int t=1;t<=comparetime;t++)
	    	{
		    	int leader=distance(t,true);
		        for(int i=0;i<travelspeed.length;i++)
		        {
		        	if((leader&(1<<i))>0)
		        	{
		        		points[i]++;		        		
		        	}
		        }		        
	    	}
		    int max=0;
		    for(int i=0;i<travelspeed.length;i++)
		    {
		    	if(points[i]>max)
		    		max=points[i];
		    }
		    System.out.println("max points "+max);
	    }
	    catch (Exception e) {e.printStackTrace();}
	}
	static int distance(int time, boolean getleaders)
	{
		int max = 0;
		int leaders=0;
        for(int i=0;i<travelspeed.length;i++)
        {
        	int distance=time/(traveltime[i]+resttime[i])*(travelspeed[i]*traveltime[i])
        			+(Math.min(time%(traveltime[i]+resttime[i]), traveltime[i])*travelspeed[i]);
        	if(distance==max)
        		leaders|=1<<i;
        	if(distance>max)
        	{
        		max=distance;
        		leaders=1<<i;
        	}	        	
        }
        if(getleaders)
        	return leaders;
    	return max;
	}
}
