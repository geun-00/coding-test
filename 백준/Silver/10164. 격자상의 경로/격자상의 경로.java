import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static boolean[][] visit;
    static int ox = -1, oy = -1;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        visit = new boolean[n][m];

        if (k != 0) {
            ox = (k - 1) / m;
            oy = (k - 1) % m;
        }

        System.out.println(dfs(0, 0, (k == 0)));
    }

    private static int dfs(int x, int y, boolean through) {

        if (x == n - 1 && y == m - 1) {
            return through ? 1 : 0;
        }

        visit[x][y] = true;
        int path = 0;

        if (y + 1 < m && !visit[x][y + 1]) {
            boolean new_through = through || (x == ox && y + 1 == oy);
            path += dfs(x, y + 1, new_through);
        }

        if (x + 1 < n && !visit[x + 1][y]) {
            boolean new_through = through || (x + 1 == ox && y == oy);
            path += dfs(x + 1, y, new_through);
        }

        visit[x][y] = false;

        return path;
    }
}