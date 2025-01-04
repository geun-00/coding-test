package Baekjoon.bitmask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/16938">백준 16938번 - 비트 마스킹 : 캠프 준비</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-16938%EB%B2%88-%EC%BA%A0%ED%94%84-%EC%A4%80%EB%B9%84">velog</a>
 * @since 2024-12-28
 */
public class BJ_16938 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        for (int i = 0; i < (1 << n); i++) {
            if (Integer.bitCount(i) >= 2 && check(i, arr, l, r, x)) {
                ans++;
            }
        }

        System.out.println(ans);
    }

    private static boolean check(int pick, int[] arr, int l, int r, int x) {

        int sum = 0;
        int min = Integer.MAX_VALUE;
        int max = 0;

        for (int i = 0; i < arr.length; i++) {
            if ((pick & (1 << i)) != 0) {
                sum += arr[i];
                min = Math.min(min, arr[i]);
                max = Math.max(max, arr[i]);
            }
        }

        int diff = max - min;

        return l <= sum && sum <= r && diff >= x;
    }
}
