package Programmers.level3;

import java.util.PriorityQueue;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/42861">프로그래머스 - Lv.3 : 섬 연결하기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%84%AC-%EC%97%B0%EA%B2%B0%ED%95%98%EA%B8%B0">velog</a>
 * @since 2024-08-02
 */
public class ConnectIsland {

    static int[] parent;

    public static void main(String[] args) {
        System.out.println(solution(4, new int[][]
                {
                        {0, 1, 1},
                        {0, 2, 2},
                        {1, 2, 5},
                        {1, 3, 1},
                        {2, 3, 8}
                }));
    }

    private static int solution(int n, int[][] costs) {

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        PriorityQueue<Edge> edges = new PriorityQueue<>();

        for (int[] cost : costs) {
            int s = cost[0];
            int e = cost[1];
            int w = cost[2];

            edges.offer(new Edge(s, e, w));
        }

        int used = 0;
        int result = 0;

        while (used < n - 1) {
            Edge edge = edges.poll();
            int s = edge.s;
            int e = edge.e;
            int w = edge.w;

            if (find(s) != find(e)) {
                union(s, e);
                result += w;
                used++;
            }
        }

        return result;
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    static class Edge implements Comparable<Edge> {
        int s, e, w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }
}