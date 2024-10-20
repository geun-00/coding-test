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
            
            boolean[] used = new boolean[10];
            int a = n;
            
            while(true){
            	int temp = n;
                while(temp > 0) {
                	used[temp % 10] = true;
                    temp /= 10;
                }
                
                boolean flag = false;
                for(boolean b : used) {
                	if (!b) {
                    	flag = true;
                        break;
                    }
                }
                
                if(flag) {
                	n += a;
                } else {
                 	break;
                }
            }
            
            System.out.printf("#%d %d\n", i, n);
        }
}
}