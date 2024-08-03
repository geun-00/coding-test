import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] sum;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {

            int k = Integer.parseInt(br.readLine());

            sum = new int[k + 1];
            dp = new int[k + 1][k + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= k; i++) {
                sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken());
                Arrays.fill(dp[i], -1);
            }

            System.out.println(solve(1, k));
        }
    }

    private static int solve(int s, int e) {
        if (s == e) {
            return 0;
        }
        if (dp[s][e] != -1) {
            return dp[s][e];
        }

        dp[s][e] = Integer.MAX_VALUE;
        for (int i = s; i < e; i++) {
            int temp = solve(s, i) + solve(i + 1, e) + sum[e] - sum[s - 1];
            dp[s][e] = Math.min(dp[s][e], temp);
        }

        return dp[s][e];
    }
}
