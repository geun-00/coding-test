package Baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/11779">백준 11779번 - 다익스트라 : 최소비용 구하기 2</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-11779%EB%B2%88-%EC%B5%9C%EC%86%8C%EB%B9%84%EC%9A%A9-%EA%B5%AC%ED%95%98%EA%B8%B0-2">velog</a>
 * @since 2024-07-10
 */
public class BJ_11779 {

    static ArrayList<Node>[] graph;
    static int[] path;
    static int[] dist;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        dist = new int[n + 1];
        path = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[s].add(new Node(e, v));
        }

        st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start, n);
        printResult(end);
    }

    private static void dijkstra(int start, int n) {
        boolean[] visit = new boolean[n + 1];
        dist[start] = 0;

        PriorityQueue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(start, 0));

        while (!qu.isEmpty()) {
            Node now = qu.poll();

            if (visit[now.adj]) {
                continue;
            }
            visit[now.adj] = true;

            for (Node next : graph[now.adj]) {
                if (dist[next.adj] > dist[now.adj] + next.v) {

                    dist[next.adj] = dist[now.adj] + next.v;
                    qu.offer(new Node(next.adj, dist[next.adj]));

                    path[next.adj] = now.adj; //최소 비용 경로
                }
            }
        }
    }

    private static void printResult(int end) {
        StringBuilder sb = new StringBuilder();

        sb.append(dist[end]).append("\n"); //출발 -> 도착 최소 비용

        ArrayList<Integer> route = new ArrayList<>();

        int now = end;
        while (now != 0) { //출발 도시에 도착할 때까지 역추적
            route.add(now);
            now = path[now];
        }

        sb.append(route.size()).append("\n"); //최소 비용 경로의 도시 개수

        for (int i = route.size() - 1; i >= 0; i--) { //최소 비용 경로의 도시를 순서대로 출력
            sb.append(route.get(i)).append(" ");
        }

        System.out.print(sb);
    }

    static class Node implements Comparable<Node>{
        int adj, v;

        public Node(int adj, int v) {
            this.adj = adj;
            this.v = v;
        }

        @Override
        public int compareTo(Node o) {
            return this.v - o.v;
        }
    }
}
