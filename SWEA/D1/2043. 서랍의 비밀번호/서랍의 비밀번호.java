import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
	
		Scanner sc = new Scanner(System.in);
		
        int  p = sc.nextInt();
        int  k = sc.nextInt();
        
        int result = 1;
        
        for(int i = k; i < p; i++){
            result++;
        }
        System.out.println(result);
	}
}