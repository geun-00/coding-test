import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		StringBuilder sb = new StringBuilder();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= t ; tc++) {
			
			sb.append("#").append(tc).append(" ");
			
			int n = Integer.parseInt(br.readLine());
			
			int[] arr = new int[n];
			int[] dp = new int[n];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int max = arr[0];
			dp[0] = arr[0];
			
			for (int i = 1; i < n; i++) {
				dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
				max = Math.max(max, dp[i]);
			}
			
			sb.append(max);
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}