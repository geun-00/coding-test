package Baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/16118">백준 16118번 - 다익스트라 : 달빛 여우</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-16118%EB%B2%88-%EB%8B%AC%EB%B9%9B-%EC%97%AC%EC%9A%B0">velog</a>
 *
 * @since 2024-09-27
 */
public class BJ_16118 {

    static ArrayList<Node>[] graph;
    static int n, m;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, d * 2L));
            graph[b].add(new Node(a, d * 2L));
        }

        long[] foxDist = foxDijkstra();
        long[][] wolfDist = wolfDijkstra();

        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (foxDist[i] < Math.min(wolfDist[i][0], wolfDist[i][1])) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static long[][] wolfDijkstra() {

        PriorityQueue<Node> qu = new PriorityQueue<>();
        //1번에서는 두배의 속도로 가야하기 때문에 1번에는 절반의 속도로 도착한 것으로 초기화
        qu.offer(new Node(1, 0, 0));

        //[n][0] : n번 노드에 절반의 속도로 도착할 때
        //[n][1] : n번 노드에 두배의 속도로 도착할 때
        long[][] dist = new long[n + 1][2];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }
        dist[1][0] = 0;

        boolean[][] visit = new boolean[n + 1][2];

        while (!qu.isEmpty()) {
            Node now = qu.poll();

            int nowTo = now.to;
            int state = now.state;

            if (visit[nowTo][state]) {
                continue;
            }
            visit[nowTo][state] = true;

            for (Node next : graph[nowTo]) {
                long newDist;
                int nState;

                //이전에 느리게 도착한 경우, 다음에는 빠르게 가야 한다.
                if (state == 0) {
                    newDist = dist[nowTo][state] + next.weight / 2;
                    nState = 1;

                //이전에 빠르게 도착한 경우, 다음에는 느리게 가야 한다.
                } else {
                    newDist = dist[nowTo][state] + next.weight * 2;
                    nState = 0;
                }

                if (dist[next.to][nState] > newDist) {
                    dist[next.to][nState] = newDist;
                    qu.offer(new Node(next.to, newDist, nState));
                }
            }
        }

        return dist;
    }

    private static long[] foxDijkstra() {

        PriorityQueue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(1, 0));

        boolean[] visit = new boolean[n + 1];
        long[] dist = new long[n + 1];

        Arrays.fill(dist, Long.MAX_VALUE);

        dist[1] = 0;

        while (!qu.isEmpty()) {
            Node now = qu.poll();

            int nowTo = now.to;
            if (visit[nowTo]) {
                continue;
            }
            visit[nowTo] = true;

            for (Node next : graph[nowTo]) {

                long newDist = dist[nowTo] + next.weight;
                int nextTo = next.to;

                if (dist[nextTo] > newDist) {
                    dist[nextTo] = newDist;
                    qu.offer(new Node(nextTo, dist[nextTo]));
                }
            }
        }

        return dist;
    }

    static class Node implements Comparable<Node> {
        int to;
        long weight;
        int state;

        public Node(int to, long weight, int state) {
            this.to = to;
            this.weight = weight;
            this.state = state;
        }

        public Node(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.weight, o.weight);
        }
    }
}
