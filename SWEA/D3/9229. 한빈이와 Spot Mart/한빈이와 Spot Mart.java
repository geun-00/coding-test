import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);

            int left = 0;
            int right = n - 1;
            int res = -1;

            while (left < right) {
                int sum = arr[left] + arr[right];
                if (sum <= m) {
                    res = Math.max(res, sum);
                    left++;
                } else {
                    right--;
                }
            }

            System.out.println("#" + tc + " " + res);
        }
	}
}