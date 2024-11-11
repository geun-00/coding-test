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
			
			int[] arr = new int[n + 1];
			
			boolean flag = false;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < n; i++) {
				int num = Integer.parseInt(st.nextToken());
				
				if(arr[num] != 0) {
					flag = true;
					break;
				}
				arr[num]++;
			}
			
			sb.append(flag ? "No" : "Yes");
            sb.append("\n");
		}
		
		System.out.print(sb);
	}
}