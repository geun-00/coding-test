import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int INF = Integer.MAX_VALUE;

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        int[] dp = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = INF;
        }
        dp[0] = 0;

        for (int i = 0; i < n; i++) {

            if (dp[i] == INF) continue;

            for (int j = 1; j <= arr[i]; j++) {
                if (i + j >= n) {
                    break;
                }
                dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
            }
        }

        System.out.println(dp[n - 1] == INF ? -1 : dp[n - 1]);
    }
}