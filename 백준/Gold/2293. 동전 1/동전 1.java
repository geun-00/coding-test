import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] dp = new int[k + 1][n + 1];

        int[] value = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            value[i] = Integer.parseInt(br.readLine());
            dp[0][i] = 1;
        }

        for (int i = 1; i <= k; i++) {

            for (int j = 1; j <= n; j++) {

                if (i - value[j] >= 0) {
                    dp[i][j] = dp[i - value[j]][j] + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        System.out.println(dp[k][n]);
    }
}
