import java.util.*;
import java.io.*;

class Solution {
	public static void main(String args[]) throws Exception {
	
        StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= t ; tc++) {
			
			sb.append("#").append(tc).append(" ");			
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			String[] S = new String[n];
			
			st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < n; i++) {
				S[i] = st.nextToken();
			}
			
			String[] T = new String[m];
			
			st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < m; i++) {
				T[i] = st.nextToken();
			}
			
			int q = Integer.parseInt(br.readLine());
			
			for(int i = 0; i < q; i++) {
				int y = Integer.parseInt(br.readLine()) - 1;
				
				sb.append(S[y % n]).append(T[y % m]).append(" ");
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}