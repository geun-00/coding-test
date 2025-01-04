package Baekjoon.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/10423">백준 10423번 - MST : 전기가 부족해</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-10423%EB%B2%88-%EC%A0%84%EA%B8%B0%EA%B0%80-%EB%B6%80%EC%A1%B1%ED%95%B4">velog</a>
 * @since 2025-01-03
 */
public class BJ_10423 {

    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int num = Integer.parseInt(st.nextToken()) - 1;
            parent[num] = -1;
        }

        PriorityQueue<Edge> qu = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            qu.offer(new Edge(u, v, w));
        }

        int ans = 0;

        while (!qu.isEmpty()) {
            Edge e = qu.poll();

            if (find(e.from) != find(e.to)) {
                union(e.from, e.to);
                ans += e.cost;
            }
        }

        System.out.println(ans);
    }

    public static int find(int a) {

        if (parent[a] == -1) return -1;
        if (parent[a] == a) return a;

        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b) {

        a = find(a);
        b = find(b);

        if (a != b) {
            if (a == -1) parent[b] = -1;
            else if (b == -1) parent[a] = -1;
            else parent[b] = a;
        }
    }

    static class Edge {

        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}