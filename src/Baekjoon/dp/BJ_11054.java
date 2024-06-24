package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/11054">백준 11054번 - DP : 가장 긴 바이토닉 부분 수열</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-11054%EB%B2%88-%EA%B0%80%EC%9E%A5-%EA%B8%B4-%EB%B0%94%EC%9D%B4%ED%86%A0%EB%8B%89-%EB%B6%80%EB%B6%84-%EC%88%98%EC%97%B4">velog</a>
 * @since 2024-06-24
 */
public class BJ_11054 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n]; //수열
        int[] dp = new int[n];  //정방향 바이토닉 수열
        int[] dp_r = new int[n];//역방향 바이토닉 수열

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
            dp_r[i] = 1;
        }

        //정방향
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (arr[i] > arr[j] && dp[i] + 1 == dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
        }

        //역방향
        for (int i = n - 2; i >= 0; i--) {
            for (int j = n - 1; j >= i; j--) {
                if (arr[i] > arr[j] && dp_r[i] + 1 == dp_r[j] + 1) {
                    dp_r[i] = dp_r[j] + 1;
                }
            }
        }

        int max = 0;

        for (int i = 0; i < n; i++) {
            max = Math.max(max, dp[i] + dp_r[i]);
        }

        System.out.println(max - 1);
    }
}
