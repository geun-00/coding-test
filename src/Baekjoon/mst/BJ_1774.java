package Baekjoon.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1774">백준 1774번 - MST : 우주신과의 교감</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1774%EB%B2%88-%EC%9A%B0%EC%A3%BC%EC%8B%A0%EA%B3%BC%EC%9D%98-%EA%B5%90%EA%B0%90">velog</a>
 * @since 2024-11-23
 */
public class BJ_1774 {

    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n];

        double[][] arr = new double[n][2];

        for (int i = 0; i < n; i++) {

            parent[i] = i;

            st = new StringTokenizer(br.readLine());

            arr[i][0] = Double.parseDouble(st.nextToken());
            arr[i][1] = Double.parseDouble(st.nextToken());
        }

        PriorityQueue<Edge> qu = new PriorityQueue<>();

        for (int i = 0; i < n - 1; i++) {

            double x1 = arr[i][0];
            double y1 = arr[i][1];

            for (int j = i + 1; j < n; j++) {

                double x2 = arr[j][0];
                double y2 = arr[j][1];

                double dist = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

                qu.offer(new Edge(i, j, dist));
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            union(a, b);
        }

        double ans = 0;

        while (!qu.isEmpty()) {

            Edge e = qu.poll();

            if (find(e.from) != find(e.to)) {
                union(e.from, e.to);
                ans += e.w;
            }
        }

        System.out.printf("%.2f", ans);
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

        int from, to;
        double w;

        public Edge(int from, int to, double w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.w, o.w);
        }
    }
}