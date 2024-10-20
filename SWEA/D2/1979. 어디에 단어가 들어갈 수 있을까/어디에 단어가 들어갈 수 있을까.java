/////////////////////////////////////////////////////////////////////////////////////////////
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
            int k = Integer.parseInt(st.nextToken());

            int result = 0;

            int[][] row = new int[n][n];
            int[][] col = new int[n][n];

            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int l = 0; l < n; l++) {
                    row[j][l] = Integer.parseInt(st.nextToken());
                    col[l][j] = row[j][l];
                }
            }

            for (int j = 0; j < n; j++) {
                int count = 0;
                for (int l = 0; l < n; l++) {
                    if (row[j][l] == 1) {
                        count++;
                        if (count == k) {
                            if (l == n - 1) {
                                result++;
                            }
                            if (l < n - 1 && row[j][l + 1] == 0) {
                                result++;
                            }
                        }
                    } else {
                        count = 0;
                    }
                }
            }

            for (int j = 0; j < n; j++) {
                int count = 0;
                for (int l = 0; l < n; l++) {
                    if (col[j][l] == 1) {
                        count++;
                        if (count == k) {
                            if (l == n - 1) {
                                result++;
                            }
                            if (l < n - 1 && col[j][l + 1] == 0) {
                                result++;
                            }
                        }
                    } else {
                        count = 0;
                    }
                }
            }

            System.out.println("#" + i + " " + result);
        }
	}
}