class Solution {
    public long solution(int[] sequence) {
        
        int n = sequence.length;

        int[] purse_1 = new int[n];
        int[] purse_2 = new int[n];

        for (int i = 0; i < n; i++) {
            purse_1[i] = sequence[i] * (i % 2 == 0 ? 1 : -1);
            purse_2[i] = sequence[i] * (i % 2 == 0 ? -1 : 1);
        }

        long max_1 = getMax(purse_1, n);
        long max_2 = getMax(purse_2, n);

        return Math.max(max_1, max_2);
    }
    
    public long getMax(int[] purse, int n) {

        long[] dp = new long[n];
        dp[0] = purse[0];
        long max = dp[0];

        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(purse[i], dp[i - 1] + purse[i]);
            max = Math.max(max, dp[i]);
        }

        return max;
    }
}