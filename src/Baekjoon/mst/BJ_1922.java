package Baekjoon.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1922">백준 1922번 - MST : 네트워크 연결</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1922%EB%B2%88-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC-%EC%97%B0%EA%B2%B0">velog</a>
 *
 * @since 2024-10-29
 */
public class BJ_1922 {

    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        PriorityQueue<Edge> qu = new PriorityQueue<>();

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            qu.offer(new Edge(a, b, c));
            qu.offer(new Edge(b, a, c));
        }

        int edge = 0;
        int cost = 0;

        while (edge < n - 1) {
            Edge e = qu.poll();

            if (find(e.from) != find(e.to)) {
                union(e.from, e.to);
                cost += e.weight;
                edge++;
            }
        }

        System.out.print(cost);
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
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}
