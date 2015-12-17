import java.io.File;
import java.util.Scanner;

public class Calendar17 {
	static int[] in=new int[20];
	static int combos=0;
	static int[] len=new int[255];
	static int min=255;
	public static void main(String[] args) {		
	  	try
	    {	  		
	    	Scanner scanner = new Scanner(new File("src/calendar17input.txt"));	    
	    	for(int line=0; scanner.hasNextLine(); line++)
	    	{	    		
	    		in[line]=Integer.parseInt(scanner.nextLine());
	    	}	
		    scanner.close();
	    	rc(150,0,0);	
		    System.out.println(combos+ " combinations detected, " + len[min] +" combos required just: "+min);		    
	    }
	    catch (Exception e) {e.printStackTrace();}
	}	
	static void rc(int rm, int start, int cl)
	{		
		for(int i=start;i<20;i++)
		{
			if(rm-in[i]==0)
			{
				len[cl+1]++;
				if(cl+1<min)
					min=cl+1;
				combos++;				
				continue;
			}
			if(rm-in[i]<0)
			{
				continue;
			}
			if(rm-in[i]>0)
			{
				rc(rm-in[i],i+1,cl+1);
			}
		}			
	}	
}
