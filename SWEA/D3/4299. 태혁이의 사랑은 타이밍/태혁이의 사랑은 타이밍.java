import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		StringBuilder sb = new StringBuilder();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		int init = (24 * 60 * 11) + (60 * 11) + 11;
		
		for (int tc = 1; tc <= t ; tc++) {
			
			sb.append("#").append(tc).append(" ");
			
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int d = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			int temp = (24 * 60 * d) + (60 * h) + m;
			
			int ans = Math.max(-1, temp - init);

			sb.append(ans);
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}