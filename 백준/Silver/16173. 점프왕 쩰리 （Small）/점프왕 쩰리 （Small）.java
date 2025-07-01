import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static String ans = "Hing";
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] map = new int[n][n];
        visit = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, n, map);
        System.out.println(ans);
    }

    private static void dfs(int x, int y, int n, int[][] map) {

        if (x == n - 1 && y == n - 1) {
            ans = "HaruHaru";
            return;
        }

        if (x < 0 || y < 0 || x >= n || y >= n) return;
        if (visit[x][y]) return;

        visit[x][y] = true;

        dfs(x, y + map[x][y], n, map);
        dfs(x + map[x][y], y, n, map);
    }
}