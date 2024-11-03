import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		StringBuilder sb = new StringBuilder();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		boolean[] arr = new boolean[101];
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= 9; j++) {
				arr[i * j] = true;
			}
		}
		
		int t = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= t ; tc++) {
			
			sb.append("#").append(tc).append(" ");
			
			int n = Integer.parseInt(br.readLine());
			
			sb.append(arr[n] ? "Yes" : "No");
	
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}