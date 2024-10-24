import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {

            int count = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < (1 << n); i++) {
                int sum = 0;

                for (int j = 0; j < n; j++) {
                    if ((i & (1 << j)) != 0) {
                        sum += arr[j];
                    }
                }

                if (sum == k) {
                    count++;
                }
            }
            System.out.println("#" + tc + " " + count);
        }
	}
}