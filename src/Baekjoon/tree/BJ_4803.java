package Baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/4803">백준 4803번 - 트리 : 트리</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-4803%EB%B2%88-%ED%8A%B8%EB%A6%AC">velog</a>
 * @since 2024-08-02
 */
public class BJ_4803 {

    static ArrayList<Integer>[] tree;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int count = 1;

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) {
                break;
            }

            tree = new ArrayList[n + 1];
            visit = new boolean[n + 1];

            for (int i = 1; i <= n; i++) {
                tree[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                tree[a].add(b);
                tree[b].add(a);
            }

            int trees = 0;
            for (int i = 1; i <= n; i++) {
                if (!visit[i]) {
                    if (dfs(i, -1)) { //사이클이 발생하지 않는 트리만 개수에 추가
                        trees++;
                    }
                }
            }

            sb.append("Case ").append(count++).append(": ");
            if (trees == 0) {
                sb.append("No trees.").append("\n");
            } else if (trees == 1) {
                sb.append("There is one tree.").append("\n");
            } else {
                sb.append("A forest of ").append(trees).append(" trees.").append("\n");
            }
        }
        System.out.print(sb);
    }

    private static boolean dfs(int node, int parent) {

        if (visit[node]) {
            return false;
        }

        visit[node] = true;

        for (int child : tree[node]) {
            //부모가 아닌 노드만 탐색했을 때 사이클이 발생하면 false
            if (child != parent && !dfs(child, node)) {
                return false;
            }
        }
        return true;
    }
}
