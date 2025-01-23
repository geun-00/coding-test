import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] dp = new int[d + 1];
        dp[d] = k;

        for (int i = k - 1; i >= 1; i--) {
            dp[d - 1] = i;
            dp[d - 2] = k - i;

            for (int j = d - 3; j >= 1; j--) {
                dp[j] = dp[j + 2] - dp[j + 1];
            }

            if ((dp[1] > 0 && dp[2] > 0) && (dp[1] < dp[2])) {
                System.out.println(dp[1]);
                System.out.println(dp[2]);
                break;
            }
        }
    }
}
