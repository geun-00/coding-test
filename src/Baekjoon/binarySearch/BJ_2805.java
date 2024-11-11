package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2805">백준 2805번 - 이분 탐색 : 나무 자르기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2805%EB%B2%88-%EB%82%98%EB%AC%B4-%EC%9E%90%EB%A5%B4%EA%B8%B0">velog</a>
 * @since 2024-11-09
 */
public class BJ_2805 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long m = Long.parseLong(st.nextToken());

        long[] arr = new long[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        long left = 0, right = 1_000_000_000;
        long ans = 0;

        while (left <= right) {

            long mid = (left + right) / 2;

            long sum = 0;

            for (long h : arr) {
                long diff = mid - h;

                //자르고 남는 게 있을 때만
                if (diff < 0) {
                    sum -= diff;
                }
            }

            if (sum >= m) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(ans);
    }
}