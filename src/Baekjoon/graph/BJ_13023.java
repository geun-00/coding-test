package Baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/13023">백준 13023번 - 그래프 탐색 : ABCDE</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-13023%EB%B2%88-ABCDE">velog</a>
 * @since 2024-09-23
 */
public class BJ_13023 {

    static ArrayList<Integer>[] graph;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        visit = new boolean[n];
        graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        for (int i = 0; i < n; i++) {
            dfs(i, 0);
        }

        System.out.println(0);
    }

    private static void dfs(int num, int depth) {
        if (depth == 4) {
            System.out.println(1);
            System.exit(0);
        }

        visit[num] = true;

        for (int next : graph[num]) {
            if (!visit[next]) {
                dfs(next, depth + 1);
            }
        }

        visit[num] = false;
    }
}
