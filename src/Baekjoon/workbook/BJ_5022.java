package Baekjoon.workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/5022">백준 5022번 - 연결</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-5022%EB%B2%88-%EC%97%B0%EA%B2%B0">velog</a>
 * @since 2025-03-23
 */
public class BJ_5022 {

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

        // 1
        int firstA = solve(points[0], points[1], points[2], points[3]);
        int firstB = solve(points[2], points[3], points[0], points[1]);

        int result = Math.min(firstA, firstB);
        System.out.println(result < INF ? result : "IMPOSSIBLE");
    }

    private static int solve(int[] p1, int[] p2, int[] p3, int[] p4) {
        // 2
        int[] path = new int[n * m];
        Arrays.fill(path, -1);
        int length_1 = bfs(path, p1, p2, p3, p4);

        // 3
        boolean[][] visit = new boolean[n][m];
        markingVisit(p2, visit, path);

        // 4
        int length_2 = bfs(p3, p4, visit);
        return length_1 + length_2;
    }

    // 2
    private static int bfs(int[] path, int[] p1, int[] p2, int[] p3, int[] p4) {
        int sx = p1[0], sy = p1[1];
        int ex = p2[0], ey = p2[1];

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

            if (x == ex && y == ey) return len;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (isInvalid(nx, ny, visit)) continue;

                visit[nx][ny] = true;
                qu.offer(new int[]{nx, ny, len + 1});
                path[nx * m + ny] = (x * m + y);
            }
        }

        return -1;
    }

    // 3
    private static void markingVisit(int[] p2, boolean[][] visit, int[] path) {
        int end = (p2[0] * m + p2[1]);
        while (end != -1) {
            int x = end / m;
            int y = end % m;
            visit[x][y] = true;
            end = path[end];
        }
    }

    // 4
    private static int bfs(int[] p1, int[] p2, boolean[][] visit) {
        int sx = p1[0], sy = p1[1];
        int ex = p2[0], ey = p2[1];

        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{sx, sy, 0});

        while (!qu.isEmpty()) {
            int[] now = qu.poll();
            int x = now[0];
            int y = now[1];
            int len = now[2];

            if (x == ex && y == ey) return len;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (isInvalid(nx, ny, visit)) continue;

                visit[nx][ny] = true;
                qu.offer(new int[]{nx, ny, len + 1});
            }
        }

        return INF;
    }

    private static boolean isInvalid(int nx, int ny, boolean[][] visit) {
        return nx < 0 || ny < 0 || nx >= n || ny >= m || visit[nx][ny];
    }
}
