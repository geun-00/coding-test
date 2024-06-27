import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static boolean[][][] visit;
    static int n, m;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int min = Integer.MAX_VALUE;

    static class Point {
        int x, y, move;
        boolean destroy;

        public Point(int x, int y, int move, boolean destroy) {
            this.x = x;
            this.y = y;
            this.move = move;
            this.destroy = destroy;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visit = new boolean[n][m][2];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(s.substring(j, j + 1));
            }
        }

        System.out.println(bfs());

    }

    private static int bfs() {
        Queue<Point> qu = new ArrayDeque<>();
        qu.offer(new Point(0, 0, 1, false));
        visit[0][0][0] = true;

        while (!qu.isEmpty()) {
            Point now = qu.poll();

            if (now.x == n - 1 && now.y == m - 1) {
                return now.move;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (map[nx][ny] == 0) {
                        if (!now.destroy && !visit[nx][ny][0]) {
                            visit[nx][ny][0] = true;
                            qu.offer(new Point(nx, ny, now.move + 1, false));
                        }
                        if (now.destroy && !visit[nx][ny][1]) {
                            visit[nx][ny][1] = true;
                            qu.offer(new Point(nx, ny, now.move + 1, true));
                        }
                    } else {
                        if (!now.destroy && !visit[nx][ny][1]) {
                            visit[nx][ny][1] = true;
                            qu.offer(new Point(nx, ny, now.move + 1, true));
                        }
                    }
                }
            }
        }

        return -1;
    }
}
