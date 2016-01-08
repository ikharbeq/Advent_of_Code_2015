import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calendar24 {
	static int total=0;
	static int[] in=new int[29];
	static List<Long> groups=new ArrayList<Long>();
	static int[] totest;
	
	public static void main(String[] args) {		
	  	try
	    {	
	    	Scanner scanner = new Scanner(new File("src/calendar24input.txt"));    
	    	
	    	for(int line=0; scanner.hasNextLine(); line++)
	    	{	
	    		in[line]=Integer.parseInt(scanner.nextLine());	   
	    		total+=in[line];
	    	}
		    scanner.close();	
		    //part1:
		    total/=3;  
		    solve(2);
		    //part2:
		    total=(total*3)/4;
		    solve(2);  
	    }
	    catch (Exception e) {e.printStackTrace();}
	}
	static void solve(int startwith)
	{
		long minqe=Long.MAX_VALUE;
	    int mincombo=0;
	    long temp=1;
		groups=new ArrayList<Long>();
		while (groups.size()<1)
		{
			combine(in.length,total,startwith,0);
			startwith+=2;
		}
	    minqe=Long.MAX_VALUE;
	    mincombo=0;
	    temp=1;
	    for(int i=0;i<groups.size();i++)
	    {
	    	temp=1;
	    	for(int j=in.length-1;j>=0;j--)
	    	{
	    		if (((groups.get(i)>>j)&1)>0)
	    		{
	    			temp*=in[j];
	    		}
	    	}
	    	if(temp<minqe)
	    	{
	    		minqe=temp;
	    		mincombo=i;
	    	}
	    }
	    temp=1;
	    System.out.print("min qe combo: ");
	    for(int j=in.length-1;j>=0;j--)
    	{
    		if (((groups.get(mincombo)>>j)&1)>0)
    		{
    			System.out.print(in[j]+" ");
    			temp*=in[j];
    		}
    	}
	    System.out.println(" product is "+temp);
	}
	static void combine(int start, int rm, int max, long out)
	{
		if(rm==0)
		{
			if(cancombine(out))
				groups.add(out);
			return;
		}
		if(max<=0)
		{
			return;
		}
		for(int i=start-1;i>=0;i--)
		{
			if(rm-in[i]>=0)
				combine(i,rm-in[i],max-1,(out|(1<<i)));
		}
	}
	static boolean cancombine(Long used)
	{
		totest=new int[in.length];
		int remaininginputs=0;
		for(int i=in.length-1;i>=0;i--)
		{
			if(((used>>i)&1)==0)
			{
				totest[i]=in[i];
				remaininginputs++;
			}
		}
		if(remaininginputs==0)
			return true;		
		return trycombine(in.length, total, used);		
	}
	static boolean trycombine(int start, int rm, long used)
	{
		if(rm==0)
		{
			return true; //true for part 1 - part 2 works but based on input not because this checks only 2 groups + rest.
		}
		for (int i=start-1;i>=0;i--)
		{			
			if (rm-in[i]>=0)
			{
				if (trycombine(i,rm-totest[i],(used|(1<<i))));
					return true;
			}
		}
		return false;
	}	
}
