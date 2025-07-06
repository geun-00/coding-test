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
        solve(visit, n, arr, 0);

        System.out.println(ans);
    }

    private static void solve(boolean[][] visit, int n, int[][] arr, int depth) {
        if (depth == 3) {
            calculate(visit, n, arr);
            return;
        }

        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (!visit[i][j]) {
                    visit[i][j] = true;
                    solve(visit, n, arr, depth + 1);
                    visit[i][j] = false;
                }
            }
        }
    }

    private static void calculate(boolean[][] visit, int n, int[][] arr) {
        int[][] count = new int[n][n];
        int cost = 0;

        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (visit[i][j]) {
                    for (int d = 0; d < 5; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if (count[nx][ny] > 0) {
                            return;
                        }

                        count[nx][ny]++;
                        cost += arr[nx][ny];
                    }
                }
            }
        }

        ans = Math.min(ans, cost);
    }
}