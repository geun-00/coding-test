import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);
		
        int a = sc.nextInt();
        int b = sc.nextInt();
        
        if(Math.abs(a - b) == 2) {
        	System.out.println((a > b) ? "B" : "A");
            return;
        }
        
        System.out.println((a > b) ? "A" : "B");
	
	}
}