package Baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/12869">백준 12869번 - 그래프 탐색 : 뮤탈리스크</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-12869%EB%B2%88-%EB%AE%A4%ED%83%88%EB%A6%AC%EC%8A%A4%ED%81%AC">velog</a>
 * @since 2025-03-06
 */
public class BJ_12869 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] scv = new int[3];
        boolean[][][] visit = new boolean[61][61][61];

        for (int i = 0; i < n; i++) {
            scv[i] = Integer.parseInt(st.nextToken());
        }

        visit[scv[0]][scv[1]][scv[2]] = true;

        Queue<State> qu = new ArrayDeque<>();
        qu.offer(new State(scv, 0));

        int[][] damage = {
            {9, 3, 1}, {9, 1, 3},
            {3, 9, 1}, {3, 1, 9},
            {1, 9, 3}, {1, 3, 9}
        };

        while (!qu.isEmpty()) {
            State state = qu.poll();

            int[] arr = state.arr;
            if (arr[0] <= 0 && arr[1] <= 0 && arr[2] <= 0) {
                System.out.println(state.count);
                return;
            }

            for (int[] d : damage) {
                int na = Math.max(0, arr[0] - d[0]);
                int nb = Math.max(0, arr[1] - d[1]);
                int nc = Math.max(0, arr[2] - d[2]);

                if (!visit[na][nb][nc]) {
                    visit[na][nb][nc] = true;
                    qu.offer(new State(new int[]{na, nb, nc}, state.count + 1));
                }
            }
        }
    }

    static class State {
        int[] arr;
        int count;

        public State(int[] arr, int count) {
            this.arr = arr;
            this.count = count;
        }
    }
}
