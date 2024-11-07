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
								
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			
			int ans = 0;
			
			for(int i = 1; i <= n; i++) {
				ans += i;
				if(ans == p) ans--;
			}
			
			sb.append(ans);
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}