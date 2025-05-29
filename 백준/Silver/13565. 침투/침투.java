import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static char[][] board;
    static int m, n;
    static boolean[][] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static String ans = "NO";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        board = new char[m][n];
        for (int i = 0; i < m; i++) {
            board[i] = br.readLine().toCharArray();
        }

        visit = new boolean[m][n];
        for (int i = 0; i < n; i++) {
            if (board[0][i] == '0' && !visit[0][i]) {
                if (dfs(0, i)) {
                    break;
                }
            }
        }

        System.out.println(ans);
    }

    private static boolean dfs(int x, int y) {
        if (x == m - 1) {
            ans = "YES";
            return true;
        }

        visit[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
            if (board[nx][ny] == '1' || visit[nx][ny]) continue;

            if (dfs(nx, ny)) {
                return true;
            }
        }
        return false;
    }
}