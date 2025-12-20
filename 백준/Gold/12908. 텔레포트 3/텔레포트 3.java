import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = 8;

        int[][] pos = new int[n][2];
        for (int i = 0; i < 2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            pos[i][0] = Integer.parseInt(st.nextToken());
            pos[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 2; i < n; i += 2) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            pos[i][0] = Integer.parseInt(st.nextToken());
            pos[i][1] = Integer.parseInt(st.nextToken());
            pos[i + 1][0] = Integer.parseInt(st.nextToken());
            pos[i + 1][1] = Integer.parseInt(st.nextToken());
        }

        long[][] dist = new long[n][n];
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                long temp = Math.abs(pos[i][0] - pos[j][0]) + Math.abs(pos[i][1] - pos[j][1]);

                dist[i][j] = temp;
                dist[j][i] = temp;
            }
        }

        for (int i = 2; i < n; i += 2) {
            dist[i][i + 1] = Math.min(dist[i][i + 1], 10);
            dist[i + 1][i] = Math.min(dist[i + 1][i], 10);
        }

        for (int k = 0; k < n; k++) {
            for (int s = 0; s < n; s++) {
                for (int e = 0; e < n; e++) {
                    dist[s][e] = Math.min(dist[s][e], dist[s][k] + dist[k][e]);
                }
            }
        }

        System.out.println(dist[0][1]);
    }
}