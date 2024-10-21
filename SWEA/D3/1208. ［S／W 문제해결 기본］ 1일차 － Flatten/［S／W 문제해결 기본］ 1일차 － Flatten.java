import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int tc = 1; tc <= 10; tc++) {

            int n = Integer.parseInt(br.readLine());

            int[] arr = new int[100];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 100; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            while (n-- > 0) {
                Arrays.sort(arr);

                if (arr[99] - arr[0] <= 1) {
                    break;
                }

                arr[0]++;
                arr[99]--;
            }
            Arrays.sort(arr);

            int res = arr[99] - arr[0];

            System.out.println("#" + tc + " " + res);
        }
	}
}