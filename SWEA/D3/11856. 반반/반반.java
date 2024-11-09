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
			
			HashSet<Character> set = new HashSet<>();
			
			char[] arr = br.readLine().toCharArray();
			
			for(int i = 0; i < 4; i++) {
				set.add(arr[i]);
			}
			
			sb.append(set.size() == 2 ? "Yes" : "No");
			
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
}