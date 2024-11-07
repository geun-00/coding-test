import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		int[] arr = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		for (int tc = 1; tc <= t ; tc++) {
			
			sb.append("#").append(tc).append(" ");			
					
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int m = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			int days = d - 1;
			
			for(int i = 0; i < m - 1; i++) {
				days += arr[i];
			}
			
			sb.append((4 + days) % 7);
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}