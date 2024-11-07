package Baekjoon.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/4386">백준 4386번 - MST : 별자리 만들기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-4386%EB%B2%88-%EB%B3%84%EC%9E%90%EB%A6%AC-%EB%A7%8C%EB%93%A4%EA%B8%B0">velog</a>
 *
 * @since 2024-11-06
 */
public class BJ_4386 {

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

    static class Star {
        double x, y;

        public Star(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        parent = new int[n + 1];
        Star[] stars = new Star[n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            stars[i] = new Star(x, y);
            parent[i] = i;
        }

        PriorityQueue<Edge> qu = new PriorityQueue<>();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {

                    double x1 = stars[i].x, y1 = stars[i].y;
                    double x2 = stars[j].x, y2 = stars[j].y;

                    double dist = Math.sqrt(
                            ((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1))
                    );

                    qu.offer(new Edge(i, j, dist));
                }
            }
        }

        double ans = 0;
        int used = 0;

        while (!qu.isEmpty()) {
            Edge e = qu.poll();

            if (find(e.from) != find(e.to)) {
                union(e.from, e.to);
                used++;
                ans += e.w;

                if (used == n - 1) {
                    break;
                }
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
}
