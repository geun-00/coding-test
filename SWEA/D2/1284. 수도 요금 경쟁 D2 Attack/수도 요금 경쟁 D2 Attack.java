import java.util.*;
import java.io.*;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        	
        	int t = Integer.parseInt(br.readLine());
        
        	for(int i = 1; i <= t; i++) {
            	StringTokenizer st = new StringTokenizer(br.readLine());
                
                int p = Integer.parseInt(st.nextToken());
                int q = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                
                int a = p * w;
                int b = q;
                
                if(w > r) {
                	b += s * (w - r);
                }
                
                System.out.printf("#%d %d\n",i, Math.min(a, b));
            }
	}
}