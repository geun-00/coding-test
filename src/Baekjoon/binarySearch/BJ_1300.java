package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href = "https://www.acmicpc.net/problem/1300">백준 1300번 - 이분 탐색 : K번째 수</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1300%EB%B2%88-K%EB%B2%88%EC%A7%B8-%EC%88%98">velog</a>
 * @since 2024-08-09
 */
public class BJ_1300 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        int s = 1;
        int e = k;
        int result = 0;

        while (s <= e) {

            int mid = (s + e) / 2;

            int sum = 0; //배열 A에서 mid 이하의 개수
            for (int i = 1; i <= n; i++) {
                sum += Math.min(mid / i, n); //각 행에서 n개를 넘을 수 없음
            }

            if (sum < k) {
                s = mid + 1;
            } else {
                result = mid;
                e = mid - 1;
            }
        }

        System.out.println(result);
    }
}
