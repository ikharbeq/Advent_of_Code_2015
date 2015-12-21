public class Calendar21 {
	static int max=0;
	static int min=500;
	static int[] statcost={8,10,25,40,74,13,31,53,75,102,25,50,100,20,40,80};
	static int[] statvals={4,5,6,7,8,1,2,3,4,5,1,2,3,1,2,3};
	static int bossstats=8+2;
	public static void main(String[] args) {	
		getequip(0,0,0,true);
		System.out.println(min);
		getequip(0,0,0,false);
		System.out.println(max);
	}		
	static void getequip(int start, int curcost, int statsum, boolean getmin)
	{
		if(curcost>min && getmin)
			return;		
		if(statsum>=bossstats) 
		{
			min=curcost;
			return;
		}
		if(curcost>max && !getmin)
		{
			max=curcost;
		}			
		for(int i=start;i<statvals.length && ( i<5 || statsum>0 ) ;i++)
		{
			if(i<5) //1 weapon
			{
				getequip(5,curcost+statcost[i],statsum+statvals[i],getmin);
				continue;
			}				
			if(i<10) //0-1 armor
			{
				getequip(10,curcost+statcost[i],statsum+statvals[i],getmin);
				continue;
			}
			getequip(i+1,curcost+statcost[i],statsum+statvals[i],getmin);
		}
	}
}
