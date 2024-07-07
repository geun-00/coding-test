import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        
        if (n % 2 == 1) {
            System.out.println(0);
            return;
        }

        int[] dp = new int[31];

        dp[0] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 2] * 3;

            for (int j = 4; j <= i; j += 2) {
                dp[i] += 2 * dp[i - j];
            }
        }

        System.out.println(dp[n]);
    }
}
