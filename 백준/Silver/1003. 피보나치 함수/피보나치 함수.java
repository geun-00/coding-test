import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        int[][] dp = new int[2][41];

        dp[0][0] = 1;
        dp[1][1] = 1;

        for (int i = 2; i <= 40; i++) {
            dp[0][i] = dp[0][i - 1] + dp[0][i - 2];
            dp[1][i] = dp[1][i - 1] + dp[1][i - 2];
        }

        while (t-- > 0) {

            int n = Integer.parseInt(br.readLine());
            sb.append(dp[0][n]).append(" ").append(dp[1][n]).append("\n");
        }

        System.out.print(sb);
    }
}