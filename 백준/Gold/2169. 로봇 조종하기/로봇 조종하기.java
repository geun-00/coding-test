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

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for (int i = 1; i <= m; i++) {
            dp[1][i] = dp[1][i - 1] + arr[1][i];
        }

        int[][] temp = new int[2][m + 2];

        for (int i = 2; i <= n; i++) {
            
            Arrays.fill(temp[0], Integer.MIN_VALUE);
            Arrays.fill(temp[1], Integer.MIN_VALUE);

            for (int j = 1; j <= m; j++) {
                temp[0][j] = Math.max(temp[0][j - 1], dp[i - 1][j]) + arr[i][j];
            }

            for (int j = m; j >= 1; j--) {
                temp[1][j] = Math.max(temp[1][j + 1], dp[i - 1][j]) + arr[i][j];
            }

            for (int j = 1; j <= m; j++) {
                dp[i][j] = Math.max(temp[0][j], temp[1][j]);
            }

        }

        System.out.println(dp[n][m]);
    }
}