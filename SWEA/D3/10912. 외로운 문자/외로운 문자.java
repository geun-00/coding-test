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
			
			int[] arr = new int[26];
			
			for(char ch : s.toCharArray()) {
				arr[ch - 'a']++;
			}
			
			boolean flag = false;
			
			for(int i = 0; i < 26; i++) {
				if(arr[i] % 2 == 1) {
					sb.append((char) (i + 'a'));
					flag = true;
				}
			}
			
			if(!flag) {
				sb.append("Good");
			}
			
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
}