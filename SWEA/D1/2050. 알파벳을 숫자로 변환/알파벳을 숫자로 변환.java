import java.util.*;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);
		
        String s = sc.next();
        
       for (int i = 0; i < s.length(); i++) {
            System.out.print((s.charAt(i) - 'A' + 1) + " ");
        }
	}
}