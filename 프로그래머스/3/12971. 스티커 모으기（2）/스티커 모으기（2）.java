class Solution {
    public int solution(int sticker[]) {
        
        int n = sticker.length;
        
        if (n == 1) {
            return sticker[0];
        }
        if (n == 2) {
            return Math.max(sticker[0], sticker[1]);
        }

        // 첫번째 스티커를 뜯은 경우
        int[] dp1 = new int[n - 1];
        dp1[0] = dp1[1] = sticker[0];
        for (int i = 2; i < n - 1; i++) {
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + sticker[i]);
        }

        // 첫번째 스티커를 뜯지 않은 경우
        int[] dp2 = new int[n];
        dp2[1] = sticker[1];
        for (int i = 2; i < n; i++) {
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + sticker[i]);
        }

        return Math.max(dp1[dp1.length - 1], dp2[dp2.length - 1]);
    }
}