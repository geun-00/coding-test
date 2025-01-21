package Baekjoon.twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/14921">백준 14921번 - 투 포인터 : 용액 합성하기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-14921%EB%B2%88-%EC%9A%A9%EC%95%A1-%ED%95%A9%EC%84%B1%ED%95%98%EA%B8%B0">velog</a>
 * @since 2025-01-18
 */
public class BJ_14921 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = Integer.MAX_VALUE;

        int left = 0;
        int right = n - 1;

        while (left < right) {

            int sum = arr[right] + arr[left];

            if (Math.abs(sum) < Math.abs(ans)) ans = sum;

            if (sum == 0) break;

            if (sum < 0) left++;
            else right--;
        }

        System.out.println(ans);
    }
}