import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calendar11 {

	static Pattern ps[]= {
			Pattern.compile("(.)\\1+.*(.)\\2+"),
			Pattern.compile("[iol]")
	};
	
	public static void main(String[] args) {
		String input="vzbxkghb";
		while (!matches(input))
		{
			input=inc(input);
		}
		System.out.println(input);
		input=inc(input);
		while (!matches(input))
		{
			input=inc(input);
		}
		System.out.println(input);
	}
	
	static String inc (String in)
	{
		//read input as base 36 (0-z), add 1, back to string, replace 0->a
		return Long.toString(Long.parseLong(in, 36)+1, 36).replaceAll("0", "a");
	}
	
	static boolean matches(String in)
	{		
		Matcher m;
		for(int i=0;i<2;i++)
		{
			m=ps[i].matcher(in);
			if(i==0 && !m.find()) 		//find 2 pairs
				return false;
			if(i>0 && m.find())  		//find forbidden chars
				return false;
		}
		char c=in.charAt(0);
		int combo=1;
		for(int i=1;i<in.length();i++)	//find 3 char combo c,c+1,c+2 
		{
			if(in.charAt(i)==(c+combo))
			{
				combo++;
			}
			else
			{
				c=in.charAt(i);
				combo=1;
			}
			if(combo>=3)
				return true;

		}
		return false;		
	}	
}
