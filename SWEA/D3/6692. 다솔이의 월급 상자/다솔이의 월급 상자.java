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
			
		
			double sum = 0;
			
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				double p = Double.parseDouble(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				
				sum += p * x;
			}
			
			sb.append(String.format("%.6f", sum));
	
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}