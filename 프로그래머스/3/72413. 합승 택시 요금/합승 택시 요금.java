import java.util.Arrays;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        int[][] dist = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], 201 * 100_000);
            dist[i][i] = 0;
        }

        for (int[] fare : fares) {

            int c = fare[0];
            int d = fare[1];
            int f = fare[2];

            dist[c][d] = f;
            dist[d][c] = f;
        }

        for (int k = 1; k <= n; k++) {
            for (int start = 1; start <= n; start++) {
                for (int end = 1; end <= n; end++) {
                    if (dist[start][end] > dist[start][k] + dist[k][end]) {
                        dist[start][end] = dist[start][k] + dist[k][end];
                    }
                }
            }
        }

        int min = dist[s][a] + dist[s][b];

        for (int i = 1; i <= n; i++) {
            min = Math.min(min, dist[s][i] + dist[i][a] + dist[i][b]);
        }

        return min;
    }
}