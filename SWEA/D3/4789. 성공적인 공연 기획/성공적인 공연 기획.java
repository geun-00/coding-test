import java.util.*;
import java.io.*;

class Solution {
	public static void main(String args[]) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= t ; tc++) {
			
			sb.append("#").append(tc).append(" ");			
			
			char[] arr = br.readLine().toCharArray();
			
			int num = arr[0] - '0';
			int ans = 0;
			
			for (int i = 1; i < arr.length; i++) {
				int diff = i - num;
				ans += Math.max(diff, 0);
				num += (arr[i] - '0') + Math.max(diff, 0);
			}
			
			sb.append(ans);
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}