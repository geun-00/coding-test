class Solution {
    
    static final int[] IMPOSSIBLE = {-1};
    static int[] ans = IMPOSSIBLE;
    static int maxDiff = 0;
    
    public int[] solution(int n, int[] info) {
        
        solve(0, new int[11], n, info);
        return ans;
    }
    
    public void solve(int index, int[] hits, int n, int[] apeach) {

        if (index == hits.length) {
            if (n > 0) return;

            int diff = getDiff(hits, apeach);
            if (diff <= 0 || diff < maxDiff) {
                return;
            }

            if (diff > maxDiff || check(ans, hits)) {
                maxDiff = diff;
                
                if (ans == IMPOSSIBLE) ans = new int[11];

                System.arraycopy(hits, 0, ans, 0, hits.length);
            }
            return;
        }

        for (int i = 0; i <= n; i++) {
            hits[index] = i;
            solve(index + 1, hits, n - i, apeach);
            hits[index] = 0;
        }
    }
    
    public boolean check(int[] ans, int[] ryan) {

        for (int i = 10; i >= 0; i--) {
            if (ans[i] == ryan[i]) continue;
            return ryan[i] > ans[i];
        }

        return false;
    }

    public int getDiff(int[] ryan, int[] apeach) {

        int diff = 0;

        for (int i = 0; i < apeach.length; i++) {

            if (ryan[i] == 0 && apeach[i] == 0) continue;

            if (ryan[i] <= apeach[i]) {
                diff -= (10 - i);
            } else {
                diff += (10 - i);
            }
        }

        return diff;
    }
}