import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine());

        int[] dp = new int[100];
        dp[1] = "Messi".length();
        dp[2] = dp[1] + " Gimossi".length();

        int i = 3;
        while (true) {
            dp[i] = dp[i - 1] + 1 + dp[i - 2];
            if (dp[i] >= m) {
                break;
            }
            i++;
        }

        System.out.println(solve(i, m, dp));
    }

    private static String solve(int n, int m, int[] dp) {
        if (n == 1) {
            if (m > dp[1]) {
                return "Messi Messi Gimossi";
            }
            return "Messi".substring(m - 1, m);
        }
        else if (n == 2) {
            if (m > dp[2]) {
                return "Messi Messi Gimossi";
            }
            String result = "Messi Gimossi".substring(m - 1, m);
            return result.isBlank() ? "Messi Messi Gimossi" : result;
        }

        if (m <= dp[n - 1]) {
            return solve(n - 1, m, dp);
        } else if (m == dp[n - 1] + 1) {
            return "Messi Messi Gimossi";
        } else {
            return solve(n - 2, m - dp[n - 1] - 1, dp);
        }
    }
}