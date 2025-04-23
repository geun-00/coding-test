import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken()) - 1;
        int b = Integer.parseInt(st.nextToken()) - 1;

        int m = Integer.parseInt(br.readLine());

        int[][][] dp = new int[m + 1][n][n];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    dp[i][j][k] = INF;
                }
            }
        }

        dp[0][a][b] = dp[0][b][a] = 0;

        for (int i = 0; i < m; i++) {
            int num = Integer.parseInt(br.readLine()) - 1;

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (dp[i][j][k] == INF) continue;

                    int now = dp[i][j][k];

                    if (num == j || num == k) {
                        dp[i + 1][j][k] = Math.min(dp[i + 1][j][k], now);
                    } else {
                        dp[i + 1][num][k] = Math.min(dp[i + 1][num][k], now + Math.abs(num - j));
                        dp[i + 1][j][num] = Math.min(dp[i + 1][j][num], now + Math.abs(num - k));
                    }
                }
            }
        }

        int ans = INF;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.min(ans, dp[m][i][j]);
            }
        }

        System.out.println(ans);
    }
}