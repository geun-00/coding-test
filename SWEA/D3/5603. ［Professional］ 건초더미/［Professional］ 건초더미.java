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
			
			int sum = 0;
			int[] arr = new int[n];
			
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(br.readLine());
				sum += arr[i];
			}
			
			int avg = sum / n;
			
			int ans = 0;
			
			for (int i = 0; i < n; i++) {
				if(arr[i] > avg) {
					ans += arr[i] - avg;
				}
			}
			
			sb.append(ans);
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}