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

        int[][] arr = new int[n + 1][m + 1];
        int[][] dp = new int[n + 1][m + 1];
        int[][] track = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int in = Integer.parseInt(st.nextToken());

            for (int j = 1; j <= m; j++) {
                arr[in][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 0; k <= j; k++) {
                    int c = dp[j - k][i - 1] + arr[k][i];
                    if (dp[j][i] < c) {
                        dp[j][i] = c;
                        track[j][i] = k;
                    }
                }
            }
        }

        int[] invest = new int[m + 1];
        int remain = n;

        for (int i = m; i > 0; i--) {
            invest[i] = track[remain][i];
            remain -= invest[i];
        }

        System.out.println(dp[n][m]);

        for (int i = 1; i <= m; i++) {
            System.out.print(invest[i] + " ");
        }
    }
}