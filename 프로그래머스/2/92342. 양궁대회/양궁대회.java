class Solution {
    
    static final int[] IMPOSSIBLE = {-1};
    static int[] ans = IMPOSSIBLE;
    static int maxDiff = 0;
    
    public int[] solution(int n, int[] info) {
        
        solve(0, new int[11], n, info);
        return ans;
    }
    
    public void solve(int index, int[] ryan, int n, int[] apeach) {

        if (index == ryan.length) {
            
            //남은 화살이 있을 때
            if (n > 0) return;

            //라이언과 어피치의 점수 차
            int diff = getDiff(ryan, apeach);
            
            if (diff <= 0 || diff < maxDiff) {
                return;
            }

            if (diff > maxDiff || check(ryan)) {
                maxDiff = diff;
                
                if (ans == IMPOSSIBLE) ans = new int[11];

                System.arraycopy(ryan, 0, ans, 0, ryan.length);
                // ans = ryan; 주의! 얕은 복사 X
            }
            
            return;
        }

        for (int i = 0; i <= n; i++) {
            ryan[index] = i;  //각 점수에 0개 ~ N개를 맞힐 수 있다.
            solve(index + 1, ryan, n - i, apeach);
            ryan[index] = 0;  //원상복구
        }
    }
    
    public boolean check(int[] ryan) {

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