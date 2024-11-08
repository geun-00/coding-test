import java.util.*;
import java.io.*;

class Solution{
	public static void main(String args[]) throws Exception {
        
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= t ; tc++) {
			
			sb.append("#").append(tc).append(" ");					
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			HashSet<String> set = new HashSet<>();
			
			int ans = 0;
			
			for(int i = 0; i < n; i++) {
				String s = br.readLine();
				String reverse = new StringBuilder(s).reverse().toString();
				
				if(set.contains(reverse)) {
					ans += m * 2;
					set.remove(reverse);
				} else {
					set.add(s);
				}
			}
			
			for(String s : set) {
				if(check(s)) {
					ans += m;
                    break;
				}
			}
			
			sb.append(ans);
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
    
    public static boolean check(String s) {
		
		int left = 0, right = s.length() - 1;
		
		while(left < right) {
			if(s.charAt(left) != s.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		
		return true;
	}
}