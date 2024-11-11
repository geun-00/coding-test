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
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int ans = 0;
			
			for(int i = 1; i < n - 1; i++) {
				if((arr[i-1]<arr[i] && arr[i+1]>arr[i]) || (arr[i-1]>arr[i] && arr[i+1]<arr[i])) {
					ans++;
				}
			}
			
			sb.append(ans);
			
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
}