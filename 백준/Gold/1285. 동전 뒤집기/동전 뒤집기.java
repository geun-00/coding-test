import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                arr[i][j] = (chars[j] == 'H') ? 0 : 1;
            }
        }

        int ans = n * n;

        for (int bit = 0; bit < (1 << n); bit++) {
            turnRows(arr, n, bit);
            ans = Math.min(ans, solve(arr, n));
            turnRows(arr, n, bit);
        }

        System.out.println(ans);
    }

    private static void turnRows(int[][] arr, int n, int bit) {

        for (int i = 0; i < n; i++) {

            if (((bit & (1 << i)) != 0)) {
                for (int j = 0; j < n; j++) {

                    arr[i][j] = 1 - arr[i][j];
                }
            }

        }
    }

    private static int solve(int[][] arr, int n) {

        int total = 0;

        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (arr[j][i] == 1) {
                    count++;
                }
            }

            total += Math.min(count, n - count);
        }

        return total;
    }
}