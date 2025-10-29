import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            int[][] arr = new int[n][n];

            for (int i = 0; i < n; i++) {
                char[] array = br.readLine().toCharArray();

                for (int j = 0; j < n; j++) {
                    arr[i][j] = array[j] - '0';
                }
            }

            cookie(arr, n, 0, 0);

            int ans = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    ans += arr[i][j];
                }
            }

            System.out.println(ans);
        }
    }

    private static void cookie(int[][] arr, int n, int x, int y) {
        if (n <= 1) {
            return;
        }

        int sum = 0;
        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                sum += arr[i][j];
            }
        }

        int half = n / 2;
        int[][] temp = {{x, y}, {x, y + half}, {x + half, y}, {x + half, y + half}};

        int[] p = temp[sum % 4];
        for (int i = p[0]; i < p[0] + half; i++) {
            for (int j = p[1]; j < p[1] + half; j++) {
                arr[i][j] = 0;
            }
        }

        for (int i = 0; i < 4; i++) {
            if (i != (sum % 4)) {
                cookie(arr, half, temp[i][0], temp[i][1]);
            }
        }
    }
}