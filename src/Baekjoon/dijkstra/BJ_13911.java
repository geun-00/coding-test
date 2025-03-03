package Baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/13911">백준 13911번 - 다익스트라 : 집 구하기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-13911%EB%B2%88-%EC%A7%91-%EA%B5%AC%ED%95%98%EA%B8%B0">velog</a>
 * @since 2025-02-26
 */
public class BJ_13911 {

    public static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        List<Node>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v, w));
            graph[v].add(new Node(u, w));
        }

        //맥도날드
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int[] macdonald = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            macdonald[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        //스타벅스
        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int[] starbucks = new int[s];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < s; i++) {
            starbucks[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        //다익스트라
        int[] macDist = dijkstra(graph, macdonald, x, n);
        int[] starDist = dijkstra(graph, starbucks, y, n);

        int ans = INF;
        for (int i = 0; i < n; i++) {
            if (macDist[i] == 0 || starDist[i] == 0) continue;      //맥도날드이거나 스타벅스 위치
            if (macDist[i] == INF || starDist[i] == INF) continue;  //최단거리가 x, y 초과인 위치

            ans = Math.min(ans, macDist[i] + starDist[i]);
        }

        System.out.println(ans == INF ? -1 : ans);
    }

    private static int[] dijkstra(List<Node>[] graph, int[] arr, int limit, int n) {
        int[] dist = new int[n];
        Arrays.fill(dist, INF);

        PriorityQueue<Node> qu = new PriorityQueue<>(Comparator.comparingInt(node -> node.w));

        for (int a : arr) {
            qu.offer(new Node(a, 0));
            dist[a] = 0;
        }

        while (!qu.isEmpty()) {
            Node node = qu.poll();
            int to = node.to;

            for (Node next : graph[to]) {
                int newDist = dist[to] + next.w;

                if (newDist <= limit && dist[next.to] > newDist) {
                    dist[next.to] = newDist;
                    qu.offer(new Node(next.to, newDist));
                }
            }
        }

        return dist;
    }

    static class Node {
        int to, w;

        public Node(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }
}
