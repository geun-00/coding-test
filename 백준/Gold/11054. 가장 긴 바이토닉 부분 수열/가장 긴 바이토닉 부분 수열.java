import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        int[] dp = new int[n];
        int[] dp_r = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
            dp_r[i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (arr[i] > arr[j] && dp[i] + 1 == dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= i; j--) {
                if (arr[i] > arr[j] && dp_r[i] + 1 == dp_r[j] + 1) {
                    dp_r[i] = dp_r[j] + 1;
                }
            }
        }

        int max = 0;

        for (int i = 0; i < n; i++) {
            max = Math.max(max, dp[i] + dp_r[i]);
        }

        System.out.println(max - 1);
    }
}
