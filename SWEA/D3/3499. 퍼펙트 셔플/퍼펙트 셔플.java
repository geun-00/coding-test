import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		
       	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int t = Integer.parseInt(br.readLine());
        
        for(int i = 1; i <= t; i++) {
        	
            sb.append("#").append(i).append(" ");
            
             int n = Integer.parseInt(br.readLine());
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            String[] arr = new String[n];
            
            for(int j = 0; j < n; j++) {
            	arr[j] = st.nextToken();
            }
            
            int mid = n / 2;
            if(n % 2 == 1) mid ++;
            
            int left = 0;
            int right = mid;
            
            while(left < mid && right < n ){
            	sb.append(arr[left++]).append(" ").append(arr[right++]).append(" ");
            }
            
            if(n % 2 == 1) sb.append(arr[left]);
            
            sb.append("\n");
        }
        
         System.out.print(sb);
	}
}