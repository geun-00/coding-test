package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1461">백준 1461번 - 그리디 : 도서관</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1461%EB%B2%88-%EB%8F%84%EC%84%9C%EA%B4%80">velog</a>
 * @since 2024-08-10
 */
public class BJ_1461 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int sum = 0;
        int max = Math.max(Math.abs(arr[0]), Math.abs(arr[n - 1])); //0에서 가장 먼 거리의 위치

        //음수 위치
        for (int i = 0; i < n; i += m) {
            if (arr[i] < 0) {
                sum += Math.abs(arr[i] * 2);
            } else {
                break;
            }
        }

        //양수 위치
        for (int i = n - 1; i >= 0; i -= m) {
            if (arr[i] > 0) {
                sum += Math.abs(arr[i] * 2);
            } else {
                break;
            }
        }

        System.out.println(sum - max);
    }
}
