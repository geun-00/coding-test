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
			
			int front = Integer.parseInt(s.substring(0, 2));
			int back = Integer.parseInt(s.substring(2));
			
			boolean isMMYY = (1 <= front && front <= 12 && 0 <= back && back <= 99);
			boolean isYYMM = (0 <= front && front <= 99 && 1 <= back && back <= 12);
			
			if(isMMYY && isYYMM) {
				sb.append("AMBIGUOUS");
			}		
			else if(isMMYY) {
				sb.append("MMYY");
			}
			else if(isYYMM) {
				sb.append("YYMM");
			}
			else {
				sb.append("NA");
			}
			
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
}