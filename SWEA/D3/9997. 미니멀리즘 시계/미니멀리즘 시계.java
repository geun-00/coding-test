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
			
			int n = Integer.parseInt(br.readLine()) * 2;
			
			sb.append(n / 60).append(" ").append(n % 60);
			
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
}