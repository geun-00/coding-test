package Baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1697">백준 1697번 - 그래프 탐색 : 숨바꼭질</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1697%EB%B2%88-%EC%88%A8%EB%B0%94%EA%BC%AD%EC%A7%88">velog</a>
 * @since 2024-11-09
 */
public class BJ_1697 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        if (n == k) {
            System.out.println(0);
            return;
        }

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(n);

        boolean[] visit = new boolean[100_001];
        visit[n] = true;

        int count = 0;

        while (true) {

            count++;

            int size = qu.size();

            for (int i = 0; i < size; i++) {

                int now = qu.poll();

                if (now + 1 == k || now - 1 == k || now * 2 == k) {
                    System.out.println(count);
                    return;
                }

                if (now - 1 >= 0 && !visit[now - 1]) {
                    visit[now - 1] = true;
                    qu.offer(now - 1);
                }

                if (now + 1 <= 100_000 && !visit[now + 1]) {
                    visit[now + 1] = true;
                    qu.offer(now + 1);
                }

                if (now * 2 <= 100_000 && !visit[now * 2]) {
                    visit[now * 2] = true;
                    qu.offer(now * 2);
                }
            }

        }
    }
}
