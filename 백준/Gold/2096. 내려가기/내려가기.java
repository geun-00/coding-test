import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] dp = new int[n][3];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                dp[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] maxDp = new int[n][3];
        int[][] minDp = new int[n][3];

        for (int i = 0; i < 3; i++) {
            maxDp[0][i] = minDp[0][i] = dp[0][i];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    maxDp[i][j] = Math.max(maxDp[i - 1][0], maxDp[i - 1][1]) + dp[i][j];
                    minDp[i][j] = Math.min(minDp[i - 1][0], minDp[i - 1][1]) + dp[i][j];
                } else if (j == 1) {
                    maxDp[i][j] = Math.max(Math.max(maxDp[i - 1][0], maxDp[i - 1][1]), maxDp[i - 1][2]) + dp[i][j];
                    minDp[i][j] = Math.min(Math.min(minDp[i - 1][0], minDp[i - 1][1]), minDp[i - 1][2]) + dp[i][j];
                } else {
                    maxDp[i][j] = Math.max(maxDp[i - 1][1], maxDp[i - 1][2]) + dp[i][j];
                    minDp[i][j] = Math.min(minDp[i - 1][1], minDp[i - 1][2]) + dp[i][j];
                }
            }
        }

        int max = maxDp[n - 1][0];
        int min = minDp[n - 1][0];

        for (int i = 1; i < 3; i++) {
            max = Math.max(max, maxDp[n - 1][i]);
            min = Math.min(min, minDp[n - 1][i]);
        }

        System.out.println(max + " " + min);
    }
}
