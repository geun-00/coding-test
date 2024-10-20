import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {

            int n = Integer.parseInt(br.readLine());

            int[][] arr = new int[n + 1][n + 1];

            for (int i = 1; i <= n; i++) {
                char[] ch = br.readLine().toCharArray();
                for (int j = 1; j <= n; j++) {
                    arr[i][j] += arr[i][j - 1] + (ch[j - 1] - '0');
                }
            }

            int res = 0;

            int mid = n / 2 + 1;
            int s = mid;
            int e = mid;

            for (int i = 1; i <= n; i++) {
                res += arr[i][e] - arr[i][s - 1];

                if (i + 1 <= mid) {
                    s--;
                    e++;
                } else {
                    s++;
                    e--;
                }
            }
            System.out.println("#" + tc + " " + res);
        }
	}
}