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
			
			double D = Double.parseDouble(st.nextToken());
			double A = Double.parseDouble(st.nextToken());
			double B = Double.parseDouble(st.nextToken());
			double F = Double.parseDouble(st.nextToken());
			
			double time = D / (A + B);
			double ans = time * F;
			
			sb.append(ans);
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}