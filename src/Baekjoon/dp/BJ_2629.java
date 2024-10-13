package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2629">백준 2629번 - DP : 양팔저울</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2629%EB%B2%88-%EC%96%91%ED%8C%94%EC%A0%80%EC%9A%B8">velog</a>
 *
 * @since 2024-10-07
 */
public class BJ_2629 {

    static final int MAX_WEIGHT = 15000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] weights = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        boolean[][] dp = new boolean[n + 1][MAX_WEIGHT + 1];
        dp[0][0] = true;

        for (int i = 1; i <= n; i++) {

            int weight = weights[i];

            for (int j = 0; j <= MAX_WEIGHT; j++) {

                if (dp[i - 1][j]) { //이전 번째에서 해당 무게를 만들 수 있을 때만

                    dp[i][j] = true; //추를 올리지 않는 경우

                    if (weight + j <= MAX_WEIGHT) { //추를 왼쪽에 올리는 경우
                        dp[i][weight + j] = true;
                    }

                    if (Math.abs(j - weight) <= MAX_WEIGHT) { //추를 오른쪽에 올리는 경우
                        dp[i][Math.abs(j - weight)] = true;
                    }
                }
            }
        }

        int m = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (num <= MAX_WEIGHT && dp[n][num]) {
                sb.append("Y ");
            } else {
                sb.append("N ");
            }
        }
        System.out.print(sb);
    }
}