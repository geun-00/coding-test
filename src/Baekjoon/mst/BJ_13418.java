package Baekjoon.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/13418">백준 13418번 - MST : 학교 탐방하기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-13418%EB%B2%88-%ED%95%99%EA%B5%90-%ED%83%90%EB%B0%A9%ED%95%98%EA%B8%B0">velog</a>
 * @since 2025-01-20
 */
public class BJ_13418 {

    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        st.nextToken();
        st.nextToken();
        int start = 1 - Integer.parseInt(st.nextToken());

        PriorityQueue<Edge> minEdges = new PriorityQueue<>((o1, o2) -> o2.w - o1.w);
        PriorityQueue<Edge> maxEdges = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            minEdges.offer(new Edge(a, b, c));
            maxEdges.offer(new Edge(a, b, c));
        }

        int min = kruskal(n, start, minEdges);
        int max = kruskal(n, start, maxEdges);

        System.out.println(max - min);
    }

    private static int kruskal(int n, int start, PriorityQueue<Edge> edges) {

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int count = start;

        while (!edges.isEmpty()) {
            Edge e = edges.poll();

            if (find(e.from) != find(e.to)) {
                union(e.from, e.to);
                if (e.w == 0) {
                    count++;
                }
            }
        }

        return count * count;
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    private static int find(int a) {
        if (a == parent[a]) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    static class Edge {
        int from, to, w;

        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }
    }
}
