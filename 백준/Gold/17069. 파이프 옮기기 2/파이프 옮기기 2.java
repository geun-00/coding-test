import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] home = new int[n][n];
        long[][][] dp = new long[n][n][3]; //0=가로, 1=세로, 2=대각선
        dp[0][1][0] = 1;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                home[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 2; i < n; i++) {
            if (home[0][i] == 0) {
                dp[0][i][0] = dp[0][i - 1][0];
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 2; j < n; j++) {
                if (home[i][j] == 1) continue;

                dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2];
                dp[i][j][1] = dp[i - 1][j][2] + dp[i - 1][j][1];

                if (home[i][j - 1] == 0 && home[i - 1][j] == 0) {
                    dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
                }
            }
        }

        System.out.println(dp[n - 1][n - 1][0] + dp[n - 1][n - 1][1] + dp[n - 1][n - 1][2]);
    }
}