package Baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/20924">백준 20924번 - 트리 : 트리의 기둥과 가지</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-20924%EB%B2%88-%ED%8A%B8%EB%A6%AC%EC%9D%98-%EA%B8%B0%EB%91%A5%EA%B3%BC-%EA%B0%80%EC%A7%80">velog</a>
 * @since 2025-01-31
 */
public class BJ_20924 {

    static List<Node>[] tree;
    static List<Integer> leafNodes = new ArrayList<>();
    static int gigaNode = -1;
    static int[] dist;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken()) - 1;

        dist = new int[n];
        tree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            tree[a].add(new Node(b, c));
            tree[b].add(new Node(a, c));
        }

        findGigaNode(r, -1);

        if (leafNodes.size() == 1) gigaNode = leafNodes.get(0);

        int ans1 = dist[gigaNode];
        int ans2 = 0;

        for (int node : leafNodes) {
            ans2 = Math.max(ans2, dist[node] - dist[gigaNode]);
        }

        System.out.println(ans1 + " " + ans2);
    }

    private static void findGigaNode(int node, int parent) {

        int children = 0;

        for (Node child : tree[node]) {
            if (child.to != parent) {
                children++;
                dist[child.to] = dist[node] + child.dist;

                findGigaNode(child.to, node);
            }
        }

        if (children == 0) leafNodes.add(node);
        if (children >= 2) gigaNode = node;
    }

    static class Node {

        int to, dist;

        public Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }
}
