class Solution {
    public int solution(int n, int[][] results) {
        
        int[][] rank = new int[n + 1][n + 1];

        for (int[] result : results) {
            int a = result[0];
            int b = result[1];
            rank[a][b] = 1;
        }

        for (int k = 1; k <= n; k++) {
            for (int s = 1; s <= n; s++) {
                for (int e = 1; e <= n; e++) {
                    if (rank[s][k] == 1 && rank[k][e] == 1) {
                        rank[s][e] = 1;
                    }
                }
            }
        }

        int result = 0;
        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= n; j++) {
                if (rank[i][j] == 1 || rank[j][i] == 1) {
                    count++;
                }
            }
            if (count == n - 1) {
                result++;
            }
        }

        return result;
    }
}