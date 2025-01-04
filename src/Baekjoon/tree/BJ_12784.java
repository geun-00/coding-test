package Baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/12784">백준 12784번 - 트리 : 인하니카 공화국</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-12784%EB%B2%88-%EC%9D%B8%ED%95%98%EB%8B%88%EC%B9%B4-%EA%B3%B5%ED%99%94%EA%B5%AD">velog</a>
 * @since 2024-12-23
 */
public class BJ_12784 {

    static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            List<Node>[] tree = new ArrayList[n];
            dp = new int[n];

            for (int i = 0; i < n; i++) {
                tree[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int u = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken()) - 1;
                int d = Integer.parseInt(st.nextToken());

                tree[u].add(new Node(v, d));
                tree[v].add(new Node(u, d));
            }

            sb.append(dfs(0, -1, tree)).append("\n");
        }

        System.out.print(sb);
    }

    private static int dfs(int node, int parent, List<Node>[] tree) {

        //리프 노드인 경우
        if (tree[node].size() == 1 && tree[node].get(0).node == parent) {
            return dp[node] = tree[node].get(0).dynamite;
        }

        for (Node child : tree[node]) {

            int nextNode = child.node;

            if (nextNode != parent) {
                int cost = dfs(nextNode, node, tree);
                dp[node] += Math.min(cost, child.dynamite);
            }
        }

        return dp[node];
    }

    static class Node {

        int node, dynamite;

        public Node(int node, int dynamite) {
            this.node = node;
            this.dynamite = dynamite;
        }
    }
}