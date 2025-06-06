import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int D = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        int[] dp = new int[D + 1];
        dp[0] = Integer.MAX_VALUE;

        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());

            int L = Integer.parseInt(st.nextToken()); //길이
            int C = Integer.parseInt(st.nextToken()); //용량

            for (int j = D - L; j >= 0; j--) {
                if (dp[j] > 0) {
                    dp[j + L] = Math.max(dp[j + L], Math.min(dp[j], C));
                }
            }
        }

        System.out.println(dp[D]);
    }
}