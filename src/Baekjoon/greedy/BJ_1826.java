package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1826">백준 1826번 - 그리디 : 연료 채우기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1826%EB%B2%88-%EC%97%B0%EB%A3%8C-%EC%B1%84%EC%9A%B0%EA%B8%B0">velog</a>
 * @since 2024-11-20
 */
public class BJ_1826 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        st = new StringTokenizer(br.readLine());

        int L = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> qu = new PriorityQueue<>(Collections.reverseOrder());

        int idx = 0;
        int count = 0;

        while (P < L) {

            while (idx < n && arr[idx][0] <= P) {
                qu.offer(arr[idx][1]);
                idx++;
            }

            //도착하기 전에 이동할 수 있는 주유소가 없다면 불가능
            if (qu.isEmpty()) {
                System.out.println(-1);
                return;
            }

            P += qu.poll();
            count++;
        }

        System.out.println(count);

    }
}