import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        char[][] arr = new char[n][n];
        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 'Y') {
                    dist[i][j] = 1;
                } else {
                    dist[i][j] = 10000;
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int s = 0; s < n; s++) {
                for (int e = 0; e < n; e++) {
                    dist[s][e] = Math.min(dist[s][e], dist[s][k] + dist[k][e]);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && dist[i][j] <= 2) {
                    count++;
                }
            }
            ans = Math.max(ans, count);
        }

        System.out.println(ans);
    }
}
