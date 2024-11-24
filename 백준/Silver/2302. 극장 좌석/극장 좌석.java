import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[41];

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int ans = 1;

        int prev = 0;

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            int num = Integer.parseInt(br.readLine());

            ans *= dp[num - prev - 1];

            prev = num;
        }

        ans *= dp[n - prev];

        System.out.println(ans);
    }
}