package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1263">백준 1263번 - 그리디 : 시간 관리</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1263%EB%B2%88-%EC%8B%9C%EA%B0%84-%EA%B4%80%EB%A6%AC">velog</a>
 * @since 2025-02-16
 */
public class BJ_1263 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] work = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            work[i][0] = Integer.parseInt(st.nextToken());
            work[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(work, (a, b) -> b[1] - a[1]);

        int start = work[0][1];

        for (int[] arr : work) {
            start = Math.min(start, arr[1]) - arr[0];
        }

        System.out.println(Math.max(-1, start));
    }
}
