import java.util.*;
import java.io.*;


class Solution
{
	public static void main(String args[]) throws Exception
	{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int t = Integer.parseInt(br.readLine());
        
        for(int i = 1; i <= t;  i++) {
        	char[] arr = br.readLine().toCharArray();
            
            int s = 0;
            int e = arr.length - 1;
            
            boolean check = true;
            
            while(s < e) {
            	if(arr[s] != arr[e]) {
                	check = false;
                    break;
                }
                s++;
                e--;
            }
            
            System.out.printf("#%d %d\n", i, (check) ? 1 : 0);
        }
		
	}
}