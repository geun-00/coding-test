import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        long[] dp = new long[1_000_001];
        dp[0] = dp[1] = 1;
        dp[2] = 2;

        final int mod = 1_000_000_009;

        for (int i = 3; i <= 1_000_000; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
            dp[i] %= mod;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");
        }

        System.out.print(sb);
    }
}