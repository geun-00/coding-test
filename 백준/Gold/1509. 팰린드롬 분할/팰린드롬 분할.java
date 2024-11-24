import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] arr = br.readLine().toCharArray();

        int n = arr.length;

        boolean[][] isPalindrome = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            isPalindrome[i][i] = true;

            if (i < n - 1 && arr[i] == arr[i + 1]) {
                isPalindrome[i][i + 1] = true;
            }
        }

        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {

                if (arr[i] == arr[j] && isPalindrome[i + 1][j - 1]) {
                    isPalindrome[i][j] = true;
                }
            }
        }

        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {

            if (isPalindrome[0][i]) {
                dp[i] = 1;
            } else {
                dp[i] = Integer.MAX_VALUE;

                for (int j = 0; j < i; j++) {
                    if (isPalindrome[j + 1][i]) {
                        dp[i] = Math.min(dp[i], dp[j] + 1);
                    }
                }
            }
        }

        System.out.println(dp[n - 1]);
    }
}