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
			
			int[] arr = new int[10];
			
			for(int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				int num1 = Integer.parseInt(st.nextToken());
				int num2 = Integer.parseInt(st.nextToken());
				int num3 = Integer.parseInt(st.nextToken());
				int num4 = Integer.parseInt(st.nextToken());
				
				String s = st.nextToken();
				
				arr[num1] += "YES".equals(s) ? 1 : -1;
				arr[num2] += "YES".equals(s) ? 1 : -1;
				arr[num3] += "YES".equals(s) ? 1 : -1;
				arr[num4] += "YES".equals(s) ? 1 : -1;
			}
			
			int num = Integer.MIN_VALUE;
			int ans = 0;
			
			for(int i = 0; i < 10; i++) {
				if(arr[i] > num) {
					num = arr[i];
					ans = i;
				}
			}
			
			sb.append(ans);
			
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
}