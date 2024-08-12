import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] appMemory = new int[n + 1];
        int[] c = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            appMemory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            c[i] = Integer.parseInt(st.nextToken());
            sum += c[i];
        }

        int[][] dp = new int[n + 1][sum + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                if (j >= c[i]) {
                    dp[i][j] = dp[i - 1][j - c[i]] + appMemory[i];
                }
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
            }
        }

        for (int i = 0; i <= sum; i++) {
            if (dp[n][i] >= m) {
                System.out.println(i);
                return;
            }
        }
    }
}
