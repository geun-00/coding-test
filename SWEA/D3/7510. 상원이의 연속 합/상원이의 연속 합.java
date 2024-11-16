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
			
			int left = 1;
			int right = 1;
			int sum = 1;
			int count = 0;
			
			while(right <= n) {
				
				if(sum == n) {
					count++;
				}
				
				if(sum < n) {
					right++;
					sum += right;
				} else {
                    sum -= left;
					left++;				
				}
			}
			
			sb.append(count);
			
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
}