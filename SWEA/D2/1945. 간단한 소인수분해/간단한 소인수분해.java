import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{

        StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        int[] d = {2, 3, 5, 7, 11};
        
        for(int  i =1; i <= t; i++) {
            
            sb.append("#").append(i).append(" ");
            
        	int n = sc.nextInt();
  
            for(int j = 0; j < 5; j++) {
                int count = 0;
                while(n % d[j] == 0) {
                	count++;
                    n /= d[j];
                }
                sb.append(count).append(" ");
            }
            
            sb.append("\n");
        }
        
        System.out.print(sb);

	}
}