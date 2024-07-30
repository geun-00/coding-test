package Baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/3584">백준 3584번 - 트리 : 가장 가까운 공통 조상</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-3584%EB%B2%88-%EA%B0%80%EC%9E%A5-%EA%B0%80%EA%B9%8C%EC%9A%B4-%EA%B3%B5%ED%86%B5-%EC%A1%B0%EC%83%81">velog</a>
 * @since 2024-07-29
 */
public class BJ_3584 {

    static ArrayList<Integer>[] tree;
    static int[] parent, depth;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {

            int n = Integer.parseInt(br.readLine());

            tree = new ArrayList[n + 1];
            parent = new int[n + 1];
            depth = new int[n + 1];
            visit = new boolean[n + 1];

            for (int i = 1; i <= n; i++) {
                tree[i] = new ArrayList<>();
            }

            int[] child = new int[n + 1];
            for (int i = 0; i < n - 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                tree[a].add(b);
                tree[b].add(a);

                child[b]++;
            }

            int root = 0;
            for (int i = 1; i <= n; i++) {
                if (child[i] == 0) { //아무도 자신을 자식으로 가지고 있지 않은 노드가 루트 노드
                    root = i;
                    break;
                }
            }

            dfs(root, 1);

            StringTokenizer st = new StringTokenizer(br.readLine());

            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            System.out.println(getLca(node1, node2));
        }
    }

    private static int getLca(int a, int b) {

        //a노드가 항상 더 깊은 노드가 되도록 한다.
        if (depth[a] < depth[b]) {
            //swap
            int temp = a;
            a = b;
            b = temp;
        }

        //두 노드의 깊이 맞추기
        while (depth[a] != depth[b]) {
            a = parent[a];
        }

        //최소 공통 조상 찾기
        while (a != b) {
            a = parent[a];
            b = parent[b];
        }

        return a;
    }

    private static void dfs(int node, int level) {

        visit[node] = true;
        depth[node] = level;

        for (int child : tree[node]) {
            if (!visit[child]) {

                parent[child] = node;
                dfs(child, level + 1);
            }
        }
    }
}
