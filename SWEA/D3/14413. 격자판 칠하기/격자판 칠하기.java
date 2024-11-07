import java.util.*;
import java.io.*;


class Solution {
    
    static int n, m;
	static char[][] arr;
    
	public static void main(String args[]) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= t ; tc++) {
			
			sb.append("#").append(tc).append(" ");			
								
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			arr = new char[n][m];
			
			for(int i = 0; i < n; i++) {
				arr[i] = br.readLine().toCharArray();				
			}
			
			sb.append(check('#') || check('.') ? "possible" : "impossible");
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
    
    public static boolean check(char ch) {
		
		char[][] temp = new char[n][m];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if((i + j) % 2 == 0) {
					temp[i][j] = ch;
				}else {
					temp[i][j] = ch == '#' ? '.' : '#';
				}
				
				if(arr[i][j] != '?' && arr[i][j] != temp[i][j]) {
					return false;
				}
			}		
		}
		
		return true;
	}
}