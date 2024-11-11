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
			
			int[] a = new int[n];
			int[] b = new int[n];
			
			for(int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				a[i] = Integer.parseInt(st.nextToken());
				b[i] = Integer.parseInt(st.nextToken());
			}
			
			int ans = 0;
			
			for(int i = 0; i < n - 1; i++) {
				for(int j = i + 1; j < n; j++) {
					
					if((a[i] < a[j] && b[i] > b[j]) || (a[i] > a[j] && b[i] < b[j])) {
						ans++;
					}
				}
			}
			
			sb.append(ans);
			
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
}