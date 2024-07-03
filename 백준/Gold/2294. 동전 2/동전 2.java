import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coins = new int[n + 1];
        int[][] dp = new int[k + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i <= k; i++) {
            dp[i][0] = 100_000;
        }

        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                if (coins[j] <= i) {
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - coins[j]][j] + 1);
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        System.out.println(dp[k][n] == 100_000 ? -1 : dp[k][n]);

    }
}
