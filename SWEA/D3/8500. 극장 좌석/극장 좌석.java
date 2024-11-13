import java.util.*;
import java.io.*;

class Solution {
	public static void main(String args[]) throws Exception {
        
		StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t ; tc++) {

            sb.append("#").append(tc).append(" ");

            int n = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] arr = new int[n];

            int max = -1;
			
			for(int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				max = Math.max(max, arr[i]);
			}
			
			int ans = 0;
			boolean first = true;
            
			for(int i = 0; i < n; i++) {
				if(arr[i] == max && first) {
					ans += arr[i] * 2 + 1;
					first = false;
				} else {
					ans += arr[i] + 1;
				}
			}
			
			sb.append(ans);

            sb.append("\n");
        }

        System.out.print(sb);
	}
}