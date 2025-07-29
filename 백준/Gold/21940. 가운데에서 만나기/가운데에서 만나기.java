import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static final int INF = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = INF;
            }
            arr[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            arr[a][b] = c;
        }

        for (int k = 0; k < n; k++) {
            for (int s = 0; s < n; s++) {
                for (int e = 0; e < n; e++) {
                    if (arr[s][k] != INF && arr[k][e] != INF) {
                        arr[s][e] = Math.min(arr[s][e], arr[s][k] + arr[k][e]);
                    }
                }
            }
        }

        int k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] cities = new int[k];

        for (int i = 0; i < k; i++) {
            cities[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        int min = INF;
        List<Integer> result = new ArrayList<>();

        for (int x = 0; x < n; x++) {
            int max = 0;
            boolean isValid = true;

            for (int city : cities) {
                if (arr[city][x] == INF || arr[x][city] == INF) {
                    isValid = false;
                    break;
                }
                max = Math.max(max, arr[city][x] + arr[x][city]);
            }

            if (!isValid) continue;

            if (max < min) {
                min = max;
                result.clear();
                result.add(x + 1);
            } else if (max == min) {
                result.add(x + 1);
            }
        }

        for (int x : result) {
            System.out.print(x + " ");
        }
    }
}