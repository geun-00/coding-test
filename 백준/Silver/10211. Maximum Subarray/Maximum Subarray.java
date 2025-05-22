import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for (int i = 0; i < tc; i++) {
            int n = Integer.parseInt(br.readLine());
            String[] input = br.readLine().split(" ");

            int[] dp = new int[n + 1];
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) {
                arr[j] = Integer.parseInt(input[j]);
                dp[j + 1] = arr[j];
            }

            int max = Integer.MIN_VALUE;
            for (int j = 1; j <= n; j++) {
                dp[j] = Math.max(arr[j - 1], arr[j - 1] + dp[j - 1]);
                max = Math.max(max, dp[j]);
            }
            System.out.println(max);
        }
    }
}