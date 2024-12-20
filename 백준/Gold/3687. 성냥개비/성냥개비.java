import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] dp = new String[101];

        Arrays.fill(dp, String.valueOf(Long.MAX_VALUE));

        dp[2] = "1";
        dp[3] = "7";
        dp[4] = "4";
        dp[5] = "2";
        dp[6] = "6";
        dp[7] = "8";

        String[] arr = {"", "", "1", "7", "4", "2", "0", "8"};

        for (int i = 8; i <= 100; i++) {
            for (int j = 2; j <= 7; j++) {

                String original = dp[i];
                String newNum = dp[i - j] + arr[j];

                if (original.length() < newNum.length()) continue;

                if (original.length() > newNum.length() || original.compareTo(newNum) > 0) {
                    dp[i] = newNum;
                }
            }
        }

        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {

            int n = Integer.parseInt(br.readLine());

            String min = dp[n];
            String max = (n % 2 == 0)
                    ? "1".repeat(n / 2)
                    : "7" + "1".repeat((n - 3) / 2);

            sb.append(min).append(" ").append(max).append("\n");
        }

        System.out.print(sb);
    }
}