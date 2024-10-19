import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int t = Integer.parseInt(br.readLine());
        
        for(int i = 1; i <= t; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
            
            int l = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            
            int res = 0;
            
            if (l > x){
            	res = l - x;
            } else if (x > u) {
            	res = -1;
            }
            
            System.out.println("#" + i + " " + res);
        }
	}
}