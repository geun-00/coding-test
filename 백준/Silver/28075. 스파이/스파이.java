import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m, ans;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[2][3];
        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, -1, 0);

        System.out.println(ans);
    }

    private static void solve(int sum, int prev, int days) {
        if (days == n) {
            if (sum >= m) {
                ans++;
            }
            return;
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == prev) {
                    solve(sum + arr[i][j] / 2, j, days + 1);
                } else {
                    solve(sum + arr[i][j], j, days + 1);
                }
            }
        }
    }
}