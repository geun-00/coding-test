import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] dist = new int[n + 1][n + 1];
        int inf = (int) 1e9;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dist[i][j] = inf;
            }
            dist[i][i] = 0;
        }

        int[][] arr = new int[n + 1][2];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            arr[i][0] = l;
            arr[i][1] = r;
        }

        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (arr[i][1] >= arr[j][0] && arr[j][1] >= arr[i][0]) {
                    dist[i][j] = dist[j][i] = 1;
                }
            }
        }

        for (int k = 1; k <= n; k++) {
            for (int s = 1; s <= n; s++) {
                for (int e = 1; e <= n; e++) {
                    dist[s][e] = Math.min(dist[s][e], dist[s][k] + dist[k][e]);
                }
            }
        }

        int q = Integer.parseInt(br.readLine());
        for (int i = 0; i < q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            System.out.println(dist[a][b] == inf ? -1 : dist[a][b]);
        }
    }
}