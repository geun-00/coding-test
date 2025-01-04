package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * <a href = "https://www.acmicpc.net/problem/3687">백준 3687번 - 그리디 : 성냥개비</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-3687%EB%B2%88-%EC%84%B1%EB%83%A5%EA%B0%9C%EB%B9%84">velog</a>
 *
 * @since 2024-12-20
 *
 * 2개 - 1
 * 3개 - 7
 * 4개 - 4
 * 5개 - 2, 3, 5
 * 6개 - 6, 9, 0
 * 7개 - 8
 */
public class BJ_3687 {
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

        for (int i = 8; i <= 100; i++) {    //성냥개비 개수
            for (int j = 2; j <= 7; j++) {  //숫자를 표현하는 데 성냥개비 최소 2개 ~ 최대 7개 필요

                String original = dp[i];
                String newNum = dp[i - j] + arr[j];

                //자릿수가 애초에 작은 경우
                if (original.length() < newNum.length()) continue;

                //자릿수가 적거나, 자릿수는 같고 비교했을 때 더 큰 경우
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