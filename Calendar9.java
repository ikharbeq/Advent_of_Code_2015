import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Calendar9 {
	static int shortest=0xFFFFFFF;
	static String sPath="";
	static int longest=0;
	static String lPath="";
	static int[][] distance=new int[8][8];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	FileInputStream fstream;
	
	try {
		fstream = new FileInputStream("src/calendar9input.txt");
		
	  	
		DataInputStream in = new DataInputStream(fstream);
	    BufferedReader br = new BufferedReader(new InputStreamReader(in));
	    String strLine;	        
	    int i=0,j=1,max=7;
	    //Read File Line By Line
	    while ((strLine = br.readLine()) != null )   
	    {
	    	 String[] temp=strLine.split(" = ");	    	 
	    	 distance[i][j]=Integer.parseInt(temp[1]);
	    	 distance[j][i]=distance[i][j];
	    	 if(j<max)
	    	 {
	    		 j++;
	    	 }
	    	 else
	    	 {
	    		 i++;
	    		 j=i+1;
	    	 }	    	 
	    }
	    	

	    br.close();
	} 
	catch (Exception e) {
		e.printStackTrace();
	}
	//next time use an existing permutation function ...
	for(int i=0;i<7;i++)//for each start location do:
	{
		String goalstoreach="01234567";
		findroute(goalstoreach.replaceAll(i+"", ""),0,i, i+"");		
	}
	System.out.println("shortest path was: "+shortest+ " path taken: "+sPath);
	System.out.println("longest path was: "+longest+ " path taken: "+lPath);
	
		
		
		
	}
	static void findroute(String goalstopick, int curdistance, int location, String path)
	{
		//if(curdistance>shortest) //removed to be able to do b)
			//return;		
		if(goalstopick.length()<1)
		{
			if(curdistance>longest)
			{
				longest=curdistance;
				lPath=path;
			}
			if(curdistance<shortest)
			{
				shortest=curdistance;
				sPath=path;
			}						
		}
		for(int i=0;i<goalstopick.length();i++)
		{
			String next=goalstopick.substring(i,i+1);
			int nextI=Integer.parseInt(next);
			findroute(goalstopick.replaceAll(next,""),curdistance+distance[location][nextI],nextI,path+next);
		}
		return;
	}

}
