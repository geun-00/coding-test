package Baekjoon.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2887">백준 2887번 - MST : 행성 터널</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2887%EB%B2%88-%ED%96%89%EC%84%B1-%ED%84%B0%EB%84%90">velog</a>
 *
 * @since 2024-11-11
 */
public class BJ_2887 {

    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        parent = new int[n];
        Planet[] planets = new Planet[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            planets[i] = new Planet(i, x, y, z);
            parent[i] = i;
        }

        PriorityQueue<Edge> qu = new PriorityQueue<>();

        for (int i = 0; i < 3; i++) {

            final int t = i;

            Arrays.sort(planets, (a, b) -> {
                if (t == 0) {
                    return a.x - b.x;
                }
                else if (t == 1) {
                    return a.y - b.y;
                }
                else {
                    return a.z - b.z;
                }
            });

            for (int j = 0; j < n - 1; j++) {
                Planet p1 = planets[j];
                Planet p2 = planets[j + 1];

                int cost;

                if (i == 0) {
                    cost = Math.abs(p1.x - p2.x);
                } else if (i == 1) {
                    cost = Math.abs(p1.y - p2.y);
                } else {
                    cost = Math.abs(p1.z - p2.z);
                }

                qu.offer(new Edge(p1.id, p2.id, cost));
            }
        }

        int used = 0;
        int cost = 0;

        while (!qu.isEmpty()) {

            Edge e = qu.poll();

            if (find(e.from) != find(e.to)) {
                union(e.from, e.to);
                cost += e.w;
                used++;

                if (used == n - 1) {
                    break;
                }
            }
        }

        System.out.println(cost);
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

        int from, to, w;

        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }

    static class Planet {

        int id, x, y, z;

        public Planet(int id, int x, int y, int z) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}