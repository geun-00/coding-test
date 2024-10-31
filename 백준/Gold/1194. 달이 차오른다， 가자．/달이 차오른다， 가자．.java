import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[][] map;
    static int n, m;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];

        int x = 0, y = 0;
        for (int i = 0; i < n; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = arr[j];

                if (map[i][j] == '0') {
                    x = i;
                    y = j;
                    map[i][j] = '.';
                }
            }
        }

        int ans = bfs(x, y);
        System.out.println(ans);
    }

    private static int bfs(int x, int y) {
        Queue<Point> qu = new ArrayDeque<>();
        qu.offer(new Point(x, y, 0, 0));

        boolean[][][] visit = new boolean[n][m][1 << 6];
        visit[x][y][0] = true;

        while (!qu.isEmpty()) {

            Point now = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m || map[nx][ny] == '#' || visit[nx][ny][now.bit]) {
                    continue;
                }

                char next = map[nx][ny];

                if (next == '1') {
                    return now.count + 1;
                }

                if (next == '.') {
                    qu.offer(new Point(nx, ny, now.count + 1, now.bit));
                    visit[nx][ny][now.bit] = true;
                }

                if ('a' <= next && next <= 'f') {
                    int bit = now.bit | (1 << (next - 'a'));
                    qu.offer(new Point(nx, ny, now.count + 1, bit));
                    visit[nx][ny][bit] = true;
                }

                if ('A' <= next && next <= 'F') {
                    if ((now.bit & (1 << (Character.toLowerCase(next) - 'a'))) != 0) {
                        qu.offer(new Point(nx, ny, now.count + 1, now.bit));
                        visit[nx][ny][now.bit] = true;
                    }
                }
            }
        }

        return -1;
    }

    static class Point {

        int x, y, count, bit;

        public Point(int x, int y, int count, int bit) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.bit = bit;
        }
    }
}
