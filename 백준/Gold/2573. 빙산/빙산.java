import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;
    static boolean[][] visit;
    static int n, m;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int years = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] > 0) {
                    years++;

                    if (bfs(i, j) >= 2) {
                        System.out.println(years);
                        return;
                    }
                }
            }
        }

        System.out.println(0);
    }

    private static int bfs(int x, int y) {

        Queue<Point> qu = new ArrayDeque<>();
        qu.offer(new Point(x, y));

        visit = new boolean[n][m];
        visit[x][y] = true;

        while (!qu.isEmpty()) {
            Point now = qu.poll();

            int count = 0;

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (!visit[nx][ny]) {
                    if (map[nx][ny] > 0) {
                        visit[nx][ny] = true;
                        qu.offer(new Point(nx, ny));
                    } else {
                        count++;
                    }
                }
            }

            map[now.x][now.y] -= count;
        }

        visit = new boolean[n][m];

        int lumps = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] > 0 && !visit[i][j]) {
                    lumps++;
                    solveLumps(i, j);
                }
            }
        }

        return lumps;
    }

    private static void solveLumps(int x, int y) {

        Queue<Point> qu = new ArrayDeque<>();
        qu.offer(new Point(x, y));

        visit[x][y] = true;

        while (!qu.isEmpty()) {

            Point now = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (!visit[nx][ny] && map[nx][ny] > 0) {
                    visit[nx][ny] = true;
                    qu.offer(new Point(nx, ny));

                }
            }
        }
    }
}
