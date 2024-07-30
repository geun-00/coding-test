package Baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/15681">백준 15681번 - 트리 : 트리와 쿼리</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-15681%EB%B2%88-%ED%8A%B8%EB%A6%AC%EC%99%80-%EC%BF%BC%EB%A6%AC">velog</a>
 * @since 2024-07-29
 */
public class BJ_15681 {

    static ArrayList<Integer>[] tree;
    static boolean[] visit;
    static int[] subTreeSize;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        tree = new ArrayList[n + 1];
        visit = new boolean[n + 1];
        subTreeSize = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            tree[u].add(v);
            tree[v].add(u);
        }

        getSubTreeSize(r);

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < q; i++) {
            int u = Integer.parseInt(br.readLine());

            result.append(subTreeSize[u]).append("\n");
        }

        System.out.print(result);
    }

    private static void getSubTreeSize(int parent) {

        visit[parent] = true;
        subTreeSize[parent] = 1;

        for (int child : tree[parent]) {
            if (!visit[child]) {
                getSubTreeSize(child); //자식 노드의 서브트리 사이즈를 구한다.

                subTreeSize[parent] += subTreeSize[child];
            }
        }
    }
}