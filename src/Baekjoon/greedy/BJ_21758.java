package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/21758">백준 21758번 - 그리디 : 꿀 따기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-21758%EB%B2%88-%EA%BF%80-%EB%94%B0%EA%B8%B0">velog</a>
 * @since 2024-10-08
 */
public class BJ_21758 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n + 1];
        long[] sum = new long[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

            sum[i] = arr[i] + sum[i - 1]; //누적 합
        }

        long max = Long.MIN_VALUE;

        //벌통이 가운데에 있고, 벌이 양 끝에 있는 경우
        //i = 벌통의 위치
        for (int i = 2; i < n; i++) {
            long temp = (sum[i] - sum[1]) + (sum[n - 1] - sum[i - 1]);
            max = Math.max(max, temp);
        }

        //벌통이 첫번째에 있는 경우(왼쪽 끝)
        for (int i = 2; i < n; i++) {
            long temp = sum[i - 1] + (sum[n - 1] - arr[i]);
            max = Math.max(max, temp);
        }

        //벌통이 마지막에 있는 경우(오른쪽 끝)
        for (int i = 2; i < n; i++) {
            long temp = (sum[n] - sum[i]) + (sum[n] - sum[1] - arr[i]);
            max = Math.max(max, temp);
        }

        System.out.println(max);
    }
}