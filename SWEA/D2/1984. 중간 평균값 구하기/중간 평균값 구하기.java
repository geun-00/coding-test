import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 1; i <= t; i++) {
            int[] arr = new int[10];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 10; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);

            int sum = 0;
            for (int j = 1; j < 9; j++) {
                sum += arr[j];
            }

            int avg = (int) Math.round(sum / 8.0);
            System.out.println("#" + i + " " + avg);
        }
	}
}