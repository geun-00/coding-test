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
								
			StringTokenizer st = new StringTokenizer(br.readLine());

			int[] arr = new int[3];
			
			for(int i = 0; i < 3; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			if(arr[2] <= 2) {
				sb.append(-1);
				sb.append("\n");
				continue;
			} 
			
			if(arr[0] < arr[1] && arr[1] < arr[2]) {
				sb.append(0);
				sb.append("\n");
				continue;
			}
				
			
			int ans = 0;
			
			if(arr[1] >= arr[2]) {
				int gap = arr[1] - arr[2] + 1;
				ans += gap;
				arr[1] -= gap;
			}
			
			if(arr[0] >= arr[1]) {
				int gap = arr[0] - arr[1] + 1;
				ans += gap;
				arr[0] -= gap;
			}
            
            if(arr[0] <= 0 || arr[1] <= 0 || arr[2] <= 0) {
				sb.append(-1);
				sb.append("\n");
				continue;
			}
            
			sb.append(ans);		
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}