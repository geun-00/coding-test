import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static final int INF = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] heavy = new int[n][n];
        int[][] light = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(heavy[i], INF);
            Arrays.fill(light[i], INF);
            heavy[i][i] = 0;
            light[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            heavy[a][b] = 1;
            light[b][a] = 1;
        }

        for (int k = 0; k < n; k++) {
            for (int s = 0; s < n; s++) {
                for (int e = 0; e < n; e++) {
                    heavy[s][e] = Math.min(heavy[s][e], heavy[s][k] + heavy[k][e]);
                    light[s][e] = Math.min(light[s][e], light[s][k] + light[k][e]);
                }
            }
        }

        int ans = 0;
        int mid = (n + 1) / 2;

        for (int i = 0; i < n; i++) {
            int heavyCount = 0;
            int lightCount = 0;

            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (heavy[i][j] != INF) heavyCount++;
                if (light[i][j] != INF) lightCount++;
            }

            if (heavyCount >= mid || lightCount >= mid) ans++;
        }

        System.out.println(ans);
    }
}