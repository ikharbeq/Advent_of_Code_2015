import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Calendar4 {

	public static void main(String[] args) {
		
		
		String puzzleinput="ckczppom";
		int attemptcounter=0;
		boolean solved=false;
		boolean solved5zeroes=false;
		while(!solved)
		{
			attemptcounter++;
			int lead0s=checkmd5forlead0s(puzzleinput+attemptcounter);
			if(!solved5zeroes && lead0s>=5)
			{
				solved5zeroes=true;
				System.out.println("5 lead 0 first at: "+attemptcounter);
			}
			if(lead0s>=6)
			{
				solved=true;
				System.out.println("6 lead 0 first at: "+attemptcounter);
			}
		}		
	}
	
	static int checkmd5forlead0s(String input)
	{
		String plaintext = input;
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m.reset();
		m.update(plaintext.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1,digest);
		String hashtext = bigInt.toString(16);
		return 32-hashtext.length();		
	}

}
