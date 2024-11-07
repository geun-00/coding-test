package Baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/5719">백준 5719번 - 다익스트라 : 거의 최단 경로</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-5719%EB%B2%88-%EA%B1%B0%EC%9D%98-%EC%B5%9C%EB%8B%A8-%EA%B2%BD%EB%A1%9C">velog</a>
 *
 * @since 2024-11-07
 */
public class BJ_5719 {

    static final int INF = Integer.MAX_VALUE;

    static ArrayList<Node>[] graph;
    static ArrayList<Integer>[] shortestPath;
    static boolean[][] blocked;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) {
                System.out.print(sb);
                break;
            }

            graph = new ArrayList[n];
            shortestPath = new ArrayList[n];
            blocked = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
                shortestPath[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());

                graph[u].add(new Node(v, p));
            }

            dijkstra(s, n);

            block(d);

            int[] dist = dijkstra(s, n);

            sb.append(dist[d] == INF ? -1 : dist[d]).append("\n");
        }
    }

    private static int[] dijkstra(int s, int n) {

        PriorityQueue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(s, 0));

        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[s] = 0;

        while (!qu.isEmpty()) {

            Node now = qu.poll();

            if (dist[now.to] < now.w) continue;

            for (Node next : graph[now.to]) {

                //첫번째 다익스트라 이후 차단된 경로
                if(blocked[now.to][next.to]) continue;

                //가능한 최단 경로가 2개 이상일 경우
                if (dist[next.to] == dist[now.to] + next.w) {
                    shortestPath[next.to].add(now.to);
                }

                //새로운 최단 경로를 찾은 경우
                if (dist[next.to] > dist[now.to] + next.w) {

                    dist[next.to] = dist[now.to] + next.w;
                    qu.offer(new Node(next.to, dist[next.to]));

                    shortestPath[next.to].clear();
                    shortestPath[next.to].add(now.to);
                }
            }
        }

        return dist;
    }

    private static void block(int d) {

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(d);

        while (!qu.isEmpty()) {

            int now = qu.poll();

            for (int prev : shortestPath[now]) {
                if (!blocked[prev][now]) {
                    blocked[prev][now] = true;
                    qu.offer(prev);
                }
            }
        }
    }

    static class Node implements Comparable<Node> {

        int to, w;

        public Node(int to, int w) {
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
}
