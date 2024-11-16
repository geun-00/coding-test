import java.util.*;
import java.io.*;

class Solution {
	public static void main(String args[]) throws Exception {
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int t = Integer.parseInt(br.readLine());
        
        for(int tc = 1; tc <= t; tc++) {
        	sb.append("#").append(tc).append(" ");
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            char[] a = st.nextToken().toCharArray();
            char[] b = st.nextToken().toCharArray();
            
            int n = a.length;
            int m = b.length;
            
            int[][] dp = new int[n + 1][m + 1];
            
            for(int i = 1; i <= n; i++) {
            	for(int j = 1; j <= m; j++) {
                	if(a[i - 1] == b[j - 1]) {
                    	dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                    }
                }
            }
                
            sb.append(dp[n][m]).append("\n");
        }
            
		System.out.print(sb);            
	}
}