package Baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/18223">백준 18223번 - 다익스트라 : 민준이와 마산 그리고 건우</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-18223%EB%B2%88-%EB%AF%BC%EC%A4%80%EC%9D%B4%EC%99%80-%EB%A7%88%EC%82%B0-%EA%B7%B8%EB%A6%AC%EA%B3%A0-%EA%B1%B4%EC%9A%B0">velog</a>
 * @since 2024-09-10
 */
public class BJ_18223 {

    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        graph = new ArrayList[v + 1];

        for (int i = 1; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        int[] fromMinjun = dijkstra(1, v);
        int[] fromGunwoo = dijkstra(p, v);

        int d = fromMinjun[v] - (fromGunwoo[1] + fromGunwoo[v]);

        System.out.println(d == 0 ? "SAVE HIM" : "GOOD BYE");
    }

    private static int[] dijkstra(int start, int v) {

        boolean[] visit = new boolean[v + 1];

        int[] dist = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        dist[start] = 0;

        PriorityQueue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(start, 0));

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