package Baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1719">백준 1719번 - 다익스트라 : 택배</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1719%EB%B2%88-%ED%83%9D%EB%B0%B0">velog</a>
 * @since 2024-08-09
 */
public class BJ_1719 {

    static ArrayList<Node>[] graph;
    static int[][] dist;
    static int[][] result;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        dist = new int[n + 1][n + 1];
        result = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            Arrays.fill(dist[i], 1000 * 10000);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            //dijkstra
            graph[u].add(new Node(v, w));
            graph[v].add(new Node(u, w));

            //floyd warshall
            dist[u][v] = w;
            dist[v][u] = w;

            //처음에는 도착 노드까지 바로 가는 것으로 설정
            result[u][v] = v;
            result[v][u] = u;
        }

        for (int k = 1; k <= n; k++) { //경유지
            for (int s = 1; s <= n; s++) { //출발지
                for (int e = 1; e <= n; e++) { //도착지
                    if (dist[s][e] > dist[s][k] + dist[k][e]) {
                        dist[s][e] = dist[s][k] + dist[k][e];
                        result[s][e] = result[s][k];
                    }
                }
            }
        }

//        for (int i = 1; i <= n; i++) {
//            dijkstra(i, n);
//        }

        printResult(n);
    }

    private static void printResult(int n) {

        StringBuilder sb = new StringBuilder();

        for (int s = 1; s <= n; s++) {
            for (int e = 1; e <= n; e++) {
                if (s == e) {
                    sb.append("- ");
                } else {
                    sb.append(result[s][e]).append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void dijkstra(int s, int n) {

        PriorityQueue<Node> qu = new PriorityQueue<>();
        boolean[] visit = new boolean[n + 1];

        qu.offer(new Node(s, 0));
        dist[s][s] = 0;

        while (!qu.isEmpty()) {
            Node now = qu.poll();

            int nowNode = now.adj;

            if (visit[nowNode]) {
                continue;
            }
            visit[nowNode] = true;

            for (Node next : graph[nowNode]) {

                int nextNode = next.adj;
                int nextDist = next.w;

                if (dist[s][nextNode] > dist[s][nowNode] + nextDist) {
                    dist[s][nextNode] = dist[s][nowNode] + nextDist;

                    qu.offer(new Node(nextNode, dist[s][nextNode]));

                    //출발 노드와 직접 연결된 경우
                    if (nowNode == s) {
                        result[s][nextNode] = nextNode;
                    }
                    //출발 노드와 한 단계 이상 건너서 연결된 경우
                    else {
                        result[s][nextNode] = result[s][nowNode];
                    }
                }
            }
        }
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
