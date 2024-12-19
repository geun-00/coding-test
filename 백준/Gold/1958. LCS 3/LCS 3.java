import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String a = br.readLine();
        String b = br.readLine();
        String c = br.readLine();

        int lenA = a.length();
        int lenB = b.length();
        int lenC = c.length();

        int[][][] dp = new int[lenA + 1][lenB + 1][lenC + 1];

        for (int i = 1; i <= lenA; i++) {
            for (int j = 1; j <= lenB; j++) {
                for (int k = 1; k <= lenC; k++) {

                    if (a.charAt(i - 1) == b.charAt(j - 1) && a.charAt(i - 1) == c.charAt(k - 1)) {
                        dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
                    } else {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], Math.max(dp[i][j - 1][k], dp[i][j][k - 1]));
                    }
                }
            }
        }

        System.out.println(dp[lenA][lenB][lenC]);

    }
}