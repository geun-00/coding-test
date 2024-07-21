package Baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/14938">백준 14938번 - 다익스트라 : 서강그라운드</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-14938%EB%B2%88-%EC%84%9C%EA%B0%95%EA%B7%B8%EB%9D%BC%EC%9A%B4%EB%93%9C">velog</a>
 * @since 2024-07-18
 */
public class BJ_14938 {

    static int[][] dist;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[] items = new int[n + 1];
        dist = new int[n + 1][n + 1];
        graph = new ArrayList[n + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
            Arrays.fill(dist[i], 100 * 100 * 15 + 1);
            dist[i][i] = 0;
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            //다익스트라
            graph[a].add(new Node(b, l));
            graph[b].add(new Node(a, l));

            //플로이드워셜
//            dist[a][b] = l;
//            dist[b][a] = l;
        }

        dijkstra(n);
//        floydWarshall(n);

        printResult(n, m, items);
    }

    private static void printResult(int n, int m, int[] items) {
        int max = 0;

        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] <= m) {
                    sum += items[j];
                }
            }
            max = Math.max(max, sum);
        }

        System.out.println(max);
    }

    private static void floydWarshall(int n) {

        for (int k = 1; k <= n; k++) {
            for (int s = 1; s <= n; s++) {
                for (int e = 1; e <= n; e++) {
                    if (dist[s][e] > dist[s][k] + dist[k][e]) {
                        dist[s][e] = dist[s][k] + dist[k][e];
                    }
                }
            }
        }
    }

    private static void dijkstra(int n) {

        boolean[][] visit = new boolean[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            PriorityQueue<Node> qu = new PriorityQueue<>();
            qu.offer(new Node(i, 0));

            while (!qu.isEmpty()) {
                Node now = qu.poll();

                if (visit[i][now.adj]) {
                    continue;
                }
                visit[i][now.adj] = true;

                for (Node next : graph[now.adj]) {
                    if (dist[i][next.adj] > dist[i][now.adj] + next.w) {
                        dist[i][next.adj] = dist[i][now.adj] + next.w;
                        qu.offer(new Node(next.adj, dist[i][next.adj]));
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