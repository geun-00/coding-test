import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= t; i++) {

            sb.append("#").append(i).append(" ");

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[][] arr = new int[n][2];

            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());

                arr[j][0] = Integer.parseInt(st.nextToken()); //부피
                arr[j][1] = Integer.parseInt(st.nextToken()); //가치
            }

            int[] dp = new int[k + 1];

            for (int j = 0; j < n; j++) {
                for (int l = k; l - arr[j][0] >= 0; l--) {
                    dp[l] = Math.max(dp[l], dp[l - arr[j][0]] + arr[j][1]);
                }
            }

            sb.append(dp[k]);

            sb.append("\n");
        }

        System.out.print(sb);
	}
}