package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/11497">백준 11497번 - 그리디 : 통나무 건너뛰기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-11497%EB%B2%88-%ED%86%B5%EB%82%98%EB%AC%B4-%EA%B1%B4%EB%84%88%EB%9B%B0%EA%B8%B0">velog</a>
 * @since 2025-01-25
 */
public class BJ_11497 {
    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            int[] arr = new int[n];
            int[] temp = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);

            int left = 0, right = n - 1;

            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) {
                    temp[left++] = arr[i];
                } else {
                    temp[right--] = arr[i];
                }
            }

            int ans = Math.abs(temp[n - 1] - temp[0]);

            for (int i = 0; i < n - 1; i++) {
                ans = Math.max(ans, Math.abs(temp[i + 1] - temp[i]));
            }

            sb.append(ans).append("\n");
        }

        System.out.print(sb);
    }
}
