import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        for(int i = 1; i <= t; i++) {
            sb.append("#").append(i).append(" ");
        	int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
            	arr[j] = Integer.parseInt(st.nextToken());
            }
            
            Arrays.sort(arr);
            
            for(int num : arr){
            	sb.append(num).append(" ");
            }
            
            sb.append("\n");
        }
        
        System.out.print(sb);
	}
}