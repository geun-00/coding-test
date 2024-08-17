package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/7579">백준 7579번 - DP : 앱</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-7579%EB%B2%88-%EC%95%B1">velog</a>
 * @since 2024-08-12
 */
public class BJ_7579 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] appMemory = new int[n + 1];
        int[] c = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            appMemory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            c[i] = Integer.parseInt(st.nextToken());
            sum += c[i];
        }

        int[][] dp = new int[n + 1][sum + 1];

        for (int i = 1; i <= n; i++) { //i번째 앱
            for (int j = 0; j <= sum; j++) { //0 ~ 비활성화 비용(c)의 합
                if (j >= c[i]) { //현재 앱이 비활성화 가능하다면
                    //현재 앱을 비활성화 했을 때의 값
                    dp[i][j] = dp[i - 1][j - c[i]] + appMemory[i];
                }
                //현재 앱을 비활성화 했을 때와 하지 않았을 때를 비교
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
            }
        }

        //최소 m만큼 메모리를 확보할 수 있는 최소 비활성화 비용 출력
        for (int i = 0; i <= sum; i++) {
            if (dp[n][i] >= m) {
                System.out.println(i);
                return;
            }
        }
    }
}