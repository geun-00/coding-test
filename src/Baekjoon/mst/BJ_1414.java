package Baekjoon.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <a href = "https://www.acmicpc.net/problem/1414">백준 1414번 - MST : 불우이웃돕기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1414%EB%B2%88-%EB%B6%88%EC%9A%B0%EC%9D%B4%EC%9B%83%EB%8F%95%EA%B8%B0">velog</a>
 * @since 2025-03-03
 */
public class BJ_1414 {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        parent = new int[n];

        PriorityQueue<Edge> qu = new PriorityQueue<>(Comparator.comparingInt(e -> e.w));
        int total = 0;

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            char[] arr = br.readLine().toCharArray();

            for (int j = 0; j < n; j++) {
                char ch = arr[j];
                if (ch == '0') continue;

                int len;
                if (Character.isUpperCase(ch)) len = ch - 'A' + 27;
                else len = ch - 'a' + 1;

                qu.offer(new Edge(i, j, len));
                total += len;
            }
        }

        int connected = 0;
        int len = 0;
        while (!qu.isEmpty()) {
            Edge e = qu.poll();

            if (find(e.from) != find(e.to)) {
                union(e.from, e.to);
                connected++;
                len += e.w;
            }
        }

        System.out.println(connected == n - 1 ? total - len : -1);
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

    static class Edge {
        int from, to, w;

        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }
    }
}
