import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[][] w;
    static int min = Integer.MAX_VALUE;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        w = new int[n][n];
        visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                w[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visit[0] = true;
        dfs(0, 0, 1, 0);

        System.out.println(min);
    }

    private static void dfs(int start, int now, int depth, int cost) {
        if (depth == n) {
            if (w[now][start] != 0) {
                min = Math.min(min, cost + w[now][start]);
            }
            return;
        }

        for (int i = 0; i < n; i++) {

            if (visit[i] || w[now][i] == 0) continue;

            visit[i] = true;
            dfs(start, i, depth + 1, cost + w[now][i]);
            visit[i] = false;
        }
    }
}
