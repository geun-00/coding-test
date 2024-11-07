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
			
			int n = Integer.parseInt(br.readLine());
			
			if(n % 3 == 1) {
				sb.append("impossible").append("\n");
				continue;
			}
			
			if(n % 3 == 2) {
				sb.append("BA");
			}
			
			for(int i = 0; i < n / 3; i++) {
				sb.append("BBA");
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}