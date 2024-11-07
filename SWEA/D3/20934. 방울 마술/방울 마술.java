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

			String s = st.nextToken();
			int k = Integer.parseInt(st.nextToken());
			
			int idx = s.indexOf("o");
			
			if(k == 0) {
				sb.append(idx);
			}
			if(k > 0) {
				if(k % 2 == 1) {
					sb.append(idx != 1 ? 1: 0);
				}else {
					sb.append(idx != 1? 0 : 1);
				}
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}