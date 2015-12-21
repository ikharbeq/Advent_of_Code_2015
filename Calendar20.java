public class Calendar20 {
	
	public static void main(String[] args) {		
		int in=34000000;
		in/=10;
		int t=1;
		while(getDivSum(t,t)<in)
			t++;
		System.out.println(t);
		t=1;
		in*=10;
		in/=11;
		while(getDivSum(t,50)<in)
			t++;
		System.out.println(t);
	    	
	}	
	static int getDivSum(int in,int maxi)
	{
		int r=in;
		if(maxi>=in)
			r+=1;
		for(int i=2;i*i<=in && i<=maxi;i++)
		{
			if(in%i==0)
			{	
				r+=(in/i);
				if(i*i!=in && in/i<maxi)
					r+=i;
			}
		}		
		return r;
	}
}
