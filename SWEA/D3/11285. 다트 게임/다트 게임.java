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
			int ans = 0;
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				double dist = Math.sqrt(x * x + y * y);
				
				int score = 0;
				
				if(dist <= 200) score = 1;				
				if(dist <= 180) score = 2;				
				if(dist <= 160) score = 3;				
				if(dist <= 140) score = 4;				
				if(dist <= 120) score = 5;				
				if(dist <= 100) score = 6;				
				if(dist <= 80) score = 7;				
				if(dist <= 60) score = 8;				
				if(dist <= 40) score = 9;				
				if(dist <= 20) score = 10;
				
				ans += score;
			}
			
			sb.append(ans);
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}