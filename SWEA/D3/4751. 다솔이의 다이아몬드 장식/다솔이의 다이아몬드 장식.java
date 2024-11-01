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
			String s = br.readLine();
			
			int n = s.length();
			
			for (int i = 0; i < n; i++) {
				sb.append("..#.");
			}
			sb.append(".").append("\n");
			
			for (int i = 0; i < n; i++) {
				sb.append(".#.#");
			}
			sb.append(".").append("\n");
			
			for (int i = 0; i < n; i++) {
				sb.append("#.").append(s.charAt(i)).append(".");
			}
			sb.append("#").append("\n");
			
			for (int i = 0; i < n; i++) {
				sb.append(".#.#");
			}
			sb.append(".").append("\n");
			
			for (int i = 0; i < n; i++) {
				sb.append("..#.");
			}
			sb.append(".").append("\n");
		}
		
		System.out.println(sb);
	}
}