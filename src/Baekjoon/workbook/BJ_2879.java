package Baekjoon.workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2879">백준 2879번 - 코딩은 예쁘게</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2879%EB%B2%88-%EC%BD%94%EB%94%A9%EC%9D%80-%EC%98%88%EC%81%98%EA%B2%8C">velog</a>
 * @since 2025-04-17
 */
public class BJ_2879 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] current = new int[n];
        int[] right = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) current[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) right[i] = Integer.parseInt(st.nextToken());

        int ans = 0;
        int prev = 0;

        for (int i = 0; i < n; i++) {
            int diff = right[i] - current[i];

            // 1
            if (prev * diff < 0) {
                ans += Math.abs(prev);
            }
            // 2
            else if (Math.abs(prev) >= Math.abs(diff)) {
                ans += Math.abs(prev) - Math.abs(diff);
            }

            prev = diff;
        }

        ans += Math.abs(prev);
        System.out.println(ans);
    }
}
