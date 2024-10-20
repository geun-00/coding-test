import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();        
        
        for(int i = 1; i <= t; i++) {
        	int n = sc.nextInt();
            
            int sum = 0;
            for(int j = 1; j <= n; j++) {
            	sum += (j % 2 == 1) ? j : (-j);
            }
            
            System.out.printf("#%d %d\n", i, sum);
        }

	}
}