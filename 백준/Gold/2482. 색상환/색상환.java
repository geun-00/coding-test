import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        final int mod = 1_000_000_003;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            dp[i][1] = i;
        }

        for (int i = 4; i <= n; i++) {
            for (int j = 2; j <= k; j++) {
                if (j > i / 2) {
                    break;
                }

                dp[i][j] = dp[i - 2][j - 1] + dp[i - 1][j];
                dp[i][j] %= mod;
            }
        }

        System.out.println(dp[n][k]);
    }
}
