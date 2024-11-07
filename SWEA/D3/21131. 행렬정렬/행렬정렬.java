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
			
			int n = Integer.parseInt(br.readLine());
			
			int[][] target = new int[n][n];
			int[][] input = new int[n][n];
			
			int num = 1;
			
			for(int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					target[i][j] = num++;
					input[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			boolean[] row = new boolean[n];
			
			for(int i = 0; i < n; i++) {
				if(target[0][i] == input[0][i]) {
					row[i] = true;
				}
			}
			
			n--;
			
			int ans = 0;
			while(n > 0) {
				if(!row[n]) {
					ans++;
					
					for(int i = 0; i < n; i++) {
						row[i] = !row[i];
					}
				}
				n--;
			}
			
			sb.append(ans);
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}