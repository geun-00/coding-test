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

            int[][] arr = new int[n][n];

            for (int j = 0; j < n; j++) {
                arr[j][0] = 1;
            }

            for (int j = 1; j < n; j++) {
                for (int k = 1; k <= j; k++) {
                    arr[j][k] = arr[j - 1][k - 1] + arr[j - 1][k];
                }
            }

            System.out.println("#" + i);
            for (int j = 0; j < n; j++) {
                for (int k = 0; k <= j; k++) {
                    System.out.print(arr[j][k] + " ");
                }
                System.out.println();
            }
        }
	}
}