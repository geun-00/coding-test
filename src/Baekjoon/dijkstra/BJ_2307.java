package Baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2307">백준 2307번 - 다익스트라 : 도로검문</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2307%EB%B2%88-%EB%8F%84%EB%A1%9C%EA%B2%80%EB%AC%B8">velog</a>
 * @since 2024-10-24
 */
public class BJ_2307 {

    static int n;
    static int[] path;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        path = new int[n + 1];
        graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, t));
            graph[b].add(new Node(a, t));
        }

        //막는 노드 없이 1번에서 N번 최단 비용
        int[] fromStart = dijkstra(-1);

        //처음부터 막을 수 있는 경우
        if (fromStart[n] == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        int prev = path[n];
        boolean[] arr = new boolean[n];

        //1번에서 N번 최단 경로로 거치는 노드 마킹
        while (prev != 1) {
            arr[prev] = true;
            prev = path[prev];
        }

        int init = fromStart[n];
        int result = 0;

        for (int i = 2; i < n; i++) {
            if (arr[i]) {

                int[] dist = dijkstra(i);

                //도시를 빠져나가게 못하게 만든 경우
                if (dist[n] == Integer.MAX_VALUE) {
                    result = -1;
                    break;
                }

                result = Math.max(result, dist[n] - init);
            }
        }

        System.out.println(result);

    }

    private static int[] dijkstra(int ban) {

        int[] dist = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[1] = 0;

        PriorityQueue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(1, 0));

        boolean[] visit = new boolean[n + 1];

        while (!qu.isEmpty()) {

            Node now = qu.poll();

            if (visit[now.to]) continue;

            visit[now.to] = true;

            for (Node next : graph[now.to]) {

                //막힌 노드 pass
                if (next.to == ban) continue;

                if (dist[next.to] > dist[now.to] + next.weight) {
                    dist[next.to] = dist[now.to] + next.weight;
                    qu.offer(new Node(next.to, dist[next.to]));
                    path[next.to] = now.to;
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
