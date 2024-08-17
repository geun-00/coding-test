package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1939">백준 1939번 - 이분 탐색 : 중량제한</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1939%EB%B2%88-%EC%A4%91%EB%9F%89%EC%A0%9C%ED%95%9C">velog</a>
 * @since 2024-08-12
 */
public class BJ_1939 {

    static ArrayList<Node>[] graph;
    static int start, end;
    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        int max = 0;

        ArrayList<Edge> edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            //bfs
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));

            //union-find
            edges.add(new Edge(a, b, c));

            max = Math.max(max, c);
        }

        st = new StringTokenizer(br.readLine());

        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        int s = 1;
        int e = max;
        int result = 0;

        while (s <= e) {

            int mid = (s + e) / 2;

            //union-find
            parent = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }

            for (Edge edge : edges) {
                if (edge.weight >= mid && find(edge.from) != find(edge.to)) {
                    union(edge.from, edge.to);
                }
            }

            if (find(start) == find(end)) {
                result = mid;
                s = mid + 1;
            } else {
                e = mid - 1;
            }
            //union-find

            //bfs
//            if (bfs(mid, n)) {
//                result = mid;
//                s = mid + 1;
//            } else {
//                e = mid - 1;
//            }
            //bfs
        }

        System.out.println(result);

    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    private static boolean bfs(int weight, int n) {

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(start);

        boolean[] visit = new boolean[n + 1];
        visit[start] = true;

        while (!qu.isEmpty()) {

            int now = qu.poll();

            if (now == end) {
                return true;
            }

            for (Node next : graph[now]) {
                if (!visit[next.to] && next.weight >= weight) {
                    visit[next.to] = true;
                    qu.offer(next.to);
                }
            }
        }

        return false;
    }

    static class Node {

        int to, weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class Edge {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}