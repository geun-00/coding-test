class Solution {
    public int solution(int[][] triangle) {
        
        int len = triangle.length;

        int[][] dp = new int[len][len];

        for (int i = 0; i < len; i++) {
            dp[len - 1][i] = triangle[len - 1][i];
        }

        for (int i = len - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = triangle[i][j] + Math.max(dp[i + 1][j], dp[i + 1][j + 1]);
            }
        }

        return dp[0][0];
    }
}