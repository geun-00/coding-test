import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k;
    static char[][] board;
    static char[] target;
    static int[][][] dp;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new char[n][m];

        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
        }

        target = br.readLine().toCharArray();
        dp = new int[n][m][target.length];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == target[0]) {
                    ans += dfs(i, j, 0);
                }
            }
        }
        System.out.println(ans);
    }

    private static int dfs(int x, int y, int depth) {
        if (depth == target.length - 1) {
            return 1;
        }

        if (dp[x][y][depth] != -1) {
            return dp[x][y][depth];
        }

        dp[x][y][depth] = 0;

        for (int d = 0; d < 4; d++) {

            for (int i = 1; i <= k; i++) {
                int nx = x + (dx[d] * i);
                int ny = y + (dy[d] * i);

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }
                if (board[nx][ny] != target[depth + 1]) {
                    continue;
                }

                dp[x][y][depth] += dfs(nx, ny, depth + 1);
            }
        }

        return dp[x][y][depth];
    }
}