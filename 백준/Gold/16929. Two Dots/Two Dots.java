import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visit = new boolean[n][m];
        map = new char[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        String ans = "No";

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dfs(i, j, i, j, 0)) {
                    ans = "Yes";
                    break;
                }
            }
        }

        System.out.println(ans);
    }

    private static boolean dfs(int x, int y, int firstX, int firstY, int k) {
        if (k >= 4 && visit[x][y] && x == firstX && y == firstY) {
            return true;
        }

        if (visit[x][y]) {
            return false;
        }

        visit[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
            if (map[x][y] != map[nx][ny]) continue;

            if (dfs(nx, ny, firstX, firstY, k + 1)) {
                return true;
            }

        }

        visit[x][y] = false;
        return false;
    }
}