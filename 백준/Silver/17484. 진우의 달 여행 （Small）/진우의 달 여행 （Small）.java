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

        int[][] arr = new int[n][m];
        int[][][] dp = new int[n][m][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = 1_000_000;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 3; j++) {
                dp[0][i][j] = arr[0][i];
            }
        }

        int[] dy = {-1, 0, 1};

        for (int x = 1; x < n; x++) {
            for (int y = 0; y < m; y++) {
                for (int i = 0; i < 3; i++) {
                    int col = y + dy[i];
                    if (col < 0 || col >= m) {
                        continue;
                    }

                    for (int j = 0; j < 3; j++) {
                        if (i == j) {
                            continue;
                        }
                        dp[x][y][i] = Math.min(dp[x][y][i], dp[x - 1][col][j] + arr[x][y]);
                    }
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 3; j++) {
                ans = Math.min(ans, dp[n - 1][i][j]);
            }
        }

        System.out.println(ans);
    }
}