import java.util.*;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
        
        for(int i = 1; i <= t; i++) {
        	int max = Integer.MIN_VALUE;
            
            for(int j = 0; j < 10; j++) {
            	max = Math.max(max, sc.nextInt());
            }
                               
			System.out.printf("#%d %d\n", i, max);
        }

	}
}