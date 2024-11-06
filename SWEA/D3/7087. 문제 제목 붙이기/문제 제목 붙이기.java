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
			
			HashSet<Character> set = new HashSet<>();
			
			int n = Integer.parseInt(br.readLine());
			
			for(int i = 0; i < n; i++) {
				set.add(br.readLine().charAt(0));
			}
			
			int count = 0;
			char target = 'A';
			
			while(true) {
				if(set.contains(target)) {
					count++;
					target++;
				}else {
					break;
				}
			}
			sb.append(count);
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}