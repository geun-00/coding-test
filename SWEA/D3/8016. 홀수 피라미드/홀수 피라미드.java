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
			
			long a = 1, b = 1;
			
			long left = -2, right = 2;
			
			for(int i = 1; i < n; i++) {
				left += 4;
				right += 4;
				a += left;
				b += right;
			}
			
			sb.append(a).append(" ").append(b);
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}