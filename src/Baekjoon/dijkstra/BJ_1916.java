package Baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1916">백준 1916번 - 다익스트라 : 최소비용 구하기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1916%EB%B2%88-%EC%B5%9C%EC%86%8C%EB%B9%84%EC%9A%A9-%EA%B5%AC%ED%95%98%EA%B8%B0">velog</a>
 * @since 2024-06-22
 */
public class BJ_1916 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine()); //도시 개수
        int m = Integer.parseInt(br.readLine()); //버스 개수

        int[] dist = new int[n + 1];
        ArrayList<Node>[] graph = new ArrayList[n + 1];
        boolean[] visit = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE;
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()); //출발 도시
            int e = Integer.parseInt(st.nextToken()); //도착 도시
            int v = Integer.parseInt(st.nextToken()); //버스 비용

            graph[s].add(new Node(e, v));
        }

        st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dist[start] = 0;

        Queue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(start, 0));

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

        System.out.println(dist[end]);
    }

    static class Node implements Comparable<Node> {
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
