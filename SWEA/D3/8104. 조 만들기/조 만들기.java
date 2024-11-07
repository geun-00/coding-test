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
			
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			int[][] arr = new int[n][k];
			
			int num = 1;
			
			for(int i = 0; i < n; i++) {
				
				if(i % 2 == 0) {
					for(int j = 0; j < k; j++) {
						arr[i][j] = num++;
					}
				} else {
					for(int j = k - 1; j >= 0; j--) {
						arr[i][j] = num++;
					}
				}
			}			

			for(int i = 0; i < k; i++) {
				int sum = 0;
				for(int j = 0; j < n; j++) {
					sum += arr[j][i];
				}
				sb.append(sum).append(" ");
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}