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
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			int count = 0;
			int res = 0;
			
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < n; i++) {
				int num = Integer.parseInt(st.nextToken());
				
				if(num == 1) {
					count = 0;
				} else {
					count++;
					if(count == d) {
						count = 0;
						res++;
					}
				}
			}
			
			sb.append(res);
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}