package Baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/10282">백준 10282번 - 다익스트라 : 해킹</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-10282%EB%B2%88-%ED%95%B4%ED%82%B9">velog</a>
 * @since 2024-07-22
 */
public class BJ_10282 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine()); //테스트 케이스 개수

        for (int i = 0; i < t; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken()); //컴퓨터 개수(노드 개수)
            int d = Integer.parseInt(st.nextToken()); //의존성 개수(간선 개수)
            int c = Integer.parseInt(st.nextToken()); //해킹당한 컴퓨터(시작 노드)

            ArrayList<Node>[] graph = new ArrayList[n + 1];
            int[] dist = new int[n + 1];
            boolean[] visit = new boolean[n + 1];

            for (int j = 1; j <= n; j++) {
                graph[j] = new ArrayList<>();
                dist[j] = Integer.MAX_VALUE;
            }

            //간선 정보 연결(b -> a)
            for (int j = 0; j < d; j++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                graph[b].add(new Node(a, s));
            }

            //다익스트라 수행
            PriorityQueue<Node> qu = new PriorityQueue<>();
            qu.offer(new Node(c, 0));
            dist[c] = 0;

            while (!qu.isEmpty()) {
                Node now = qu.poll();

                if (visit[now.adj]) {
                    continue;
                }
                visit[now.adj] = true;

                for (Node next : graph[now.adj]) {
                    if (dist[next.adj] > dist[now.adj] + next.w) {
                        dist[next.adj] = dist[now.adj] + next.w;

                        qu.offer(new Node(next.adj, dist[next.adj]));
                    }
                }
            }

            int max = 0; //마지막 컴퓨터가 감염되는 시간
            int count = 0; //감염되는 컴퓨터 수

            for (int j = 1; j <= n; j++) {
                if (visit[j]) {
                    count++;
                    max = Math.max(max, dist[j]);
                }
            }

            sb
                    .append(count)
                    .append(" ")
                    .append(max)
                    .append("\n");
        }

        System.out.print(sb);
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
