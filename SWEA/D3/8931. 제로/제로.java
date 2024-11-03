import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		StringBuilder sb = new StringBuilder();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		Deque<Integer> stack = new ArrayDeque<>();
		
		for (int tc = 1; tc <= t ; tc++) {
			
			sb.append("#").append(tc).append(" ");
			
			int k = Integer.parseInt(br.readLine());
			
			for (int i = 0; i < k; i++) {
				
				int num = Integer.parseInt(br.readLine());
				
				if(num == 0) {
					stack.pop();
				} else {
					stack.push(num);
				}
			}
			
			int sum = 0;
			while(!stack.isEmpty()) {
				sum += stack.pop();
			}
			
			sb.append(sum);
			
			sb.append("\n");
			
		}
		
		System.out.println(sb);
	}
}