import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calendar10 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	String input="3113322113";
	Pattern p=Pattern.compile("(\\d)\\1*");	
	Matcher m;
	long startTime = System.currentTimeMillis();
	for(int i=0;i<50;i++)
	{		
		m=p.matcher(input);
		StringBuilder nextInput=new StringBuilder();
		while (m.find())
		{
			nextInput.append(m.group().length());
			nextInput.append(m.group(1)); 
		}
		input=nextInput.toString();
		if(i==39 || i==49)
		{
			System.out.println("string length after: "+(i+1)+" is "+input.length()+" time required: "+ (System.currentTimeMillis()-startTime)+"ms");
		}
	}
		
	}
	

}
