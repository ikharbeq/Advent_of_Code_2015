import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.*;

public class Calendar6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileInputStream fstream;
		boolean[][] lights=new boolean[1000][1000];
		int[][] lightb=new int[1000][1000];
		
		// find out which lights get changed in a line
		Pattern p=Pattern.compile("\\d+"); 					//find 1 or more digits
		
		
		Matcher m;	
						
		try {
			fstream = new FileInputStream("src/calendar6input.txt");
		
	  	
			DataInputStream in = new DataInputStream(fstream);
	        BufferedReader br = new BufferedReader(new InputStreamReader(in));
	        String strLine;
	        
	        int[] readcoords=new int[4];
	        //Read File Line By Line
	        while ((strLine = br.readLine()) != null)   {
	        	//find the numbers for the line
	        	m = p.matcher(strLine);
	        	for(int i=0;m.find();i++)
	        	{
	        		//System.out.println(strLine+" "+m.group());
	        		readcoords[i]=Integer.parseInt(m.group());
	        		if(i>1)
	        			readcoords[i]++; //region to modify includes upper bound
	        	}
	        	//now apply the appropriate action to the selected region
	        	if(strLine.contains("on"))
	        	{
	        		for(int i=readcoords[0];i<readcoords[2];i++)
	        		{
	        			for(int j=readcoords[1];j<readcoords[3];j++)
	        			{
	        				lights[i][j]=true;
	        				lightb[i][j]+=1;
	        			}
	        		}
	        		continue;
	        	}
	        	if(strLine.contains("off"))
	        	{
	        		for(int i=readcoords[0];i<readcoords[2];i++)
	        		{
	        			for(int j=readcoords[1];j<readcoords[3];j++)
	        			{
	        				lights[i][j]=false;
	        				if(lightb[i][j]>0)
	        					lightb[i][j]-=1;
	        			}
	        		}
	        		continue;
	        	}
	        	if(strLine.contains("toggle"))
	        	{
	        		for(int i=readcoords[0];i<readcoords[2];i++)
	        		{
	        			for(int j=readcoords[1];j<readcoords[3];j++)
	        			{
	        				lights[i][j]=!lights[i][j];
	        				lightb[i][j]+=2;
	        			}
	        		}
	        		continue;
	        	}
	        	
	        	 
	        }
	        br.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		//calculate results
		int lightsum=0;
		int lightsumb=0;
		for(int i=0;i<1000;i++)
		{
			for(int j=0;j<1000;j++)
			{
				if(lights[i][j])
				{
					lightsum++;
				}
				lightsumb+=lightb[i][j];
			}
		}
		System.out.println(lightsum+ " lights on");
		System.out.println(lightsumb+ " is the lightsum for b)");
	}

}
