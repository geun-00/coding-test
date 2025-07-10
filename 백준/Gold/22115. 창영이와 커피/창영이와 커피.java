import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dp = new int[K + 1];
        int inf = Integer.MAX_VALUE;
        Arrays.fill(dp, inf);
        dp[0] = 0;

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int C = Integer.parseInt(st.nextToken());

            for (int j = K; j >= C; j--) {
                if (dp[j - C] != inf) {
                    dp[j] = Math.min(dp[j], dp[j - C] + 1);
                }
            }
        }

        System.out.println(dp[K] == inf ? -1 : dp[K]);
    }
}