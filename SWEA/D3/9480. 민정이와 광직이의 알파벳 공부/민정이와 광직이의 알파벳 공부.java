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
			
			int n = Integer.parseInt(br.readLine());
			
			int[] word = new int[n];
			for (int i = 0; i < n; i++) {
				String s = br.readLine();
				
				int bit = 0;
				for(char c : s.toCharArray()) {
					bit |= (1<< (c - 'a'));
				}
				word[i] = bit;				
			}
			
			ArrayList<Integer> list = new ArrayList<>();
			
			for(int i = 1; i < (1 << n); i++) {
				
				int bit = 0;
				for(int j = 0; j < n; j++) {
					if((i & (1 << j)) != 0) {
						bit |= word[j];
					}
				}
				
				if(bit == (1 << 26) - 1) {
					list.add(bit);
				}
			}
			
			sb.append(list.size());
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}