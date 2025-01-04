class Solution {
    public int solution(int[][] board, int[][] skill) {
        
        int n = board.length;
        int m = board[0].length;

        int[][] arr = new int[n + 2][m + 2];

        for (int[] sk : skill) {

            int type = sk[0];
            int r1 = sk[1] + 1;
            int c1 = sk[2] + 1;
            int r2 = sk[3] + 1;
            int c2 = sk[4] + 1;
            int degree = sk[5];

            if (type == 1) {
                degree = -degree;
            }

            arr[r1][c1] += degree;
            arr[r1][c2 + 1] -= degree;
            arr[r2 + 1][c1] -= degree;
            arr[r2 + 1][c2 + 1] += degree;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                arr[i][j] = arr[i - 1][j] + arr[i][j - 1] - arr[i - 1][j - 1] + arr[i][j];
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i + 1][j + 1] + board[i][j] > 0) {
                    ans++;
                }
            }
        }

        return ans;
    }
}