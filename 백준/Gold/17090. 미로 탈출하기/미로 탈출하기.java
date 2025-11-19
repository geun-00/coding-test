import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int POSSIBLE = 1, IMPOSSIBLE = -1;
    static int n, m;
    static char[][] map;
    static String dir = "URDL";
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] visit;
    static int[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int count = 0;
        visit = new boolean[n][m];
        memo = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dfs(i, j) == POSSIBLE) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    private static int dfs(int x, int y) {
        if (x < 0 || y < 0 || x >= n || y >= m) return POSSIBLE;
        if (memo[x][y] != 0) return memo[x][y];
        if (visit[x][y]) return IMPOSSIBLE;

        visit[x][y] = true;
        int nx = x + dx[dir.indexOf(map[x][y])];
        int ny = y + dy[dir.indexOf(map[x][y])];

        memo[x][y] = dfs(nx, ny);
        visit[x][y] = false;

        return memo[x][y];
    }
}