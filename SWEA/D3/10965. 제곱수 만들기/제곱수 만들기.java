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
					
			int a = Integer.parseInt(br.readLine());
			
			int b = 1;
			
			for(int i = 2; i * i <= a; i++) {
				int count = 0;
				
				while(a % i == 0) {
					count++;
					a /= i;
				}
				
				if(count % 2 == 1) {
					b *= i;
				}
			}
			
			if(a > 1) {
				b *= a;
			}
			
			sb.append(b);
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}