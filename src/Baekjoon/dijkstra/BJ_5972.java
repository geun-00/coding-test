package Baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/5972">백준 5972번 - 다익스트라 : 택배 배송</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-5972%EB%B2%88-%ED%83%9D%EB%B0%B0-%EB%B0%B0%EC%86%A1">velog</a>
 * @since 2024-08-04
 */
public class BJ_5972 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] dist = new int[n + 1];
        boolean[] visit = new boolean[n + 1];
        ArrayList<Node>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        PriorityQueue<Node> qu = new PriorityQueue<>();

        qu.offer(new Node(1, 0));
        dist[1] = 0;

        while (!qu.isEmpty()) {
            Node now = qu.poll();

            if (visit[now.adj]) {
                continue;
            }
            visit[now.adj] = true;

            for (Node next : graph[now.adj]) {
                if (dist[next.adj] > dist[now.adj] + next.w) {
                    dist[next.adj] = dist[now.adj] + next.w;

                    qu.offer(new Node(next.adj, dist[next.adj]));
                }
            }
        }

        System.out.println(dist[n]);
    }

    static class Node implements Comparable<Node>{
        int adj, w;

        public Node(int adj, int w) {
            this.adj = adj;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
}
