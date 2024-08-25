package Baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/17396">백준 17396번 - 다익스트라 : 백도어</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-17396%EB%B2%88-%EB%B0%B1%EB%8F%84%EC%96%B4">velog</a>
 * @since 2024-08-23
 */
public class BJ_17396 {

    static ArrayList<Node>[] graph;
    static int[] ward;
    static int n, m;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n];
        ward = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            ward[i] = Integer.parseInt(st.nextToken());
        }

        ward[n - 1] = 0;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, t));
            graph[b].add(new Node(a, t));
        }

        long[] dist = dijkstra();

        System.out.println(dist[n - 1] == Long.MAX_VALUE ? -1 : dist[n - 1]);
    }

    private static long[] dijkstra() {

        PriorityQueue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(0, 0));

        boolean[] visit = new boolean[n];
        long[] dist = new long[n];

        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        while (!qu.isEmpty()) {
            Node now = qu.poll();

            if (visit[now.to]) {
                continue;
            }
            visit[now.to] = true;

            for (Node next : graph[now.to]) {

                if (ward[next.to] == 0) {
                    if (dist[next.to] > dist[now.to] + next.weight) {
                        dist[next.to] = dist[now.to] + next.weight;

                        qu.offer(new Node(next.to, (int) dist[next.to]));
                    }
                }
            }
        }

        return dist;
    }

    static class Node implements Comparable<Node> {

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
