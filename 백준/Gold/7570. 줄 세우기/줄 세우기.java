import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] dp = new int[n + 1];

        int max = 0;

        for (int i = 0; i < n; i++) {

            int num = Integer.parseInt(st.nextToken());
            dp[num] = dp[num - 1] + 1;

            max = Math.max(max, dp[num]);
        }

        System.out.println(n - max);
    }
}