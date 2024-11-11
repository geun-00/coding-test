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
			
			char[] s = br.readLine().toCharArray();
			int n = s.length;
			
			boolean[][] isPalindrome = new boolean[n][n];
			
			for(int i = 0; i < n; i++) {
				isPalindrome[i][i] = true;
				if(i < n - 1) {
					if(s[i] == s[i + 1]) {
						isPalindrome[i][i + 1] = true;
					}
				}
			}
			
			for(int i = n - 3; i >= 0; i--) {
				for(int j = i + 2; j < n; j++) {
					if(s[i] == s[j] && isPalindrome[i + 1][j - 1]) {
						isPalindrome[i][j] = true;
					}
				}
			}
			
			String ans = "YES";
			
			int mid = n / 2;
			
			if(!isPalindrome[0][n - 1] || !isPalindrome[0][mid - 1] || !isPalindrome[mid + 1][n - 1]) {
				ans = "NO";
			}
			
			sb.append(ans);
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}