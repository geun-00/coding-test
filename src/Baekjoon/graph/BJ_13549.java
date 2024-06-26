package Baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/13549">백준 13549번 - 그래프 탐색 : 숨바꼭질 3</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-13549%EB%B2%88-%EC%88%A8%EB%B0%94%EA%BC%AD%EC%A7%88-3">velog</a>
 * @since 2024-06-26
 */
public class BJ_13549 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] time = new int[100_001];
        Arrays.fill(time, Integer.MAX_VALUE);
        time[n] = 0;

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(n);

        while (!qu.isEmpty()) {
            int now = qu.poll();

            if (now == k) {
                System.out.println(time[now]);
                return;
            }

            if (now * 2 <= 100_000 && time[now * 2] > time[now]) {
                time[now * 2] = time[now];
                qu.offer(now * 2);
            }

            if (now + 1 <= 100_000 && time[now + 1] > time[now] + 1) {
                time[now + 1] = time[now] + 1;
                qu.offer(now + 1);
            }

            if (now - 1 >= 0 && time[now - 1] > time[now] + 1) {
                time[now - 1] = time[now] + 1;
                qu.offer(now - 1);
            }
        }
    }
}
