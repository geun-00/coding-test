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
			
			String s = br.readLine();
			
			int n = Integer.parseInt(s.substring(s.length() - 1));
			
			sb.append(n % 2 == 0 ? "Even" : "Odd");
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}