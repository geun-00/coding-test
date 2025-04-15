import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= n; tc++) {
            String ans = "no";

            StringTokenizer st = new StringTokenizer(br.readLine());
            char[] a = st.nextToken().toCharArray();
            char[] b = st.nextToken().toCharArray();
            char[] c = st.nextToken().toCharArray();

            int lenA = a.length;
            int lenB = b.length;

            boolean[][] dp = new boolean[lenA + 1][lenB + 1];
            dp[0][0] = true;

            for (int i = 1; i <= lenA; i++) dp[i][0] = dp[i - 1][0] && a[i - 1] == c[i - 1];
            for (int i = 1; i <= lenB; i++) dp[0][i] = dp[0][i - 1] && b[i - 1] == c[i - 1];

            for (int i = 1; i <= lenA; i++) {
                for (int j = 1; j <= lenB; j++) {
                    dp[i][j] =
                        (dp[i - 1][j] && a[i - 1] == c[i + j - 1]) ||
                        (dp[i][j - 1] && b[j - 1] == c[i + j - 1]);
                }
            }

            if (dp[lenA][lenB]) ans = "yes";

            sb.append("Data set ")
              .append(tc)
              .append(": ")
              .append(ans)
              .append("\n");
        }

        System.out.print(sb);
    }
}