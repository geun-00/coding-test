import java.util.*;
import java.io.*;
import java.awt.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[][] arr = new int[n][n];
            int h = n / 2 - 1;

            arr[h][h] = 2;
            arr[h + 1][h + 1] = 2;

            arr[h][h + 1] = 1;
            arr[h + 1][h] = 1;

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken()) - 1;
                int y = Integer.parseInt(st.nextToken()) - 1;
                int color = Integer.parseInt(st.nextToken());

                arr[x][y] = color;

                for (int d = 0; d < 8; d++) {

                    int tx = x;
                    int ty = y;

                    tx += dx[d];
                    ty += dy[d];

                    ArrayList<Point> list = new ArrayList<>();

                    while (tx >= 0 && ty >= 0 && tx < n && ty < n) {
                        
                        if (arr[tx][ty] == 0) {
                            break;
                        }

                        if (arr[tx][ty] != color) {
                            list.add(new Point(tx, ty));
                        }

                        if (arr[tx][ty] == color) {
                            for (Point p : list) {
                                arr[p.x][p.y] = color;
                            }
                            break;
                        }

                        tx += dx[d];
                        ty += dy[d];
                    }
                }
            }


            int black = 0;
            int white = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (arr[i][j] == 1) {
                        black++;
                    }
                    if (arr[i][j] == 2) {
                        white++;
                    }
                }
            }

            System.out.println("#" + tc + " " + black + " " + white);
        }
	}
}