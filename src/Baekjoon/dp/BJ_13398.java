package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/13398">백준 13398번 - DP : 연속합 2</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-13398%EB%B2%88-%EC%97%B0%EC%86%8D%ED%95%A9-2">velog</a>
 * @since 2024-10-15
 */
public class BJ_13398 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp1 = new int[n]; //수를 제거하지 않았을 때 i번째 최대 부분합
        int[] dp2 = new int[n]; //수를 제거했을 때 i번째 최대 부분합

        dp1[0] = arr[0];

        //수는 한 개 이상 선택해야 하므로 만약 n=1이면 하나의 수가 그대로 정답이다.
        int result = arr[0];

        for (int i = 1; i < n; i++) {
            dp1[i] = Math.max(dp1[i - 1] + arr[i], arr[i]);
            dp2[i] = Math.max(dp2[i - 1] + arr[i], dp1[i - 1]);
            result = Math.max(result, Math.max(dp1[i], dp2[i]));
        }

        System.out.println(result);
    }
}
