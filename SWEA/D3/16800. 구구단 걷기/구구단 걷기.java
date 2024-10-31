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
		
			long n = Long.parseLong(br.readLine());
			
			long min = Long.MAX_VALUE;
						
			for (int i = 1; i <= Math.sqrt(n); i++) {
				if(n % i == 0) {
					long j = n / i;
					min = Math.min(min, (i - 1) + (j - 1));
				}
			}
			
			sb.append(min);
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}