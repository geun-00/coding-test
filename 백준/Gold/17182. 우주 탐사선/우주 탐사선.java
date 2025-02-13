import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] arr;
    static int n, k;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 0; k < n; k++) {
            for (int s = 0; s < n; s++) {
                for (int e = 0; e < n; e++) {
                    if (s == e) continue;
                    arr[s][e] = Math.min(arr[s][e], arr[s][k] + arr[k][e]);
                }
            }
        }

        solve(0, 1 << k, k);
        System.out.println(ans);
    }

    private static void solve(int time, int visit, int row) {
        if (visit == (1 << n) - 1) {
            ans = Math.min(ans, time);
            return;
        }

        for (int i = 0; i < n; i++) {

            if ((visit & (1 << i)) != 0) continue;

            solve(time + arr[row][i], visit | (1 << i), i);
        }
    }
}
