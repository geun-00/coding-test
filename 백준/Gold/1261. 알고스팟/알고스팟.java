import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int n, m;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(s.substring(j, j + 1));
            }
        }

        bfs();
    }

    private static void bfs() {

        Deque<Point> qu = new ArrayDeque<>();
        boolean[][] visit = new boolean[n][m];

        qu.offerFirst(new Point(0, 0));
        visit[0][0] = true;

        int[][] dist = new int[n][m];

        while (!qu.isEmpty()) {
            Point now = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visit[nx][ny]) {
                    if (map[nx][ny] == 0) {
                        dist[nx][ny] = dist[now.x][now.y];
                        qu.offerFirst(new Point(nx, ny));
                    } else if (map[nx][ny] == 1) {
                        dist[nx][ny] = dist[now.x][now.y] + 1;
                        qu.offerLast(new Point(nx, ny));
                    }

                    visit[nx][ny] = true;
                }
            }
        }

        System.out.println(dist[n - 1][m - 1]);
    }
}