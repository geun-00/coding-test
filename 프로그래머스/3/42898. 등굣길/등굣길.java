class Solution {
    public int solution(int m, int n, int[][] puddles) {
        
        int[][] dp = new int[n][m];

        for (int[] p : puddles) {
            int x = p[1] - 1;
            int y = p[0] - 1;

            dp[x][y] = -1;
        }

        int mod = 1_000_000_007;
        dp[0][0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (dp[i][j] == -1) {
                    continue;
                }

                if (i > 0) {
                    if (dp[i - 1][j] > 0) {
                        dp[i][j] += dp[i - 1][j] % mod;
                    }
                }

                if (j > 0 ) {
                    if (dp[i][j - 1] > 0) {
                        dp[i][j] += dp[i][j - 1] % mod;
                    }
                }

                dp[i][j] %= mod;
            }
        }

        return dp[n - 1][m - 1];
    }
}