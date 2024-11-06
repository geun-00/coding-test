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
			String x = st.nextToken();
			
			int num = 0;
			
			for(int i = x.length() - 1; i >= 0; i--) {
				num += x.charAt(i) - '0';
				num %= n - 1;
			}
			
			sb.append(num);
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}