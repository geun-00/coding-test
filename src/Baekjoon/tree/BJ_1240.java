package Baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1240">백준 1240번 - 트리 : 노드사이의 거리</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1240%EB%B2%88-%EB%85%B8%EB%93%9C%EC%82%AC%EC%9D%B4%EC%9D%98-%EA%B1%B0%EB%A6%AC">velog</a>
 * @since 2024-08-06
 */
public class BJ_1240 {

    static ArrayList<Node>[] tree;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            tree[a].add(new Node(b, c));
            tree[b].add(new Node(a, c));
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            System.out.println(dfs(s, e, -1, 0));
        }
    }

    private static int dfs(int node, int e, int parent, int dist) {

        //정상적으로 마지막 노드에 도달하면 거리 반환
        if (node == e) {
            return dist;
        }

        for (Node next : tree[node]) {

            if (next.adj != parent) { //부모로 다시 올라감 방지

                //-1을 반환받은 경우 다음 가능한 경로 탐색
                int temp = dfs(next.adj, e, node, dist + next.w);
                if (temp != -1) {
                    return temp;
                }
            }
        }

        //현재 경로는 마지막 노드에 도달할 수 없으므로 예외 값 반환
        return -1;
    }

    static class Node {
        int adj, w;

        public Node(int adj, int w) {
            this.adj = adj;
            this.w = w;
        }
    }
}
