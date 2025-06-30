import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[][] map;
    static boolean[][][] visit;
    static int[] dx = {0, 0, 1, -1}; //동서남북
    static int[] dy = {1, -1, 0, 0}; //동서남북

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        visit = new boolean[m][n][4];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(st.nextToken()) - 1;
        int sy = Integer.parseInt(st.nextToken()) - 1;
        int sdir = Integer.parseInt(st.nextToken()) - 1;
        visit[sx][sy][sdir] = true;

        st = new StringTokenizer(br.readLine());
        int ex = Integer.parseInt(st.nextToken()) - 1;
        int ey = Integer.parseInt(st.nextToken()) - 1;
        int edir = Integer.parseInt(st.nextToken()) - 1;

        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{sx, sy, sdir, 0});

        int ans = Integer.MAX_VALUE;

        while (!qu.isEmpty()) {
            int[] cur = qu.poll();
            int x = cur[0];
            int y = cur[1];
            int dir = cur[2];
            int count = cur[3];

            if (x == ex && y == ey && dir == edir) {
                ans = Math.min(ans, count);
                continue;
            }

            //명령 1
            for (int i = 1; i <= 3; i++) {
                int nx = x + (i * dx[dir]);
                int ny = y + (i * dy[dir]);

                if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
                    continue;
                }

                if (map[nx][ny] == 1) {
                    break;
                }

                if (!visit[nx][ny][dir]) {

                    visit[nx][ny][dir] = true;
                    qu.offer(new int[]{nx, ny, dir, count + 1});
                }
            }

            //명령 2
            int left = turnLeft(dir);
            if (!visit[x][y][left]) {
                visit[x][y][left] = true;
                qu.offer(new int[]{x, y, left, count + 1});
            }

            int right = turnRight(dir);
            if (!visit[x][y][right]) {
                visit[x][y][right] = true;
                qu.offer(new int[]{x, y, right, count + 1});
            }
        }

        System.out.println(ans);
    }

    private static int turnLeft(int dir) {
        switch (dir) {
            case 0:
                return 3;
            case 1:
                return 2;
            case 2:
                return 0;
            case 3:
                return 1;
        }
        return -1;
    }

    private static int turnRight(int dir) {
        switch (dir) {
            case 0:
                return 2;
            case 1:
                return 3;
            case 2:
                return 1;
            case 3:
                return 0;
        }
        return -1;
    }
}