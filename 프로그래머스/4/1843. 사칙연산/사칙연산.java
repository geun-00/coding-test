import java.util.*;

class Solution {
    
    static int[][][] dp;
    static final int MIN_INF = Integer.MIN_VALUE;
    static final int MAX_INF = Integer.MAX_VALUE;
    
    public int solution(String arr[]) {
        
        int n = arr.length;
        dp = new int[n + 1][n + 1][2];
        
        for (int[][] d : dp) {
            for (int[] row : d) {
                Arrays.fill(row, MIN_INF);
            }
        }

        return solve(0, n, 0, arr);
    }
    
    public int solve(int start, int end, int type, String[] arr) {

        if (dp[start][end][type] != MIN_INF) {
            return dp[start][end][type];
        }

        if (end - start == 1) return Integer.parseInt(arr[start]);

        int result = (type == 0) ? MIN_INF : MAX_INF;

        for (int i = start + 1; i < end; i += 2) {
            int left = solve(start, i, type, arr);
            int temp;

            if (arr[i].equals("+")) {
                temp = left + solve(i + 1, end, type, arr);
            } else {
                temp = left - solve(i + 1, end, 1 - type, arr);
            }

            result = (type == 0) ? Math.max(result, temp) : Math.min(result, temp);
        }

        return dp[start][end][type] = result;
    }
}