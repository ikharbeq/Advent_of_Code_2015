public class Calendar25 {
	
	public static void main(String[] args) {	
	  	long current=20151125;
	  	long mult=252533;
	  	long mod=33554393;
	  	
	  	int row=1,column=1,next=2;
	  	
	  	
	  	while (true)
	  	{
	  		while(row>1)
	  		{
	  			row--;
	  			column++;	
	  			current=(current*mult)%mod;
	  			if(row==3010 && column == 3019)
	  			{
	  				System.out.println(current);
	  				return;
	  			}
	  		}
	  		column=1;
	  		row=next;
	  		next++;
	  		current=(current*mult)%mod;
	  	}
	}
	
}
