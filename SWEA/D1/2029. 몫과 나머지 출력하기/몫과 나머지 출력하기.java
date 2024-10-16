import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);
		
        int t = sc.nextInt();
        
        for(int i = 1; i <= t; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
        	System.out.printf("#%d %d %d\n", i, a / b, a%b);
        }
	}
}