package Baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/16928">백준 16928번 - 그래프 탐색 : 뱀과 사다리 게임</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-16928%EB%B2%88-%EB%B1%80%EA%B3%BC-%EC%82%AC%EB%8B%A4%EB%A6%AC-%EA%B2%8C%EC%9E%84">velog</a>
 * @since 2024-08-13
 */
public class BJ_16928 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashMap<Integer, Integer> ladder = new HashMap<>();
        HashMap<Integer, Integer> snake = new HashMap<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            ladder.put(x, y);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            snake.put(u, v);
        }

        Queue<Player> qu = new ArrayDeque<>();

        boolean[] visit = new boolean[101];
        qu.offer(new Player(1, 0));

        while (!qu.isEmpty()) {

            Player player = qu.poll();

            if (player.num == 100) {
                System.out.println(player.count);
                return;
            }

            for (int i = 1; i <= 6; i++) {

                int next = player.num + i;

                if (next > 100 || visit[next]) {
                    continue;
                }

                if (ladder.containsKey(next)) {
                    qu.offer(new Player(ladder.get(next), player.count + 1));
                } else {
                    qu.offer(new Player(snake.getOrDefault(next, next), player.count + 1));
                }
                visit[next] = true;
            }
        }
    }

    static class Player {
        int num, count;

        public Player(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }
}