import java.util.*;
import java.io.*;

class Solution {
    
    static int[] inOrder;
    static int k;
    static StringBuilder[] ans;
    
	public static void main(String args[]) throws Exception {
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int t = Integer.parseInt(br.readLine());
        
        for(int tc = 1; tc <= t; tc++) {
        	sb.append("#").append(tc).append(" ");
            
            k = Integer.parseInt(br.readLine());
            
            ans = new StringBuilder[k];
            
            for(int i = 0; i < k; i++) {
                ans[i] = new StringBuilder();            
			}
            
            int n = (1 << k) - 1;
            inOrder = new int[n];
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            for(int i = 0; i < n; i++) {
            	inOrder[i] = Integer.parseInt(st.nextToken());
            }
            
            solve(0, n - 1, 0);
            
            for(int i = 0; i < k; i++) {
                sb.append(ans[i].toString()).append("\n");    
			}
        }
        
        System.out.print(sb);
	}
    
    public static void solve(int s, int e, int depth) {
    	
        if(s > e || depth >= k) return;
    	
        int mid = (s + e) / 2;
        ans[depth].append(inOrder[mid]).append(" ");
        
        solve(s, mid - 1, depth + 1);
        solve(mid + 1, e, depth + 1);
    }
}