package Baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/13424">백준 13424번 - 다익스트라 : 비밀 모임</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-13424%EB%B2%88-%EB%B9%84%EB%B0%80-%EB%AA%A8%EC%9E%84">velog</a>
 * @since 2024-09-19
 */
public class BJ_13424 {

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

    static ArrayList<Node>[] graph;
    static int[][] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            graph = new ArrayList[n + 1];
            arr = new int[n + 1][n + 1];

            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();

                arr[i][i] = 0;
                for (int j = 1; j <= n; j++) {
                    if (i != j) {
                        arr[i][j] = 1000 * 100;
                    }
                }
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                graph[a].add(new Node(b, c));
                graph[b].add(new Node(a, c));

                arr[a][b] = c;
                arr[b][a] = c;
            }

            for (int k = 1; k <= n; k++) {
                for (int s = 1; s <= n; s++) {
                    for (int e = 1; e <= n; e++) {
                        if (arr[s][e] > arr[s][k] + arr[k][e]) {
                            arr[s][e] = arr[s][k] + arr[k][e];
                        }
                    }
                }
            }

            int k = Integer.parseInt(br.readLine());

            int[][] dist = new int[k][n + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < k; i++) {
                int room = Integer.parseInt(st.nextToken());
                dist[i] = arr[room];

//                dist[i] = dijkstra(room, n);
            }

            int min = Integer.MAX_VALUE;
            int result = 0;

            for (int i = 1; i <= n; i++) {
                int sum = 0;
                for (int j = 0; j < k; j++) {
                    sum += dist[j][i];
                }
                if (sum < min) {
                    min = sum;
                    result = i;
                }
            }

            System.out.println(result);
        }
    }

    private static int[] dijkstra(int start, int n) {

        int[] dist = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        dist[start] = 0;

        boolean[] visit = new boolean[n + 1];

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
}
