package Baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2211">백준 2211번 - 다익스트라 : 네트워크복구</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2211%EB%B2%88-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC%EB%B3%B5%EA%B5%AC">velog</a>
 * @since 2024-08-16
 */
public class BJ_2211 {

    static ArrayList<Node>[] graph;
    static int[] result;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        result = new int[n + 1];

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

        dijkstra(n);

        ArrayList<int[]> ans = new ArrayList<>();

        for (int i = 2; i <= n; i++) {
            if (result[i] > 0) {
                ans.add(new int[]{result[i], i}); //서로 연결된 두 노드의 번호
            }
        }

        StringBuilder sb = new StringBuilder();

        sb.append(ans.size()).append("\n");

        for (int[] a : ans) {
            sb.append(a[0]).append(" ").append(a[1]).append("\n");
        }

        System.out.println(sb);
    }

    private static void dijkstra(int n) {

        PriorityQueue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(1, 0));

        boolean[] visit = new boolean[n + 1];
        int[] dist = new int[n + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

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

                    result[next.to] = now.to; //현재 노드에 도착하기 전 직전 노드 저장
                }
            }
        }
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