import java.util.Arrays;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], 1_000_000);
            dist[i][i] = 0;
        }

        for (int[] r : road) {
            int a = r[0] - 1;
            int b = r[1] - 1;
            int c = r[2];

            dist[a][b] = Math.min(dist[a][b], c);
            dist[b][a] = Math.min(dist[b][a], c);
        }

        for (int k = 0; k < N; k++) {
            for (int s = 0; s < N; s++) {
                for (int e = 0; e < N; e++) {
                    dist[s][e] = Math.min(dist[s][e], dist[s][k] + dist[k][e]);
                }
            }
        }

        int ans = 0;

        for (int i = 0; i < N; i++) {
            if (dist[0][i] <= K) {
                ans++;
            }
        }

        return ans;
    }
}