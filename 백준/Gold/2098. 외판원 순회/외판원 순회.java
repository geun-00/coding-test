import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[][] w;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        w = new int[n][n];
        dp = new int[n][1 << n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                w[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve(0, 1));
    }

    private static long solve(int now, int visited) {

        if (visited == (1 << n) - 1) {
            return w[now][0] == 0 ? Integer.MAX_VALUE : w[now][0];
        }

        if (dp[now][visited] != 0) {
            return dp[now][visited];
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if ((visited & (1 << i)) == 0 && w[now][i] != 0) {

                int next = visited | (1 << i);
                min = (int) Math.min(min, solve(i, next) + w[now][i]);

            }
        }

        return dp[now][visited] = min;
    }
}
