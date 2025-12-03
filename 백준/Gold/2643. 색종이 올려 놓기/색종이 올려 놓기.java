import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] papers = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            papers[i][0] = Math.min(a, b);
            papers[i][1] = Math.max(a, b);
        }

        Arrays.sort(papers, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        int[] dp = new int[n];
        int ans = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                if (papers[i][0] >= papers[j][0] && papers[i][1] >= papers[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            ans = Math.max(ans, dp[i]);
        }

        System.out.println(ans);
    }
}