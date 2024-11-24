package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/13397">백준 13397번 - 이분 탐색 : 구간 나누기 2</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-13397%EB%B2%88-%EA%B5%AC%EC%97%AD-%EB%82%98%EB%88%84%EA%B8%B0-2">velog</a>
 * @since 2024-11-21
 */
public class BJ_13397 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = 10000;
        int ans = 0;

        while (left <= right) {

            int mid = (left + right) / 2;

            int count = 1;
            int min = arr[0];
            int max = arr[0];

            for (int i = 1; i < n; i++) {

                min = Math.min(min, arr[i]);
                max = Math.max(max, arr[i]);

                //새로운 구간
                if (max - min > mid) {
                    count++;
                    min = arr[i];
                    max = arr[i];
                }
            }

            if (count <= m) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(ans);
    }
}