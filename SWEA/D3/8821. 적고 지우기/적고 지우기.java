import java.util.*;
import java.io.*;

class Solution {
	public static void main(String args[]) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= t ; tc++) {
			
			sb.append("#").append(tc).append(" ");					
			
			int[] arr = new int[10];
			
			for(char c : br.readLine().toCharArray()) {
				arr[c - '0']++;
			}
			
			int ans = 0;
			
			for(int i : arr) {
				if(i % 2 == 1) {
					ans++;
				}
			}
			
			sb.append(ans);
			
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
}