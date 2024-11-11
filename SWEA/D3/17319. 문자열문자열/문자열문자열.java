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
			String s = br.readLine();
			
			if(n % 2 == 1) {
				sb.append("No");
			}else {
				int mid = n / 2;
				
				String left = s.substring(0, mid);
				String right = s.substring(mid);
				
				sb.append(left.equals(right) ? "Yes" : "No");
			}
			
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
}