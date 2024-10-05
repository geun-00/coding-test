package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/8983">백준 8983번 - 이분 탐색 : 사냥꾼</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-8983%EB%B2%88-%EC%82%AC%EB%83%A5%EA%BE%BC">velog</a>
 * @since 2024-10-03
 */
public class BJ_8983 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int[] arr = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int count = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            //이분 탐색 수행
            int left = 0;
            int right = m - 1;

            while (left <= right) {

                int mid = (left + right) / 2;

                int dist = Math.abs(arr[mid] - x) + y;

                if (dist <= l) {
                    count++;
                    break;
                }

                //사대의 y 좌표는 항상 고정이기 때문에 x 좌표만 비교하여 범위를 좁힌다.
                if (arr[mid] < x) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        System.out.println(count);

    }
}
