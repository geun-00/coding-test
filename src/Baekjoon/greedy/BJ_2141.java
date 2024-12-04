package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2141">백준 2141번 - 그리디 : 우체국</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2141%EB%B2%88-%EC%9A%B0%EC%B2%B4%EA%B5%AD">velog</a>
 * @since 2024-11-25
 */
public class BJ_2141 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][2];

        long sum = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());

            sum += a;

            arr[i][0] = x;
            arr[i][1] = a;
        }

        long mid = (sum + 1) / 2;   //홀수 고려 +1
        long count = 0;

        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        for (int i = 0; i < n; i++) {

            count += arr[i][1];

            if (count >= mid) {
                System.out.println(arr[i][0]);
                return;
            }
        }
    }
}