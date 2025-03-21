package Baekjoon.workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/15591">백준 15591번 - MooTube (Silver)</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-15591%EB%B2%88-MooTube-Silver">velog</a>
 * @since 2025-03-11
 */
public class BJ_15591 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        List<Node>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken()) - 1;
            int q = Integer.parseInt(st.nextToken()) - 1;
            int r = Integer.parseInt(st.nextToken());

            graph[p].add(new Node(q, r));
            graph[q].add(new Node(p, r));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken()) - 1;

            boolean[] visit = new boolean[N];
            Queue<Node> qu = new ArrayDeque<>();
            int ans = 0;

            visit[v] = true;
            for (Node node : graph[v]) {
                visit[node.to] = true;
                qu.offer(new Node(node.to, node.w));
            }

            while (!qu.isEmpty()) {
                Node now = qu.poll();

                if (now.w >= k) {
                    ans++;

                    for (Node node : graph[now.to]) {
                        if (!visit[node.to]) {
                            visit[node.to] = true;
                            qu.offer(new Node(node.to, Math.min(now.w, node.w)));
                        }
                    }
                }
            }

            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }

    static class Node {
        int to, w;

        public Node(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }
}
