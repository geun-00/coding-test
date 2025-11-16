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
        int k = Integer.parseInt(st.nextToken());

        char[][] arr = new char[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            char[] input = br.readLine().toCharArray();

            for (int j = 1; j <= m; j++) {
                arr[i][j] = input[j - 1];
            }
        }

        int[][] black = new int[n + 1][m + 1];
        int[][] white = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                char tempB = ((i + j) % 2 == 0) ? 'B' : 'W';
                char tempW = (tempB == 'B') ? 'W' : 'B';

                if (arr[i][j] != tempB) black[i][j] = 1;
                if (arr[i][j] != tempW) white[i][j] = 1;
            }
        }

        black = getPrefixSum(black);
        white = getPrefixSum(white);

        int ans = Integer.MAX_VALUE;

        for (int i = 1; i <= n - k + 1; i++) {
            for (int j = 1; j <= m - k + 1; j++) {
                int blackSum = black[i + k - 1][j + k - 1] - black[i - 1][j + k - 1] - black[i + k - 1][j - 1] + black[i - 1][j - 1];
                int whiteSum = white[i + k - 1][j + k - 1] - white[i - 1][j + k - 1] - white[i + k - 1][j - 1] + white[i - 1][j - 1];

                ans = Math.min(ans, Math.min(blackSum, whiteSum));
            }
        }

        System.out.println(ans);
    }

    private static int[][] getPrefixSum(int[][] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr[i].length; j++) {
                arr[i][j] = arr[i - 1][j] + arr[i][j - 1] - arr[i - 1][j - 1] + arr[i][j];
            }
        }

        return arr;
    }
}