import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] dist = new int[n + 1][n + 1];
        final int inf = 1_000_000;

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dist[i][j] = inf;
            }
            dist[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dist[a][b] = dist[b][a] = 1;
        }

        for (int k = 1; k <= n; k++) {
            for (int s = 1; s <= n; s++) {
                for (int e = 1; e <= n; e++) {
                    if (dist[s][e] > dist[s][k] + dist[k][e]) {
                        dist[s][e] = dist[s][k] + dist[k][e];
                    }
                }
            }
        }
        
        int num1 = 101;
        int num2 = 101;
        int time = inf;

        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {

                int temp = 0;

                for (int k = 1; k <= n; k++) {
                    temp += Math.min(dist[k][i], dist[k][j]) * 2;
                }

                if (temp < time) {
                    time = temp;
                    num1 = i;
                    num2 = j;
                }
            }
        }

        System.out.println(num1 + " " + num2 + " " + time);
    }
}