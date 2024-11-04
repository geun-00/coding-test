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
			
			String S = st.nextToken();
			String T = st.nextToken();
			
			int a = S.length();
			int b = T.length();
			
			int gcd = gcd(a, b);
            int lcm = a * b / gcd;
			
			StringBuilder s_build = new StringBuilder();
			StringBuilder t_build = new StringBuilder();
			
			for (int i = 0; i < lcm / a; i++) {
				s_build.append(S);
			}
			
			for (int i = 0; i < lcm / b; i++) {
				t_build.append(T);
			}
			
			String ans = "no";
			
			if(s_build.toString().equals(t_build.toString())) ans = "yes";
			
			sb.append(ans);
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
    
    public static int gcd(int a, int b) {
		if(b == 0) return a;
		
		return gcd(b, a % b);
	}
}