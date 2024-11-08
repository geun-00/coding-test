import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][n];
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            dp[n - 1][i] = arr[n - 1][i];
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = arr[i][j] + Math.max(dp[i + 1][j], dp[i + 1][j + 1]);
            }
        }

        System.out.println(dp[0][0]);
    }
}