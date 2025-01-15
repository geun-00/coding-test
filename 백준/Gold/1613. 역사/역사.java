import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        final int inf = 100_000;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) arr[i][j] = inf;
            }
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            arr[a][b] = 1;
        }

        for (int p = 0; p < n; p++) {
            for (int s = 0; s < n; s++) {
                for (int e = 0; e < n; e++) {
                    arr[s][e] = Math.min(arr[s][e], arr[s][p] + arr[p][e]);
                }
            }
        }

        int s = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            int ans = 0;

            if (arr[a][b] < arr[b][a]) {
                ans = -1;
            }
            else if(arr[a][b] > arr[b][a]) {
                ans = 1;
            }

            sb.append(ans)
              .append("\n");
        }

        System.out.print(sb);
    }
}