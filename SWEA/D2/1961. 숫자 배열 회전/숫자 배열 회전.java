import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for(int i = 1; i <= t; i++) {
            System.out.println("#" + i);

            int n = Integer.parseInt(br.readLine());

            int[][] matrix = new int[n][n];
            for (int j = 0; j < n; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < n; k++) {
                    matrix[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] rotate_90 = new int[n][n];
            int[][] rotate_180 = new int[n][n];
            int[][] rotate_270 = new int[n][n];

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    rotate_90[k][n - 1 - j] = matrix[j][k];
                    rotate_180[n - 1 - j][n - 1 - k] = matrix[j][k];
                    rotate_270[n - 1 - k][j] = matrix[j][k];
                }
            }

            for (int j = 0; j < n; j++) {
                printRow(rotate_90[j]);
                System.out.print(" ");
                printRow(rotate_180[j]);
                System.out.print(" ");
                printRow(rotate_270[j]);
                System.out.println();
            }
        }
	}
    
    public static void printRow(int[] row) {
        for (int r : row) {
            System.out.print(r);
        }
    }
}