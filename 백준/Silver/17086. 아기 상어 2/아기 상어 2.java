import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int n, m;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static int ans = 0;

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

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    ans = Math.max(ans, bfs(i, j));
                }
            }
        }

        System.out.println(ans);
    }

    private static int bfs(int i, int j) {
        boolean[][] visit = new boolean[n][m];
        visit[i][j] = true;

        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{i, j, 0});

        while (!qu.isEmpty()) {
            int[] now = qu.poll();
            int x = now[0];
            int y = now[1];
            int count = now[2];

            if (map[x][y] == 1) {
                return count;
            }

            for (int d = 0; d < 8; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m || visit[nx][ny]) continue;

                visit[nx][ny] = true;
                qu.offer(new int[]{nx, ny, count + 1});
            }
        }

        return 0;
    }
}