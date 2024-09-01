package Baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/14284">백준 14284번 - 다익스트라 : 간선 이어가기 2</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-14284%EB%B2%88-%EA%B0%84%EC%84%A0-%EC%9D%B4%EC%96%B4%EA%B0%80%EA%B8%B0-2">velog</a>
 *
 * @since 2024-09-02
 */
public class BJ_14284 {

    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());

        int s = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int[] dist = dijkstra(s, n);

        System.out.println(dist[t]);
    }

    private static int[] dijkstra(int s, int n) {

        PriorityQueue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(s, 0));

        boolean[] visit = new boolean[n + 1];
        int[] dist = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[s] = 0;

        while (!qu.isEmpty()) {
            Node now = qu.poll();

            if (visit[now.to]) {
                continue;
            }
            visit[now.to] = true;

            for (Node next : graph[now.to]) {
                if (dist[next.to] > dist[now.to] + next.weight) {
                    dist[next.to] = dist[now.to] + next.weight;

                    qu.offer(new Node(next.to, dist[next.to]));
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