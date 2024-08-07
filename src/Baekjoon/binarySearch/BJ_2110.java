package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2110">백준 2110번 - 이분 탐색 : 공유기 설치</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2110%EB%B2%88-%EA%B3%B5%EC%9C%A0%EA%B8%B0-%EC%84%A4%EC%B9%98">velog</a>
 * @since 2024-08-05
 */
public class BJ_2110 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int s = 1;
        int e = arr[n - 1] - arr[0];
        int result = 0;

        while (s <= e) {
            int mid = (s + e) / 2;

            int count = 1; //첫번째 집에 공유기 설치
            int last = arr[0];

            for (int i = 1; i < arr.length; i++) {
                int next = arr[i];

                if (mid <= next - last) {
                    last = next;
                    count++;
                }
            }

            if (count >= c) {
                result = mid;
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        System.out.println(result);


/*
        //브루트포스
        int result = 0;
        for (int i = 1; i <= max; i++) {

            int count = 1;
            int last = arr[0];

            for (int j = 1; j < arr.length; j++) {
                int next = arr[j];

                if (i <= next - last) {
                    last = next;
                    count++;
                }
            }

            if (count == c) {
                result = i;
            }
        }
        System.out.println(result);
*/
    }
}
