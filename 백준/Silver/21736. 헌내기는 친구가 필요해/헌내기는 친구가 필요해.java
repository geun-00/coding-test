import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static char[][] campus;
    static int n, m;
    static boolean[][] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        campus = new char[n][m];
        visit = new boolean[n][m];

        int x = 0, y = 0;

        for (int i = 0; i < n; i++) {
            campus[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (campus[i][j] == 'I') {
                    x = i;
                    y = j;
                }
            }
        }

        int ans = dfs(x, y);
        System.out.println(ans == 0 ? "TT" : ans);
    }

    private static int dfs(int x, int y) {

        if (x < 0 || y < 0 || x >= n || y >= m || visit[x][y] || campus[x][y] == 'X') {
            return 0;
        }

        visit[x][y] = true;

        int count = 0;
        if (campus[x][y] == 'P') {
            count++;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            count += dfs(nx, ny);
        }

        return count;
    }
}