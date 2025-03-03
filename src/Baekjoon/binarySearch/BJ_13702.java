package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/13702">백준 13702번 - 이분 탐색 : 이상한 술집</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-13702%EB%B2%88-%EC%9D%B4%EC%83%81%ED%95%9C-%EC%88%A0%EC%A7%91">velog</a>
 * @since 2025-02-21
 */
public class BJ_13702 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[] arr = new long[n];
        long left = 1;
        long right = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
            right = Math.max(right, arr[i]);
        }

        long ans = 0;

        while (left <= right) {
            long mid = (left + right) / 2;
            long count = 0;

            for (long a : arr) {
                count += (a / mid);
            }

            if (count >= k) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(ans);
    }
}
