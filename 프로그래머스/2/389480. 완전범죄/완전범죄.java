class Solution {
    int ans = -1;
    boolean[][][] visit;
    
    public int solution(int[][] info, int n, int m) {
        visit = new boolean[info.length][n][m];
        solve(info, n, m, 0, 0, 0);

        return ans;
    }

    public void solve(int[][] info, int n, int m, int a, int b, int depth) {
        if (a >= n || b >= m) {
            return;
        }

        if (ans > -1 && ans < a) {
            return;
        }

        if (depth == info.length) {
            if (ans == -1 || a < ans) {
                ans = a;
            }
            return;
        }
        
        if (visit[depth][a][b]) {
            return;
        }
        visit[depth][a][b] = true;

        solve(info, n, m, a + info[depth][0], b, depth + 1);
        solve(info, n, m, a, b + info[depth][1], depth + 1);
    }
}