package Baekjoon.twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1806">백준 1806번 - 투 포인터 : 부분합</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1806%EB%B2%88-%EB%B6%80%EB%B6%84%ED%95%A9">velog</a>
 * @since 2024-10-29
 */
public class BJ_1806 {

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        long[] arr = new long[n + 1];
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
        }

        int left = 1, right = 1;

        int res = INF;

        while (right <= n) {

            //left ~ right 범위 부분합
            long sum = arr[right] - arr[left - 1];

            if (sum >= s) {
                int len = right - left + 1;
                res = Math.min(res, len);
                left++;
            } else {
                right++;
            }
        }

        System.out.println(res != INF ? res : 0);
    }
}
