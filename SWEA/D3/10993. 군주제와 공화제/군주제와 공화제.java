import java.util.*;
import java.io.*;

class Solution {
	public static void main(String args[]) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= t ; tc++) {
			
			sb.append("#").append(tc).append(" ");					
			
			int n = Integer.parseInt(br.readLine());
			
			City[] cities = new City[n];
			
			for(int i = 0; i < n; i++) {
				
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				double s = Double.parseDouble(st.nextToken());
				
				cities[i] = new City(x, y, s);
			}
			
			int[] arr = new int[n];
			String[] ans = new String[n];
			
			for(int i = 0; i < n; i++) {
				
				int x1 = cities[i].x;
				int y1 = cities[i].y;
				
				double max = 0;
				int count = 0;
				int num = 0;
				
				for(int j = 0; j < n; j++) {
					if(i == j) continue;
					
					int x2 = cities[j].x;
					int y2 = cities[j].y;
					
					double temp = cities[j].s / (((x2-x1) * (x2-x1)) + ((y2-y1) * (y2-y1)));
					if(temp > cities[i].s) {
						if(max < temp) {
							max = temp;
							count = 1;
							num = j;
						} else if(max <= temp) {
							count++;
						}
					}
				}
				
				if(count == 0) {
					arr[i] = i;
					ans[i] = "K";
				}
				if(count >= 2) {
					arr[i] = i;
					ans[i] = "D";
				}
				if(count == 1) {
					arr[i] = num;
				}
			}
			

			for(int i = 0; i < n; i++) {
				if(arr[i] != i) {
					
					int now = arr[i];
					
					while(arr[now] != now) {
						now = arr[now];						
					}
					
					ans[i] = now + 1 + "";
				}
			}
			
			for(String s : ans) {
				sb.append(s).append(" ");
			}
			
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
    
    static class City {
		int x, y;
		double s;
		
		public City(int x, int y, double s) {
			this.x = x;
			this.y = y;
			this.s = s;
		}
	}
}