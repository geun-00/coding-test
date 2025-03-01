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

        int[][] walk = new int[n + 1][2];
        int[][] bike = new int[n + 1][2];
        int[][] dp = new int[101][100_001];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            walk[i][0] = Integer.parseInt(st.nextToken());
            walk[i][1] = Integer.parseInt(st.nextToken());

            bike[i][0] = Integer.parseInt(st.nextToken());
            bike[i][1] = Integer.parseInt(st.nextToken());
        }

        dp[1][walk[1][0]] = walk[1][1];
        dp[1][bike[1][0]] = Math.max(dp[1][bike[1][0]], bike[1][1]);

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                if (dp[i - 1][j] == 0) continue;

                if (j + walk[i][0] <= k) {
                    dp[i][j + walk[i][0]] = Math.max(
                        dp[i][j + walk[i][0]],
                        dp[i - 1][j] + walk[i][1]
                    );
                }

                if (j + bike[i][0] <= k) {
                    dp[i][j + bike[i][0]] = Math.max(
                        dp[i][j + bike[i][0]],
                        dp[i - 1][j] + bike[i][1]
                    );
                }
            }
        }

        int ans = 0;
        for (int i = 0; i <= k; i++) {
            ans = Math.max(ans, dp[n][i]);
        }
        System.out.println(ans);
    }
}