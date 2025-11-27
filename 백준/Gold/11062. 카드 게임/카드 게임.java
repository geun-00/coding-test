import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] a = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }

            // prefix sum
            int[] prefix = new int[N + 1];
            for (int i = 0; i < N; i++) {
                prefix[i + 1] = prefix[i] + a[i];
            }

            // 구간 합 함수: [l..r]
            // rangeSum = prefix[r+1] - prefix[l]

            int[][] dp = new int[N][N];

            // 길이 1 구간
            for (int i = 0; i < N; i++) {
                dp[i][i] = a[i];
            }

            // 길이 2 이상 구간
            for (int len = 2; len <= N; len++) {
                for (int i = 0; i + len - 1 < N; i++) {
                    int j = i + len - 1;

                    int sumLeftRest = prefix[j + 1] - prefix[i + 1]; // sum(i+1..j)
                    int sumRightRest = prefix[j] - prefix[i];        // sum(i..j-1)

                    int takeLeft  = a[i] + (sumLeftRest  - dp[i + 1][j]);
                    int takeRight = a[j] + (sumRightRest - dp[i][j - 1]);

                    dp[i][j] = Math.max(takeLeft, takeRight);
                }
            }

            int geunwoo = dp[0][N - 1];
            sb.append(geunwoo).append('\n');
        }

        System.out.print(sb);
    }
}