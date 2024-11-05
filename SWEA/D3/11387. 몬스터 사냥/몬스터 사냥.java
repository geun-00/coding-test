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
			
			int d = Integer.parseInt(st.nextToken());
			double l = Double.parseDouble(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			
			long sum = 0;
			for(int i = 0; i < n; i++) {
				sum += d * (1 + i * (l * 0.01));
			}
			
			sb.append(sum);
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}