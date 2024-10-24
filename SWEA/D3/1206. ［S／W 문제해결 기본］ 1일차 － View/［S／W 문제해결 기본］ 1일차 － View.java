import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int tc = 1; tc <= 10; tc++) {

            int n = Integer.parseInt(br.readLine());

            int[] arr = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int res = 0;

            for (int i = 2; i < n - 2; i++) {
                int max = 0;
                for (int j = i - 2; j <= i + 2; j++) {
                    if (i == j) {
                        continue;
                    }
                    max = Math.max(max, arr[j]);
                }

                if (max < arr[i]) {
                    res += arr[i] - max;
                }
            }

            System.out.println("#" + tc + " " + res);
        }
	}
}