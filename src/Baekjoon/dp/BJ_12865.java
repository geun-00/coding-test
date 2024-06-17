package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/12865">백준 12865번 - DP : 평범한 배낭</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-12865%EB%B2%88-%ED%8F%89%EB%B2%94%ED%95%9C-%EB%B0%B0%EB%82%AD">velog</a>
 */
public class BJ_12865 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] dp = new int[k + 1];
        int[] w = new int[n + 1]; //각 물건 무게
        int[] v = new int[n + 1]; //각 물건 가치

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            for (int j = k; j - w[i] >= 0; j--) {
                dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
            }
        }

        System.out.println(dp[k]);
    }
}
