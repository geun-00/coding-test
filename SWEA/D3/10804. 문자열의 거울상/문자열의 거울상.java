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
			
			for(int i = s.length() - 1; i >= 0; i--) {
				char c = s.charAt(i);
				
				if(c == 'b') sb.append("d");
				else if(c == 'd') sb.append("b");
				else if(c == 'p') sb.append("q");
				else if(c == 'q') sb.append("p");
			}
			
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
}