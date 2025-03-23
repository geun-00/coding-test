import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int INF = 1_000_000;
    static int n, m;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()) + 1;
        m = Integer.parseInt(st.nextToken()) + 1;

        int[][] points = new int[4][2];
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            points[i][0] = Integer.parseInt(st.nextToken());
            points[i][1] = Integer.parseInt(st.nextToken());
        }

        int firstA = solve(points[0], points[1], points[2], points[3]);
        int firstB = solve(points[2], points[3], points[0], points[1]);

        if (firstA < INF || firstB < INF) {
            System.out.println(Math.min(firstA, firstB));
        } else {
            System.out.println("IMPOSSIBLE");
        }
    }

    private static int solve(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[] path = new int[m * n];
        int length_1 = bfs(path, p1, p2, p3, p4);

        boolean[][] visit = new boolean[n][m];
        int end = (p2[0] * m + p2[1]);
        int start = (p1[0] * m + p1[1]);

        while (end != start) {
            int x = end / m;
            int y = end % m;
            visit[x][y] = true;
            end = path[end];
        }
        int x = end / m;
        int y = end % m;
        visit[x][y] = true;

        int length_2 = bfs(p3, p4, visit);

        return length_1 + length_2;
    }

    private static int bfs(int[] path, int[] p1, int[] p2, int[] p3, int[] p4) {
        int sx = p1[0];
        int sy = p1[1];
        int ex = p2[0];
        int ey = p2[1];

        boolean[][] visit = new boolean[n][m];
        visit[sx][sy] = true;
        visit[p3[0]][p3[1]] = true;
        visit[p4[0]][p4[1]] = true;
        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{sx, sy, 0});

        while (!qu.isEmpty()) {
            int[] now = qu.poll();
            int x = now[0];
            int y = now[1];
            int len = now[2];

            if (x == ex && y == ey) {
                return len;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m || visit[nx][ny]) continue;

                visit[nx][ny] = true;
                qu.offer(new int[]{nx, ny, len + 1});
                path[nx * m + ny] = (x * m + y);
            }
        }

        return -1;
    }

    private static int bfs(int[] p1, int[] p2, boolean[][] visit) {
        int sx = p1[0];
        int sy = p1[1];
        int ex = p2[0];
        int ey = p2[1];

        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{sx, sy, 0});

        while (!qu.isEmpty()) {
            int[] now = qu.poll();
            int x = now[0];
            int y = now[1];
            int len = now[2];

            if (x == ex && y == ey) {
                return len;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m || visit[nx][ny]) continue;

                visit[nx][ny] = true;
                qu.offer(new int[]{nx, ny, len + 1});
            }
        }

        return INF;
    }
}
