import java.util.*;
import java.io.*;

class Solution
{
    static int[] nums;
	static int[] arr;
    
	public static void main(String args[]) throws Exception
	{
		StringBuilder sb = new StringBuilder();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= t ; tc++) {
			
			sb.append("#").append(tc).append(" ");
			
			nums = new int[301];
			
			arr = new int[7];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < 7; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			solve(0, 0, 0);
			
			int count = 5;
			for (int i = 300; i >= 0; i--) {
				if(nums[i] > 0) count--;
				if(count <= 0) {
					sb.append(i);
					break;
				}
			}
			
			sb.append("\n");
			
		}
		System.out.println(sb);
	}
    
    public static void solve(int start, int depth, int sum) {
		if(depth == 3) {
			nums[sum]++;
			return;
		}
		
		for (int i = start; i < 7; i++) {
			solve(i + 1, depth + 1, sum + arr[i]);
		}
	}
}