import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int n, m;
    static boolean[][] visit;
    static int max = Integer.MIN_VALUE;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visit[i][j]) {
                    visit[i][j] = true;
                    dfs(1, i, j, map[i][j]);
                    visit[i][j] = false;

                    dfs(0, 0, i, j, map[i][j]);
                }
            }
        }

        System.out.println(max);
    }

    private static void dfs(int depth, int x, int y, int sum) {
        if (depth == 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visit[nx][ny]) {
                visit[nx][ny] = true;
                dfs(depth + 1, nx, ny, sum + map[nx][ny]);
                visit[nx][ny] = false;
            }
        }
    }

    private static void dfs(int start, int depth, int x, int y, int sum) {
        if (depth == 3) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = start; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                dfs(i + 1, depth + 1, x, y, sum + map[nx][ny]);
            }
        }
    }
}
