import java.io.File;
import java.util.Scanner;



public class Calendar15 {
	static int[][] in=new int[4][5]; //ingredient , value
	static int[] ti=new int[4]; 
	
	public static void main(String[] args) {
	  	try
	    {	  		
	    	Scanner scanner = new Scanner(new File("src/calendar15input.txt"));	    
	    	String strLine; 	
	    	for(int line=0; scanner.hasNextLine(); line++)
	    	{
	    		strLine=scanner.nextLine();
	    		String[] temp=strLine.split(" ");
	    		for(int i=1;i<5;i++)
	    		{
	    			in[line][i-1]=Integer.parseInt(temp[i*2].substring(0, temp[i*2].length()-1));
	    		}
	    		in[line][4]=Integer.parseInt(temp[10]);
	    	}	
		    scanner.close();
		    long startTime = System.currentTimeMillis();		    
		    System.out.println("answer a: "+getMax(false)+" in "+(System.currentTimeMillis()-startTime)+"ms");
		    //b)		
		    startTime = System.currentTimeMillis();
		    System.out.println("answer b: "+getMax(true)+" in "+(System.currentTimeMillis()-startTime)+"ms");
	    }
	    catch (Exception e) {e.printStackTrace();}
	}
	static int getMax(boolean cal500)
	{
		int max=0;
		int vala=0;
		for(int i=0;i<=100;i++)
	    {
	    	for(int j=0;j<=100-i;j++)
	    	{
	    		for(int k=0;k<=100-i-j;k++)
	    		{
	    			vala=getvalue(i,j,k,100-i-j-k,cal500);
	    			if(vala>max)
	    			{
	    				max=vala;	
	    			}
	    		}
	    	}
	    }
		return max;
	}
	static int getvalue(int a0, int a1, int a2, int a3, boolean cal500)
	{
		if(cal500)
		{
			if(a0*in[0][4]+a1*in[1][4]+a2*in[2][4]+a3*in[3][4] != 500)
			{
				return 0;
			}			
		}
		for(int i=0;i<4;i++)
		{
			ti[i]=Math.max(a0*in[0][i]+a1*in[1][i]+a2*in[2][i]+a3*in[3][i],0);
		}
		return ti[0]*ti[1]*ti[2]*ti[3];		
	}
}
