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

            int[] a = new int[n];
            int[] b = new int[m];

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                a[j] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                b[j] = Integer.parseInt(st.nextToken());
            }

            if (n > m) {
                int temp = n;
                n = m;
                m = temp;
                int[] tmp = a;
                a = b;
                b = tmp;
            }

            int max = Integer.MIN_VALUE;

            for (int j = 0; j <= m - n; j++) {
                int sum = 0;

                for (int k = 0; k < n; k++) {
                    sum += a[k] * b[j + k];
                }

                max = Math.max(max, sum);
            }

            System.out.println("#" + i + " " + max);
        }
	}
}