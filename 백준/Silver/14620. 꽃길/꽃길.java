import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] visit = new boolean[n][n];
        solve(visit, n, arr, 0, 0);

        System.out.println(ans);
    }

    private static void solve(boolean[][] visit, int n, int[][] arr, int depth, int sum) {
        if (depth == 3) {
            ans = Math.min(ans, sum);
            return;
        }

        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (check(i, j, visit)) {
                    int cost = mark(i, j, arr, visit);
                    solve(visit, n, arr, depth + 1, sum + cost);
                    unmark(i, j, visit);
                }
            }
        }
    }

    private static void unmark(int x, int y, boolean[][] visit) {
        for (int i = 0; i < 5; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            visit[nx][ny] = false;
        }
    }

    private static int mark(int x, int y, int[][] arr, boolean[][] visit) {
        int cost = 0;

        for (int i = 0; i < 5; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            cost += arr[nx][ny];
            visit[nx][ny] = true;
        }

        return cost;
    }

    private static boolean check(int x, int y, boolean[][] visit) {
        for (int i = 0; i < 5; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (visit[nx][ny]) {
                return false;
            }
        }
        return true;
    }
}