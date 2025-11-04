import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        int[][] coins = new int[k + 1][2];
        for (int i = 1; i <= k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            coins[i][0] = Integer.parseInt(st.nextToken());
            coins[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[k + 1][t + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= k; i++) {
            for (int j = 0; j <= t; j++) {
                dp[i][j] = dp[i - 1][j];

                int value = coins[i][0];
                int count = coins[i][1];

                for (int c = 1; c <= count; c++) {
                    int prev = j - (value * c);

                    if (prev < 0) break;

                    dp[i][j] += dp[i - 1][prev];
                }
            }
        }

        System.out.println(dp[k][t]);
    }
}