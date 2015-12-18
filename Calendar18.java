import java.io.File;
import java.util.Scanner;

public class Calendar18 {
	static int size=100;
	static int border=2;
	static boolean[][] grid=new boolean[size+border][size+border];
	static boolean[][] gridb=new boolean[size+border][size+border];
	static boolean[][] next=new boolean[size+border][size+border];
	public static void main(String[] args) {		
	  	try
	    {	
	    	Scanner scanner = new Scanner(new File("src/calendar18input.txt"));	    
	    	String strLine="";
	    	for(int line=1; scanner.hasNextLine(); line++)
	    	{	    		
	    		strLine=scanner.nextLine();
	    		for(int i=1;i<strLine.length()+1;i++)
	    		{
	    			if(strLine.charAt(i-1)=='.')
	    			{
	    				grid[i][line]=false;
	    				gridb[i][line]=false;
	    			}
	    			else
	    			{
	    				grid[i][line]=true;
	    				gridb[i][line]=true;
	    			}
	    		}
	    	}	
		    scanner.close();
		    int steps=100;
		    for(int i=0;i<steps;i++)
		    {
		    	setNext();
		    	getNext();
		    }
		    System.out.println(count());
		    
		    //b
		    for(int i=0;i<grid.length;i++)
		    {
		    	for(int j=0;j<grid.length;j++)
		    	{
		    		grid[i][j]=gridb[i][j];
		    	}
		    }
		    grid[1][1]=true;
		    grid[1][grid.length-2]=true;
		    grid[grid.length-2][1]=true;
		    grid[grid.length-2][grid.length-2]=true;
		    for(int i=0;i<steps;i++)
		    {		    	
		    	setNext();
		    	getNext();
		    	grid[1][1]=true;
			    grid[1][grid.length-2]=true;
			    grid[grid.length-2][1]=true;
			    grid[grid.length-2][grid.length-2]=true;
		    }		    
		    System.out.println(count());
		    
	    			    
	    }
	    catch (Exception e) {e.printStackTrace();}
	}	
	static void setNext()
	{		
		int aN=0;
		 for(int i=1;i<grid.length-1;i++)
		 {
			 for(int j=1;j<grid.length-1;j++)
			 {
				 aN=activeNeighbors(i,j);
				 next[j][i]=((grid[j][i] && (aN==2 || aN==3)) || 
						 (!grid[j][i] && aN==3));
			 }
		 }
	}
	static void getNext()
	{
		for(int j=1;j<grid.length-1;j++)
    	{
    		for(int k=1;k<grid.length-1;k++)
    		{
    			grid[j][k]=next[j][k];
    		}
    	}
	}
	static int activeNeighbors(int i, int j)
	{
		int rv=0;
		for(int k=-1;k<2;k++)
		{
			for(int l=-1;l<2;l++)
			{
				if ((k!=0 || l!=0) && (grid[j+k][i+l]))
					rv++;				
			}
		}
		return rv;
	}
	static int count()
	{
		int rv=0;
		for(int i=1;i<grid.length-1;i++)
	    {
	    	for(int j=1;j<grid.length-1;j++)
	    	{
	    		if(grid[j][i])
	    		{
	    			rv++;
	    		}
	    	}
	    }
		return rv;
	}
	
}
