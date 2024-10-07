import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] weights = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        int maxWeight = 15000;
        boolean[][] dp = new boolean[n + 1][maxWeight + 1];
        dp[0][0] = true;

        for (int i = 1; i <= n; i++) {
            int weight = weights[i];
            for (int j = 0; j <= maxWeight; j++) {
                if (dp[i - 1][j]) {
                    dp[i][j] = true;

                    if (weight + j <= maxWeight) {
                        dp[i][weight + j] = true;
                    }
                    if (Math.abs(j - weight) <= maxWeight) {
                        dp[i][Math.abs(j - weight)] = true;
                    }
                }
            }
        }

        int m = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < m; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (num <= maxWeight && dp[n][num]) {
                System.out.print("Y ");
            } else {
                System.out.print("N ");
            }
        }
    }
}