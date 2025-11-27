import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] cards, prefix;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            cards = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; i++) {
                cards[i] = Integer.parseInt(st.nextToken());
            }

            prefix = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                prefix[i] = prefix[i - 1] + cards[i - 1];
            }

            dp = new int[n][n];
            System.out.println(solve(0, n - 1));
        }
    }

    private static int solve(int i, int j) {
        if (i == j) {
            return cards[i];
        }
        
        if (dp[i][j] != 0) {
            return dp[i][j];
        }

        int left = cards[i] + ((prefix[j + 1] - prefix[i + 1]) - solve(i + 1, j));
        int right = cards[j] + ((prefix[j] - prefix[i]) - solve(i, j - 1));

        return dp[i][j] = Math.max(left, right);
    }
}