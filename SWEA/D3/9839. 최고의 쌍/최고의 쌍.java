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
			
			int ans = -1;
			
			for(int i = 0; i < n - 1; i++) {
				inner:
				for(int j = i + 1; j < n; j++) {
					int target = arr[i] * arr[j];
					
					int prev = target % 10;
					target /= 10;
					
					while(target > 0) {
						if(target % 10 != prev - 1) {
							continue inner;
						}
						prev = target % 10;
						target /= 10;
					}
					ans = Math.max(ans, arr[i] * arr[j]);
					
				}
			}
			
			sb.append(ans);
			
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
}