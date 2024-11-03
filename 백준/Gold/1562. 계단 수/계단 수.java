import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        if (n < 10) {
            System.out.println(0);
            return;
        }

        int[][][] dp = new int[n + 1][10][1 << 10];

        for (int i = 1; i <= 9; i++) {
            dp[1][i][1 << i] = 1;
        }

        for (int len = 2; len <= n; len++) {
            for (int num = 0; num <= 9; num++) {
                for (int bit = 0; bit < (1 << 10); bit++) {
                    int now = bit | (1 << num);

                    if (num > 0) {
                        dp[len][num][now] += dp[len - 1][num - 1][bit];
                        dp[len][num][now] %= MOD;
                    }
                    if (num < 9) {
                        dp[len][num][now] += dp[len - 1][num + 1][bit];
                        dp[len][num][now] %= MOD;
                    }
                }
            }
        }

        int result = 0;
        for (int i = 0; i < 10; i++) {
            result += dp[n][i][(1 << 10) - 1];
            result %= MOD;
        }
        System.out.println(result);
    }
}
