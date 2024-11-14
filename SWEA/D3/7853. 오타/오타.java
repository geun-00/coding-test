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
			
			char[] arr = br.readLine().toCharArray();
			int n = arr.length;
			
			long ans = 1;
			
			for(int i = 0; i < n; i++) {
				
				HashSet<Character> set = new HashSet<>();
				set.add(arr[i]);
				
				if(i > 0) {
					set.add(arr[i - 1]);
				}
				
				if(i < n - 1) {
					set.add(arr[i + 1]);
				}			
				
				ans *= set.size();
				ans %= 1_000_000_007;
			}
			
			sb.append(ans);
			
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
}