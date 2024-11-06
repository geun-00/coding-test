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
			
			for(int i = 0; i < 3; i++) {
				String s = st.nextToken();
				sb.append(Character.toUpperCase(s.charAt(0)));
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}