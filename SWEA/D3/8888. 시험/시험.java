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
			
			int N = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			
			int[][] arr = new int[N][T];
			
			int[] score = new int[T];
			Arrays.fill(score, N);
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < T; j++) {
					int num = Integer.parseInt(st.nextToken());
					score[j] -= num;
					arr[i][j] = num;
				}
			}
			
			int[][] person = new int[N][2];
			
			for(int i = 0; i < N; i++) {
				int count = 0;
				int sum = 0;
				for(int j = 0; j < T; j++) {
					if(arr[i][j] == 1) {
						count++;
						sum += score[j];
					}
				}
				
				person[i][0] = count;
				person[i][1] = sum;
			}
			
			int j_rank = 1;
			int j_score = person[P - 1][1];
			int j_count = person[P - 1][0];
			
			for(int i = 0; i < N; i++) {
				
				if(i == P - 1) continue;
				
				if(person[i][1] > j_score) {
					j_rank++;
				}
				else if(person[i][1] == j_score) {
					if(person[i][0] > j_count) {
						j_rank++;
					}
					else if(person[i][0] == j_count) {
						if(i < P - 1) {
							j_rank++;
						}
					}
				}
			}

			sb.append(j_score).append(" ").append(j_rank);
			
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
}