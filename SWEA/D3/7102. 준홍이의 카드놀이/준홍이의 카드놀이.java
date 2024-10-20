import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 1; i <= t; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[] arr = new int[n + m + 1];

            int max = 0;
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= m; k++) {
                    arr[j + k]++;
                    max = Math.max(max, arr[j + k]);
                }
            }

            System.out.print("#" + i);

            for (int j = 1; j <= n + m; j++) {
                if (arr[j] == max) {
                    System.out.print(" " + j);
                }
            }
            System.out.println();
        }
	}
}