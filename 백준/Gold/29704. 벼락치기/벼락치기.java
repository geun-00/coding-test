import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int total = 0;
        int[] dp = new int[t + 1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int d = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            total += m;

            for (int j = t; j >= d; j--) {
                dp[j] = Math.max(dp[j], dp[j - d] + m);
            }
        }

        System.out.println(total - dp[t]);
    }
}