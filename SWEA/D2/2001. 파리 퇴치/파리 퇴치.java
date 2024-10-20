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

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[][] arr = new int[n][n];

            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < n; k++) {
                    arr[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            int max = Integer.MIN_VALUE;

            for (int j = 0; j <= n - m; j++) {
                for (int k = 0; k <= n - m; k++) {
                    int sum = 0;

                    for (int l = j; l < j + m; l++) {
                        for (int o = k; o < k + m; o++) {
                            sum += arr[l][o];
                        }
                    }
                    max = Math.max(max, sum);
                }
            }

            System.out.println("#" + i + " " + max);

        }
	}
}