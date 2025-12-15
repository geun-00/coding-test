import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        int[] dp = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            if (i > 0) {
                dp[i] = dp[i - 1];
            }
            int min = arr[i];
            int max = arr[i];

            for (int j = i - 1; j >= 0; j--) {
                min = Math.min(min, arr[j]);
                max = Math.max(max, arr[j]);

                if (j > 0) {
                    dp[i] = Math.max(dp[i], dp[j - 1] + (max - min));
                } else {
                    dp[i] = Math.max(dp[i], (max - min));
                }
            }
        }

        System.out.println(dp[n - 1]);
    }
}