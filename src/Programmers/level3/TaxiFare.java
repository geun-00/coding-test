package Programmers.level3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/72413">프로그래머스 - Lv.3 : 합승 택시 요금</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%ED%95%A9%EC%8A%B9-%ED%83%9D%EC%8B%9C-%EC%9A%94%EA%B8%88">velog</a>
 * @since 2024-08-14
 */
public class TaxiFare {

    static  ArrayList<Node>[] graph;

    public static void main(String[] args) {

        System.out.println(solution(6, 4, 6, 2, new int[][]{
                {4, 1, 10},
                {3, 5, 24},
                {5, 6, 2},
                {3, 1, 41},
                {5, 1, 24},
                {4, 6, 50},
                {2, 4, 66},
                {2, 3, 22},
                {1, 6, 25},
        }));
        System.out.println(solution(7, 3, 4, 1, new int[][]{
                {5, 7, 9},
                {4, 6, 4},
                {3, 6, 1},
                {3, 2, 3},
                {2, 1, 6},
        }));
        System.out.println(solution(6, 4, 5, 6, new int[][]{
                {2, 6, 6},
                {6, 3, 7},
                {4, 6, 7},
                {6, 5, 11},
                {2, 5, 12},
                {5, 3, 20},
                {2, 4, 8},
                {4, 3, 9},
        }));
    }

    private static int solution(int n, int s, int a, int b, int[][] fares) {

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] fare : fares) {

            int c = fare[0];
            int d = fare[1];
            int f = fare[2];

            graph[c].add(new Node(d, f));
            graph[d].add(new Node(c, f));
        }

        int[] fromS = dijkstra(s, n);
        int[] fromA = dijkstra(a, n);
        int[] fromB = dijkstra(b, n);

        int min = fromS[a] + fromS[b];

        for (int i = 1; i <= n; i++) {
            min = Math.min(min, fromS[i] + fromA[i] + fromB[i]);
        }

        return min;
    }

    static int[] dijkstra(int start, int n) {

        PriorityQueue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(start, 0));

        boolean[] visit = new boolean[n + 1];
        int[] dist = new int[n + 1];

        Arrays.fill(dist, 201 * 100_000);
        dist[start] = 0;

        while (!qu.isEmpty()) {

            Node now = qu.poll();

            if (visit[now.to]) {
                continue;
            }
            visit[now.to] = true;

            for (Node next : graph[now.to]) {
                if (dist[next.to] > dist[now.to] + next.weight) {
                    dist[next.to] = dist[now.to] + next.weight;

                    qu.offer(new Node(next.to, dist[next.to]));
                }
            }
        }

        return dist;
    }

    static class Node implements Comparable<Node>{

        int to, weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}
