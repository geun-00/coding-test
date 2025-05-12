import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int n;
    static char[][] arr;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new char[n][n];
        dp = new int[n][1 << n][10];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        System.out.println(solve(0, 1, 0));
    }

    private static int solve(int row, int visit, int price) {
        if (dp[row][visit][price] != 0) return dp[row][visit][price];

        dp[row][visit][price] = 1;

        for (int i = 0; i < n; i++) {
            if (((visit & (1 << i)) == 0) && arr[row][i] - '0' >= price) {
                dp[row][visit][price] = Math.max(
                    dp[row][visit][price],
                    1 + solve(i, visit | (1 << i), arr[row][i] - '0')
                );
            }
        }

        return dp[row][visit][price];
    }
}