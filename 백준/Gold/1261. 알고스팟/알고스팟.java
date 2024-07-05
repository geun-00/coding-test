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
        Deque<Point> deque = new ArrayDeque<>();
        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        deque.offerFirst(new Point(0, 0));
        dist[0][0] = 0;

        while (!deque.isEmpty()) {
            Point now = deque.pollFirst();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    if (map[nx][ny] == 0 && dist[nx][ny] > dist[now.x][now.y]) {
                        dist[nx][ny] = dist[now.x][now.y];
                        deque.offerFirst(new Point(nx, ny));
                    } else if (map[nx][ny] == 1 && dist[nx][ny] > dist[now.x][now.y] + 1) {
                        dist[nx][ny] = dist[now.x][now.y] + 1;
                        deque.offerLast(new Point(nx, ny));
                    }
                }
            }
        }

        System.out.println(dist[n-1][m-1]);
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
