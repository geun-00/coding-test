import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for(int i = 1; i <= t; i++) {

            int n = Integer.parseInt(br.readLine());
            int[][] arr = new int[n][n];

            int x = 0, y = 0;
            int dir = 1;

            for (int j = 1; j <= n * n; j++) {
                arr[x][y] = j;
                x += dx[dir];
                y += dy[dir];

                if (x < 0 || x >= n || y < 0 || y >= n || arr[x][y] != 0) {
                    x -= dx[dir];
                    y -= dy[dir];

                    dir = (dir + 1) % 4;
                    x += dx[dir];
                    y += dy[dir];
                }
            }

            System.out.println("#" + i);
            for (int[] row : arr) {
                for (int r : row) {
                    System.out.print(r + " ");
                }
                System.out.println();
            }
        }
	}
}