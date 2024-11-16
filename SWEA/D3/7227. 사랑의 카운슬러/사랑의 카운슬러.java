import java.util.*;
import java.io.*;

class Solution{
	public static void main(String args[]) throws Exception {
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int t = Integer.parseInt(br.readLine());
        
        for(int tc = 1; tc <= t; tc++){
        	sb.append("#").append(tc).append(" ");
            
            int n = Integer.parseInt(br.readLine());
            
            int[][] arr = new int[n][2];
            
            for(int i = 0; i < n; i++) {
            	StringTokenizer st = new StringTokenizer(br.readLine());
                
                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());
            }
            
            long min = Long.MAX_VALUE;
            
            for(int i = 0; i < (1 << n); i++) {
            	
                if(Integer.bitCount(i) == n / 2) {
                	
                    long sumX = 0, sumY = 0;
                    
                    for(int j = 0; j < n; j++) {
                    	if((i & (1 << j)) != 0) {
                        	sumX += arr[j][0];
                            sumY += arr[j][1];
                        } else {
                        	sumX -= arr[j][0];
                            sumY -= arr[j][1];
                        }
                    }
                    
                    min = Math.min(min, sumX * sumX + sumY * sumY);
                }
            }
            sb.append(min);
            sb.append("\n");
        }     
        
        System.out.print(sb);
	}        
}