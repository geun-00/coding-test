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
        int h = Integer.parseInt(st.nextToken());

        int[] dp = new int[h + 1];
        dp[0] = 1;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int[] cloneDp = dp.clone();

            while (st.hasMoreTokens()) {
                int block = Integer.parseInt(st.nextToken());

                for (int j = h; j >= block; j--) {
                    cloneDp[j] += dp[j - block];
                    cloneDp[j] %= 10007;
                }
            }

            dp = cloneDp;
        }

        System.out.println(dp[h]);
    }
}