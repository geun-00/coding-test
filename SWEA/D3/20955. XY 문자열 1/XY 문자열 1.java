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
			String s = br.readLine();
			String e = br.readLine();
			
			while(s.length() != e.length()) {
				char last = e.charAt(e.length() - 1);
				
				if(last == 'X') {
					e = e.substring(0, e.length() - 1);
				}else {
					e = e.substring(0, e.length() - 1);
					StringBuilder temp = new StringBuilder(e);
					e = temp.reverse().toString();
				}
			}
			
			sb.append(s.equals(e) ? "Yes" : "No");
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}