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
			
			String a = st.nextToken();
			String b = st.nextToken();
			
			sb.append(a.equals(b) ? a : 1);
			
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
}