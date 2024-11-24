import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] volumes = new int[n];

        for (int i = 0; i < n; i++) {
            volumes[i] = Integer.parseInt(st.nextToken());
        }

        boolean[][] dp = new boolean[m + 1][n];

        if (s - volumes[0] >= 0) {
            dp[s - volumes[0]][0] = true;
        }
        if (s + volumes[0] <= m) {
            dp[s + volumes[0]][0] = true;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= m; j++) {

                if (dp[j][i - 1]) {
                    if (j - volumes[i] >= 0) {
                        dp[j - volumes[i]][i] = true;
                    }
                    if (j + volumes[i] <= m) {
                        dp[j + volumes[i]][i] = true;
                    }

                }
            }
        }

        for (int i = m; i >= 0; i--) {
            if (dp[i][n - 1]) {
                System.out.println(i);
                return;
            }
        }

        System.out.println(-1);
    }
}