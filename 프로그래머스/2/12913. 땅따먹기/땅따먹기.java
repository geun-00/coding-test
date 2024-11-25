class Solution {
    int solution(int[][] land) {
        
        int ans = 0;

        int n = land.length;
        
        int[][] dp = new int[n][4];
        
        for(int i = 0; i < 4 ; i++){
            dp[0][i] = land[0][i];
        }
        
        for(int i = 1; i < n; i++){
            
            dp[i][0] = land[i][0] + Math.max(dp[i-1][1], Math.max(dp[i-1][2], dp[i-1][3]));
            dp[i][1] = land[i][1] + Math.max(dp[i-1][0], Math.max(dp[i-1][2], dp[i-1][3]));
            dp[i][2] = land[i][2] + Math.max(dp[i-1][0], Math.max(dp[i-1][1], dp[i-1][3]));
            dp[i][3] = land[i][3] + Math.max(dp[i-1][0], Math.max(dp[i-1][1], dp[i-1][2]));
        }
        
        for(int i = 0; i < 4; i++){
            ans = Math.max(ans, dp[n - 1][i]);
        }

        return ans;
    }
}