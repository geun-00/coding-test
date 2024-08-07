import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int n, m;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int cheese = 0;
    static boolean[][] visit;

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

                if (map[i][j] == 1) {
                    cheese++;
                }
            }
        }

        int time = 0;

        while (cheese > 0) {

            time++;

            boolean outside = true;
            visit = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 0 && !visit[i][j]) {
                        markOutside(i, j, outside);
                        outside = false;
                    }
                }
            }

            visit = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 1 && !visit[i][j]) {
                        removeCheese(i, j);
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == -1) {
                        map[i][j] = 0;
                    }
                }
            }

        }

        System.out.println(time);
    }

    private static void removeCheese(int x, int y) {

        Queue<Point> qu = new ArrayDeque<>();
        visit[x][y] = true;
        qu.offer(new Point(x, y));

        while (!qu.isEmpty()) {

            Point now = qu.poll();

            int count = 0;

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visit[nx][ny]) {
                    if (map[nx][ny] == 1) {
                        visit[nx][ny] = true;
                        qu.offer(new Point(nx, ny));
                    } else if (map[nx][ny] == 0) {
                        count++;
                    }
                }
            }
            if (count >= 2) {
                map[now.x][now.y] = 0;
                cheese--;
            }
        }
    }

    private static void markOutside(int x, int y, boolean outside) {

        Queue<Point> qu = new ArrayDeque<>();
        visit[x][y] = true;
        qu.offer(new Point(x, y));

        int num = outside ? 0 : -1;

        map[x][y] = num;

        while (!qu.isEmpty()) {
            Point now = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    if (!visit[nx][ny] && map[nx][ny] == 0) {
                        visit[nx][ny] = true;
                        map[nx][ny] = num;
                        qu.offer(new Point(nx, ny));
                    }
                }
            }
        }
    }
}
