import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        long[][] dp = new long[n + m + 1][n + m + 1];

        for (int i = 0; i <= n + m; i++) {
            dp[i][0] = dp[i][i] = 1;
            for (int j = 1; j < i; j++) {
                dp[i][j] = Math.min(dp[i - 1][j] + dp[i - 1][j - 1], 1_000_000_001);
            }
        }

        if (k > dp[n + m][n]) {
            System.out.println(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();

        while (n > 0 && m > 0) {

            long a = dp[n + m - 1][n - 1];

            if (k <= a) {
                sb.append("a");
                n--;
            } else {
                sb.append("z");
                m--;
                k -= a;
            }
        }

        sb.append("a".repeat(n));
        sb.append("z".repeat(m));

        System.out.print(sb);
    }
}