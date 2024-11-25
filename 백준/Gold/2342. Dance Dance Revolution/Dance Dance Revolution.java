import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static final int INF = 999_999;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] arr = {
                {0, 2, 2, 2, 2},
                {2, 1, 3, 4, 3},
                {2, 3, 1, 3, 4},
                {2, 4, 3, 1, 3},
                {2, 3, 4, 3, 1},
        };

        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int n = input.length;

        int[][][] dp = new int[n][5][5];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    dp[i][j][k] = INF;
                }
            }
        }

        dp[0][0][0] = 0;

        int idx = 0;

        while (input[idx] != 0) {

            int point = input[idx];

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {

                    if (dp[idx][i][j] == INF) continue;

                    if (point != i) {

                        dp[idx + 1][i][point] = Math.min(
                                dp[idx + 1][i][point],
                                dp[idx][i][j] + arr[j][point]
                        );
                    }

                    if (point != j) {

                        dp[idx + 1][point][j] = Math.min(
                                dp[idx + 1][point][j],
                                dp[idx][i][j] + arr[i][point]
                        );
                    }
                }
            }

            idx++;
        }

        int ans = INF;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                ans = Math.min(dp[idx][i][j], ans);
            }
        }

        System.out.println(ans);
    }
}