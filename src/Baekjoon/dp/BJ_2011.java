package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href = "https://www.acmicpc.net/problem/2011">백준 2011번 - DP : 암호코드</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2011%EB%B2%88-%EC%95%94%ED%98%B8%EC%BD%94%EB%93%9C">velog</a>
 * @since 2024-08-10
 */
public class BJ_2011 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] arr = br.readLine().toCharArray();

        if (arr[0] == '0') {
            System.out.println(0);
            return;
        }

        int[] dp = new int[arr.length + 1];
        dp[0] = dp[1] = 1;

        for (int i = 2; i <= arr.length; i++) {

            //-1이라 헷갈릴 수 있는데 해당 자리를 보는 것이다.
            if (arr[i - 1] != '0') {
                dp[i] = dp[i - 1];
            }

            //이것도 해당 자리와 이전 자리의 조합이다.
            int num = (arr[i - 2] - '0') * 10 + (arr[i - 1] - '0');

            if (10 <= num && num <= 26) {
                dp[i] += dp[i - 2];
            }

            dp[i] %= 1_000_000;
        }

        System.out.println(dp[arr.length]);

    }
}
