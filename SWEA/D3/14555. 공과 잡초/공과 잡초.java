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
			
			Deque<Character> stack = new ArrayDeque<>();
			
			int count = 0;
			
			for (int i = 0; i < arr.length - 1; i++) {
				if(arr[i] == '|') {
					if(arr[i + 1] == ')') {
						count++;
					}
				} else if(arr[i] == '(') {
					if(arr[i + 1] == '|' || arr[i + 1] == ')') {
						count++;
					}
				}
			}
			
			sb.append(count);
			
			sb.append("\n");
			
		}
		System.out.println(sb);
	}
}