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

        int[][] prefix = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= m; j++) {
                prefix[i][j] = prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1] + Integer.parseInt(st.nextToken());
            }
        }

        int ans = Integer.MIN_VALUE;
        for (int x1 = 1; x1 <= n; x1++) {
            for (int y1 = 1; y1 <= m; y1++) {
                for (int x2 = x1; x2 <= n; x2++) {
                    for (int y2 = y1; y2 <= m; y2++) {
                        ans = Math.max(ans, prefix[x2][y2] - prefix[x1 - 1][y2] - prefix[x2][y1 - 1] + prefix[x1 - 1][y1 - 1]);
                    }
                }
            }
        }

        System.out.println(ans);
    }
}