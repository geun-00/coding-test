package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2792">백준 2792번 - 이분 탐색 : 보석 상자</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2792%EB%B2%88-%EB%B3%B4%EC%84%9D-%EC%83%81%EC%9E%90">velog</a>
 * @since 2025-01-11
 */
public class BJ_2792 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[m];

        int low = 0, high = 0;

        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            high = Math.max(high, arr[i]);
        }

        int ans = 0;

        while (low <= high) {

            int mid = (low + high) / 2;

            int count = 0;
            for (int i : arr) {
                count += (int) Math.ceil((double) i / mid);
            }

            if (count <= n) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        System.out.println(ans);
    }
}