import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[][][] dp = new int[101][101][101];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == -1 && b == -1 && c == -1) {
                break;
            }

            sb
                    .append("w(")
                    .append(a)
                    .append(", ")
                    .append(b)
                    .append(", ")
                    .append(c)
                    .append(") = ")
                    .append(w(a + 50, b + 50, c + 50))
                    .append("\n");
        }

        System.out.print(sb);

    }

    private static int w(int a, int b, int c) {
        if (a <= 50 || b <= 50 || c <= 50) {
            return 1;
        }
        if (a > 70 || b > 70 || c > 70) {
            return w(70, 70, 70);
        }

        if (dp[a][b][c] != -1) {
            return dp[a][b][c];
        }

        if (a < b && b < c) {
            dp[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
        } else {
            dp[a][b][c] =
                    w(a - 1, b, c) +
                    w(a - 1, b - 1, c) +
                    w(a - 1, b, c - 1) -
                    w(a - 1, b - 1, c - 1);
        }

        return dp[a][b][c];
    }
}