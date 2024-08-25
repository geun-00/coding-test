package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1781">백준 1781번 - 그리디 : 컵라면</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1781%EB%B2%88-%EC%BB%B5%EB%9D%BC%EB%A9%B4">velog</a>
 *
 * @since 2024-08-20
 */
public class BJ_1781 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][2];

        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[i][0] = a;
            arr[i][1] = b;
        }

        Arrays.sort(arr, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            }
            return o1[0] - o2[0];
        });

        PriorityQueue<Integer> qu = new PriorityQueue<>();

        for (int[] a : arr) {

            int deadline = a[0];
            int score = a[1];

            qu.offer(score);

            if (qu.size() > deadline) {
                qu.poll();
            }
        }

        int sum = 0;
        while (!qu.isEmpty()) {
            sum += qu.poll();
        }

        System.out.println(sum);
    }
}