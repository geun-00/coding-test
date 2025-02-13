package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/17266">백준 17266번 - 이분 탐색 : 어두운 굴다리</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-17266%EB%B2%88-%EC%96%B4%EB%91%90%EC%9A%B4-%EA%B5%B4%EB%8B%A4%EB%A6%AC">velog</a>
 * @since 2025-02-02
 */
public class BJ_17266 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[] arr = new int[m];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 1;
        int right = n;
        int ans = 0;

        while (left <= right) {

            int mid = (left + right) / 2;

            int light = 0; //마지막 불빛 위치

            for (int pos : arr) {

                //왼쪽으로 비추는 범위가 마지막 불빛보다 크면 사이에 공백이 생긴다.
                if (light < pos - mid) {
                    break;
                }

                light = pos + mid;
            }

            if (light >= n) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(ans);
    }
}
