package Baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/24230">백준 24230번 - 트리 : 트리 색칠하기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-24230%EB%B2%88-%ED%8A%B8%EB%A6%AC-%EC%83%89%EC%B9%A0%ED%95%98%EA%B8%B0">velog</a>
 * @since 2025-02-19
 */
public class BJ_24230 {

    static int[] color;
    static List<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        color = new int[n];
        tree = new ArrayList[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            color[i] = Integer.parseInt(st.nextToken());
            tree[i] = new ArrayList<>();
        }

        int ans = 0;
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

//            tree[a].add(b);
//            tree[b].add(a);
            if (color[a] != color[b]) {
                ans++;
            }
        }

        if (color[0] != 0) {
            ans++;
        }
//
//        int ans = dfs(-1, 0);
//        if (color[0] != 0) {
//            ans++;
//        }
        System.out.println(ans);
    }

    private static int dfs(int parent, int node) {

        int count = 1;

        for (int child : tree[node]) {
            if (child != parent) {
                count += dfs(node, child);
            }
        }

        if (parent != -1 && color[parent] == color[node]) {
            count--;
        }

        return count;
    }
}
