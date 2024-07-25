package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/14002">백준 14002번 - DP : 가장 긴 증가하는 부분 수열 4</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-14002%EB%B2%88-%EA%B0%80%EC%9E%A5-%EA%B8%B4-%EC%A6%9D%EA%B0%80%ED%95%98%EB%8A%94-%EB%B6%80%EB%B6%84-%EC%88%98%EC%97%B4-4">velog</a>
 *
 * @since 2024-07-24
 */
public class BJ_14002 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        int[] dp = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
        }

        int max = 1; //LIS 길이
        int maxIdx = 0; //LIS 마지막 인덱스

        for (int i = 1; i < n; i++) {
            //LIS 계산
            for (int j = 0; j <= i; j++) {
                if (arr[i] > arr[j] && dp[i] + 1 == dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }

            if (max < dp[i]) {
                max = dp[i];
                maxIdx = i; //LIS 마지막 인덱스 갱신
            }
        }

        result.append(max).append("\n");

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = maxIdx; i >= 0; i--) {
            if (dp[i] == max) {
                max--;
                stack.push(arr[i]);
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop()).append(" ");
        }

        System.out.print(result);
    }
}