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
			
			long n = Long.parseLong(st.nextToken());
			long p_d = Long.parseLong(st.nextToken());
			long p_g = Long.parseLong(st.nextToken());
			
			String ans = "Possible";
			
			if((p_g == 100 && p_d != 100) || (p_g == 0 && p_d != 0)) {
				ans = "Broken";
			}
			else {
				boolean flag = false;
				for(long i = 1; i <= n; i++) {
					if(p_d * i % 100 == 0) {
						flag = true;
						break;
					}
				}
				
				if(!flag) ans = "Broken";
			}	
			
			sb.append(ans);
			
			sb.append("\n");
		}
        System.out.print(sb);
	}
}