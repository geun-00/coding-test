import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int tc = 1; tc <= 10; tc++) {

            int n = Integer.parseInt(br.readLine());

            int[][] arr = new int[100][100];

            for (int i = 0; i < 100; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 100; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int rowMax = 0;
            for (int i = 0; i < 100; i++) {
                int sum = 0;
                for (int j = 0; j < 100; j++) {
                    sum += arr[i][j];
                }
                rowMax = Math.max(rowMax, sum);
            }

            int colMax = 0;
            for (int i = 0; i < 100; i++) {
                int sum = 0;
                for (int j = 0; j < 100; j++) {
                    sum += arr[j][i];
                }
                colMax = Math.max(colMax, sum);
            }

            int i = 0, j = 0;
            int sum1 = 0;
            while (i < 100 || j < 100) {
                sum1 += arr[i++][j++];
            }

            int sum2 = 0;
            i = 99;
            j = 99;
            while (i >= 0 || j >= 0) {
                sum2 += arr[i--][j--];
            }

            int max1 = Math.max(rowMax, colMax);
            int max2 = Math.max(sum1, sum2);

            int res = Math.max(max1, max2);

            System.out.println("#" + tc + " " + res);
        }
	}
}