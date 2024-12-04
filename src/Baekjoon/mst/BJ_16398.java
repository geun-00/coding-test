package Baekjoon.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/16398">백준 16398번 - MST : 행성 연결</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-16398%EB%B2%88-%ED%96%89%EC%84%B1-%EC%97%B0%EA%B2%B0">velog</a>
 *
 * @since 2024-12-04
 */
public class BJ_16398 {

    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        parent = new int[n];

        PriorityQueue<Edge> qu = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            parent[i] = i;

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {

                int cost = Integer.parseInt(st.nextToken());
                if (i < j) {
                    qu.offer(new Edge(i, j, cost));
                }
            }
        }

        long ans = 0;

        while (!qu.isEmpty()) {

            Edge e = qu.poll();

            if (find(e.from) != find(e.to)) {
                union(e.from, e.to);
                ans += e.cost;
            }
        }

        System.out.println(ans);
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

        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}