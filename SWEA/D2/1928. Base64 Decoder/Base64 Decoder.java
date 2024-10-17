import java.util.*;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        sc.nextLine();
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = 1; i <= t; i++) {
        	String s = sc.nextLine();
            
            Base64.Decoder decoder = Base64.getDecoder();
            
            byte[] decode = decoder.decode(s);
            
            String str = new String(decode);
            
            sb.append("#").append(i).append(" ").append(str).append("\n");
        }
        
        System.out.print(sb);

	}
}