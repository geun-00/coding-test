package Baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/11657">백준 11657번 - 벨만포드 : 타임머신</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-11657%EB%B2%88-%ED%83%80%EC%9E%84%EB%A8%B8%EC%8B%A0">velog</a>
 * @since 2024-11-30
 */
public class BJ_11657 {
    public static void main(String[] args) throws IOException {

        final int INF = Integer.MAX_VALUE;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Edge[] edges = new Edge[m];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(a, b, c);
        }

        long[] dist = new long[n + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        for (int i = 0; i < n - 1; i++) {

            for (Edge edge : edges) {

                int from = edge.from;
                int to = edge.to;
                int time = edge.time;

                if (dist[from] != INF && dist[from] + time < dist[to]) {
                    dist[to] = dist[from] + time;
                }
            }
        }

        boolean cycle = false;

        for (Edge edge : edges) {

            int from = edge.from;
            int to = edge.to;
            int time = edge.time;

            if (dist[from] != INF && dist[from] + time < dist[to]) {
                cycle = true;
                break;
            }
        }

        if (!cycle) {
            StringBuilder sb = new StringBuilder();

            for (int i = 2; i <= n; i++) {
                sb.append(dist[i] == INF ? -1 : dist[i]).append("\n");
            }

            System.out.print(sb);
        }
        else {
            System.out.println(-1);
        }
    }

    static class Edge {

        int from, to, time;

        public Edge(int from, int to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }
    }
}