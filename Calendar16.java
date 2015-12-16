import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Calendar16 {
	static int[][] auntfacts=new int[501][10];
	public static void main(String[] args) {
		//search input: 
		auntfacts[0][0]=3;			//children
		auntfacts[0][1]=7;			//cats
		auntfacts[0][2]=2;			//dog1
		auntfacts[0][3]=3;			//dog2
		auntfacts[0][4]=0;			//dog3
		auntfacts[0][5]=0;			//dog4
		auntfacts[0][6]=5;			//goldfish
		auntfacts[0][7]=3;			//trees
		auntfacts[0][8]=2;			//cars
		auntfacts[0][9]=1;			//perfumes
		Pattern[] aff={
				Pattern.compile("children: (\\d+)"),
				Pattern.compile("cats: (\\d+)"),
				Pattern.compile("samoyeds: (\\d+)"),
				Pattern.compile("pomeranians: (\\d+)"),
				Pattern.compile("akitas: (\\d+)"),
				Pattern.compile("vizslas: (\\d+)"),
				Pattern.compile("goldfish: (\\d+)"),
				Pattern.compile("trees: (\\d+)"),
				Pattern.compile("cars: (\\d+)"),
				Pattern.compile("perfumes: (\\d+)")
		};
		Matcher m;
	  	try
	    {	  		
	    	Scanner scanner = new Scanner(new File("src/calendar16input.txt"));	    
	    	String strLine; 	
	    	for(int line=1; scanner.hasNextLine(); line++)
	    	{	    		
	    		strLine=scanner.nextLine();
	    		for(int i=0;i<10;i++)
	    		{
	    			m=aff[i].matcher(strLine);
	    			if(m.find())
	    			{
	    				auntfacts[line][i]=Integer.parseInt(m.group(1));
	    			}
	    			else
	    			{
	    				auntfacts[line][i]=-1;
	    			}
	    		}
	    			
	    	}	
		    scanner.close();
		    for(int i=1;i<=500;i++)
		    {
		    	if(matchauntfacts(i,false))
		    	{
		    		System.out.println("got a match for part1 at aunt: "+i);
		    	}
		    	if(matchauntfacts(i,true))
		    	{
		    		System.out.println("got a match for part2 at aunt: "+i);
		    	}
		    }
	    }
	    catch (Exception e) {e.printStackTrace();}
	}	
	static boolean matchauntfacts(int auntnumber, boolean part2)
	{
		for(int i=0;i<10;i++)
		{
			if(part2) 
			{
				if(i==1 || i==7)//remember more cats and trees than detected
				{
					if(auntfacts[auntnumber][i] <= auntfacts[0][i] && auntfacts[auntnumber][i] != -1)
						return false;
					else
						continue;
				}
				if(i==3 || i==6)//remember less dog2s and goldfish than detected
				{
					if(auntfacts[auntnumber][i] >= auntfacts[0][i] && auntfacts[auntnumber][i] != -1)
						return false;
					else
						continue;
				}
					
			}
			if(auntfacts[auntnumber][i]!=auntfacts[0][i] && auntfacts[auntnumber][i]!=-1)
			{
				return false;
			}
		}
		return true;
	}
}
