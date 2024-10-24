import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        int[][] dp = new int[4][10001];

        dp[1][1] = 1;

        dp[1][2] = 1;
        dp[2][2] = 1;

        dp[1][3] = 1;
        dp[2][3] = 1;
        dp[3][3] = 1;

        for (int i = 4; i <= 10000; i++) {
            dp[1][i] = dp[1][i - 1];
            dp[2][i] = dp[1][i - 2] + dp[2][i - 2];
            dp[3][i] = dp[3][i - 3] + dp[2][i - 3] + dp[1][i - 3];
        }

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            bw.write(String.valueOf(dp[1][n] + dp[2][n] + dp[3][n]) + "\n");
        }
        bw.flush();

        bw.close();
        br.close();
    }
}
