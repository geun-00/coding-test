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

            int[] arr = new int[101];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 1000; j++) {
                arr[Integer.parseInt(st.nextToken())]++;
            }

            int max = 0;
            for (int j = 0; j <= 100; j++) {
                max = Math.max(max, arr[j]);
            }

            for (int j = 100; j >= 0; j--) {
                if (arr[j] == max) {
                    System.out.println("#" + i + " " + j);
                    break;
                }
            }
        }
	}
}