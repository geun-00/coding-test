import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        BigInteger[] dp = new BigInteger[251];

        dp[0] = dp[1] = BigInteger.valueOf(1);
        dp[2] = BigInteger.valueOf(3);

        for (int i = 3; i <= 250; i++) {
            dp[i] = dp[i - 1].add(dp[i - 2].multiply(BigInteger.valueOf(2)));
        }

        while ((line = br.readLine()) != null && !line.isEmpty()) {
            int n = Integer.parseInt(line);
            System.out.println(dp[n]);
        }
    }
}