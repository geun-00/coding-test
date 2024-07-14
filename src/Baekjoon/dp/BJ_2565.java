package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2565">백준 2565번 - DP : 전깃줄</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2565%EB%B2%88-%EC%A0%84%EA%B9%83%EC%A4%84">velog</a>
 *
 * @since 2024-07-12
 */
public class BJ_2565 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); //전체 전깃줄의 개수

        Line[] lines = new Line[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            lines[i] = new Line(a, b);
        }

        Arrays.sort(lines);

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int max = 1;

        //가장 긴 증가하는 부분 수열의 길이 구하기
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (lines[i].b > lines[j].b && dp[i] + 1 == dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
            max = Math.max(max, dp[i]);
        }

        System.out.println(n - max);
    }

    static class Line implements Comparable<Line> {
        int a, b;

        public Line(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Line o) {
            if (this.a == o.a) {
                return this.b - o.b;
            }
            return this.a - o.a;
        }
    }
}
