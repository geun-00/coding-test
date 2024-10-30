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
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int[] arr = new int[7];
			
			for (int i = 0; i < 7; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int min = Integer.MAX_VALUE;
			
			for (int i = 0; i < 7; i++) {
				
				int days = 0;
				int count = 0;
				int now = i;
				
				while(count < n) {
					if(arr[now] == 1) {
						count++;
					}
					days++;
					now = (now + 1) % 7;
				}
				
				min = Math.min(min, days);		
			}
			
			sb.append(min);	
			sb.append("\n");		
		}
		System.out.println(sb);
	}
}