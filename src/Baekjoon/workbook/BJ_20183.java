package Baekjoon.workbook;

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
 * <a href = "https://www.acmicpc.net/problem/20183">백준 20183번 - 골목 대장 호석 - 효율성 2</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-20183%EB%B2%88-%EA%B3%A8%EB%AA%A9-%EB%8C%80%EC%9E%A5-%ED%98%B8%EC%84%9D-%ED%9A%A8%EC%9C%A8%EC%84%B1-2">velog</a>
 * @since 2025-04-22
 */
public class BJ_20183 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken()) - 1;
        int b = Integer.parseInt(st.nextToken()) - 1;
        long c = Long.parseLong(st.nextToken());

        List<Node>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 1
        long max = 0;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            long w = Long.parseLong(st.nextToken());

            max = Math.max(max, w);

            graph[u].add(new Node(v, w));
            graph[v].add(new Node(u, w));
        }

        // 2
        long left = 1, right = max;
        long ans = -1;

        while (left <= right) {
            long mid = (left + right) / 2;

            long[] dist = dijkstra(graph, mid, a, n, c);

            if (dist[b] <= c) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(ans);
    }

    // 3
    private static long[] dijkstra(List<Node>[] graph, long limit, int start, int n, long c) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(node -> node.w));
        pq.offer(new Node(start, 0));

        boolean[] visit = new boolean[n];

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (visit[node.to]) continue;
            visit[node.to] = true;

            for (Node next : graph[node.to]) {
                if (next.w > limit) continue;   // 요금이 제한을 넘는 경우
                if (dist[node.to] + next.w > c) continue;   // 이동 중 비용이 가진 돈을 넘는 경우

                if (dist[next.to] > dist[node.to] + next.w) {
                    dist[next.to] = dist[node.to] + next.w;
                    pq.offer(new Node(next.to, dist[next.to]));
                }
            }
        }

        return dist;
    }

    static class Node {
        int to;
        long w;

        public Node(int to, long w) {
            this.to = to;
            this.w = w;
        }
    }
}
