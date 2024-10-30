import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb  = new StringBuilder();
        
        int t = Integer.parseInt(br.readLine());
        
        for(int i = 1; i <= t; i++) {
        	sb.append("#").append(i).append(" ");
            
            int n = Integer.parseInt(br.readLine());
            
            int[] arr = new int[n];
            int[] dp = new int[n];
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
            	arr[j] = Integer.parseInt(st.nextToken());
                dp[j] = 1;
            }
            
            int max = 1;
            for(int j = 1; j < n; j++){
            	for(int k = 0; k < j; k++) {
                	if(arr[k] < arr[j] && dp[k] + 1 == dp[j] + 1) {
                    	dp[j] = dp[k] + 1;
                    }
                }
                max = Math.max(max, dp[j]);
            }
            
            sb.append(max).append("\n");
        }
        
        System.out.print(sb);
	}
}