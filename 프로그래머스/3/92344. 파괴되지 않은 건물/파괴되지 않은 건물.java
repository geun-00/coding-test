class Solution {
    public int solution(int[][] board, int[][] skill) {
        
        int n = board.length;
        int m = board[0].length;

        int[][] arr = new int[n + 1][m + 1];
        
        for (int[] s : skill) {

            int type = s[0];
            int x1 = s[1];
            int y1 = s[2];
            int x2 = s[3];
            int y2 = s[4];
            int degree = s[5] * ((type == 1) ? -1 : 1);

            arr[x1][y1] += degree;
            arr[x1][y2 + 1] += degree * -1;
            arr[x2 + 1][y1] += degree * -1;
            arr[x2 + 1][y2 + 1] += degree;
        
        }

        for (int x = 0; x < n; x++) {
            for (int y = 1; y < m; y++) {
                arr[x][y] += arr[x][y - 1];
            }
        }

        for (int y = 0; y < m; y++) {
            for (int x = 1; x < n; x++) {
                arr[x][y] += arr[x - 1][y];
            }
        }

        int result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] + arr[i][j] > 0) {
                    result++;
                }
            }
        }

        return result;
    }
}