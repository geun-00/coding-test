import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
        
        for(int i = n; i >= 0; i--) {
        	System.out.print(i + " ");
        }
	
	}
}