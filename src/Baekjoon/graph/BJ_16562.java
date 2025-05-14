package Baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/16562">백준 16562번 - 그래프 탐색 : 친구비</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-16562%EB%B2%88-%EC%B9%9C%EA%B5%AC%EB%B9%84">velog</a>
 * @since 2025-05-07
 */
public class BJ_16562 {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        parent = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            parent[i] = i;
        }

        // 1
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken()) - 1;

            if (v > w) {
                int temp = v;
                v = w;
                w = temp;
            }

            union(v, w);
        }

        // 2
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            int root = find(i);
            cost[root] = Math.min(cost[root], arr[i]);
        }

        // 3
        int sum = 0;
        for (int c : cost) {
            if (c != Integer.MAX_VALUE) {
                sum += c;
            }
        }

        System.out.println(sum <= k ? sum : "Oh no");
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) parent[b] = a;
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }
}
