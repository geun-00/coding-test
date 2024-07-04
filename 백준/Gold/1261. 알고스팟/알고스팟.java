import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int n, m;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int min = Integer.MAX_VALUE;
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

        Queue<Point> qu = new PriorityQueue<>();
        boolean[][] visit = new boolean[n][m];

        qu.offer(new Point(0, 0, 0));
        visit[0][0] = true;

        while (!qu.isEmpty()) {
            Point now = qu.poll();

            if (now.x == n - 1 && now.y == m - 1) {
                System.out.println(now.count);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visit[nx][ny]) {
                    if (map[nx][ny] == 0) {
                        qu.offer(new Point(nx, ny, now.count));
                    } else {
                        qu.offer(new Point(nx, ny, now.count + 1));
                        map[nx][ny] = 0;
                    }

                    visit[nx][ny] = true;
                }
            }
        }
    }

    static class Point implements Comparable<Point> {
        int x, y, count;

        public Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public int compareTo(Point o) {
            return this.count - o.count;
        }
    }
}
