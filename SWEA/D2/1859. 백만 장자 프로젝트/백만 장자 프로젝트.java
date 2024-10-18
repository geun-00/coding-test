import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 1; i <= t; i++) {

            int n = Integer.parseInt(br.readLine());

            int[] arr = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            int max = arr[n - 1];
            long count = 0;

            for (int j = n - 2; j >= 0; j--) {
                if (arr[j] > max) {
                    max = arr[j];
                } else {
                    count += max - arr[j];
                }
            }
            System.out.println("#" + i + " " + count);
        }
	}
}