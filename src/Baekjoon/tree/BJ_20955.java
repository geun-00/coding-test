package Baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/20955">백준 20955번 - 트리 : 민서의 응급 수술</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-20955%EB%B2%88-%EB%AF%BC%EC%84%9C%EC%9D%98-%EC%9D%91%EA%B8%89-%EC%88%98%EC%88%A0">velog</a>
 * @since 2024-10-31
 */
public class BJ_20955 {

    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        int cut = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            u = find(u);
            v = find(v);

            if (u == v) {
                cut++;
                continue;
            }

            union(u, v);
        }

        HashSet<Integer> root = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            root.add(find(i));
        }

        System.out.println(cut + root.size() - 1);
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
}
