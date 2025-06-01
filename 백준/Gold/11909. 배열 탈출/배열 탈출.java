import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][n];
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        dp[0][0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i + 1 < n) {
                    if (arr[i][j] > arr[i + 1][j]) {
                        dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j]);
                    } else {
                        dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + (arr[i + 1][j] - arr[i][j] + 1));
                    }
                }

                if (j + 1 < n) {
                    if (arr[i][j] > arr[i][j + 1]) {
                        dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j]);
                    } else {
                        dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + (arr[i][j + 1] - arr[i][j] + 1));
                    }
                }
            }
        }

        System.out.println(dp[n - 1][n - 1]);
    }
}