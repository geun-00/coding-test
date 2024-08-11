package Programmers.level3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/132266">프로그래머스 - Lv.3 : 부대복귀</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%B6%80%EB%8C%80%EB%B3%B5%EA%B7%80">velog</a>
 * @since 2024-08-10
 */
public class ReturnArmy {

    static int[] dist;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) {

        System.out.println(Arrays.toString(solution(
                3,
                new int[][]{{1, 2}, {2, 3}},
                new int[]{2, 3},
                1
        )));

        System.out.println(Arrays.toString(solution(
                5,
                new int[][]{{1, 2}, {1, 4}, {2, 4}, {2, 5}, {4, 5}},
                new int[]{1, 3, 5},
                5
        )));
    }

    private static int[] solution(int n, int[][] roads, int[] sources, int destination) {

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList();
        }

        for (int[] road : roads) {
            int a = road[0];
            int b = road[1];

            graph[a].add(b);
            graph[b].add(a);
        }

        dist = new int[n + 1];
        Arrays.fill(dist , -1);

        bfs(destination);

        int[] result = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            result[i] = dist[sources[i]];
        }

        return result;
    }

    private static void bfs(int start) {

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(start);
        dist[start] = 0;

        while (!qu.isEmpty()) {
            int now = qu.poll();

            for (int next : graph[now]) {
                if (dist[next] == -1) {
                    dist[next] = dist[now] + 1;
                    qu.offer(next);
                }
            }
        }
    }
}
