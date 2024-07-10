package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href = "https://www.acmicpc.net/problem/2133">백준 2133번 - DP : 타일 채우기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2133%EB%B2%88-%ED%83%80%EC%9D%BC-%EC%B1%84%EC%9A%B0%EA%B8%B0">velog</a>
 * @since 2024-07-07
 */
public class BJ_2133 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        if (n % 2 == 1) {
            System.out.println(0);
            return;
        }

        int[] dp = new int[n + 1];

        dp[0] = 1;

        for (int i = 2; i <= n; i += 2) { //홀수는 볼 필요 없음
            dp[i] = dp[i - 2] * 3; //기본 패턴

            for (int j = 4; j <= i; j += 2) {
                dp[i] += 2 * dp[i - j]; //추가 패턴
            }
        }

        System.out.println(dp[n]);
    }
}
