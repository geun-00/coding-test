import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        BigInteger[][] dp = new BigInteger[251][3];

        dp[0][0] = BigInteger.valueOf(0);   //2x2
        dp[0][1] = BigInteger.valueOf(0);   //2x1
        dp[0][2] = BigInteger.valueOf(1);   //1x2

        dp[1][0] = BigInteger.valueOf(0);   //2x2
        dp[1][1] = BigInteger.valueOf(0);   //2x1
        dp[1][2] = BigInteger.valueOf(1);   //1x2

        for (int i = 2; i <= 250; i++) {
            dp[i][0] = dp[i - 1][2];
            dp[i][1] = dp[i - 1][2];
            dp[i][2] = dp[i - 1][0].add(dp[i - 1][1].add(dp[i - 1][2]));
        }

        while ((line = br.readLine()) != null && !line.isEmpty()) {
            int n = Integer.parseInt(line);
            System.out.println(dp[n][0].add(dp[n][1].add(dp[n][2])));
        }
    }
}