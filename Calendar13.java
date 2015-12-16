import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.google.common.collect.Collections2;


public class Calendar13 {
	public static void main(String[] args) {	

		Map<String, Integer> happiness=new HashMap<String, Integer>();
		Map<String, Integer> happinessb=new HashMap<String, Integer>();
		Set<String> persons=new HashSet<String>();
		Set<String> personsb=new HashSet<String>();
	  	try
	    {
	    	Scanner scanner = new Scanner(new File("src/calendar13input.txt"));	    
	    	String strLine;		    
	    	while(scanner.hasNextLine())
	    	{
	    		strLine=scanner.nextLine();
	    		String[] temp=strLine.split(" ");
	    		temp[10]=temp[10].substring(0, temp[10].length()-1);
	    		if(temp[2].matches("lose"))
	    		{
	    			happiness.put(temp[0]+temp[10], Integer.parseInt(temp[3])*-1);
	    			happinessb.put(temp[0]+temp[10], Integer.parseInt(temp[3])*-1);
	    		}
	    		else
	    		{
	    			happiness.put(temp[0]+temp[10], Integer.parseInt(temp[3]));
	    			happinessb.put(temp[0]+temp[10], Integer.parseInt(temp[3]));
	    		}
	    		if(!persons.contains(temp[0]))
	    		{
	    			persons.add(temp[0]);
	    			personsb.add(temp[0]);
	    			
	    			happinessb.put(temp[0]+"You",0);
	    			happinessb.put("You"+temp[0],0);
	    		}	    		
	    	}	    	
	    	personsb.add("You");
	    	
	    	int max = 0;
	        for(List<String> perm : Collections2.permutations(persons)) {
	            int total = 0;
	            for (int i = 0; i < perm.size(); i++) {
	            	if(i>0 && i<perm.size()-1)
	            	{
	            		total += happiness.get(perm.get(i)+perm.get(i+1));
	            		total += happiness.get(perm.get(i)+perm.get(i-1));
	            	}
	            	else
	            	{
	            		if(i==0)
	            		{
	            			total += happiness.get(perm.get(i)+perm.get(i+1));
	            			total += happiness.get(perm.get(i)+perm.get(perm.size()-1));
	            		}
	            		else
	            		{
	            			total += happiness.get(perm.get(i)+perm.get(0));
	            			total += happiness.get(perm.get(i)+perm.get(i-1));
	            		}
	            	}
	            }
	            if (total > max) {
	                max = total;
	            }
	        }
		    System.out.println("max happiness - a: " +max);	
	        persons=personsb;
	        happiness=happinessb;
	        max = 0;
	        for(List<String> perm : Collections2.permutations(persons)) {
	            int total = 0;
	            for (int i = 0; i < perm.size(); i++) {
	            	if(i>0 && i<perm.size()-1)
	            	{
	            		total += happiness.get(perm.get(i)+perm.get(i+1));
	            		total += happiness.get(perm.get(i)+perm.get(i-1));
	            	}
	            	else
	            	{
	            		if(i==0)
	            		{
	            			total += happiness.get(perm.get(i)+perm.get(i+1));
	            			total += happiness.get(perm.get(i)+perm.get(perm.size()-1));
	            		}
	            		else
	            		{
	            			total += happiness.get(perm.get(i)+perm.get(0));
	            			total += happiness.get(perm.get(i)+perm.get(i-1));
	            		}
	            	}
	            }
	            if (total > max) {
	                max = total;
	            }
	        }
		    System.out.println("max happiness - b: " +max);	
		    scanner.close();
	    }
	    catch (Exception e) {e.printStackTrace();}
	}
}
