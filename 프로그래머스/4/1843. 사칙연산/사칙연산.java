class Solution {
    
    public int solution(String arr[]) {
        
        int n = arr.length / 2 + 1; //숫자의 개수

        int[][][] dp = new int[n][n][2];

        for (int i = 0; i < n; i++) {
            dp[i][i][0] = dp[i][i][1] = Integer.parseInt(arr[i * 2]);
        }

        for (int len = 1; len < n; len++) {
            for (int s = 0; s < n - len; s++) {

                int e = s + len;

                dp[s][e][0] = Integer.MIN_VALUE;
                dp[s][e][1] = Integer.MAX_VALUE;

                for (int m = s; m < e; m++) {

                    int leftMax = dp[s][m][0];
                    int leftMin = dp[s][m][1];
                    int rightMax = dp[m + 1][e][0];
                    int rightMin = dp[m + 1][e][1];

                    if (arr[2 * m + 1].equals("+")) {
                        dp[s][e][0] = Math.max(dp[s][e][0], leftMax + rightMax);
                        dp[s][e][1] = Math.min(dp[s][e][1], leftMin + rightMin);
                    } else {
                        dp[s][e][0] = Math.max(dp[s][e][0], leftMax - rightMin);
                        dp[s][e][1] = Math.min(dp[s][e][1], leftMin - rightMax);
                    }
                }
            }
        }

        return dp[0][n - 1][0];
    }
}