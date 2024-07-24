package Baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2533">백준 2533번 - 트리 : 사회망 서비스(SNS)</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2533%EB%B2%88-%EC%82%AC%ED%9A%8C%EB%A7%9D-%EC%84%9C%EB%B9%84%EC%8A%A4SNS">velog</a>
 *
 * @since 2024-07-23
 */
public class BJ_2533 {

    static ArrayList<Integer>[] tree;
    static int[][] dp;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        dp = new int[n + 1][2];
        tree = new ArrayList[n + 1];
        visit = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            tree[u].add(v);
            tree[v].add(u);
        }

        dfs(1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    private static void dfs(int node) {
        visit[node] = true;

        dp[node][0] = 0; //노드가 얼리아답터가 아닌 경우
        dp[node][1] = 1; //노드가 얼리아답터인 경우

        for (int child : tree[node]) {
            if (!visit[child]) {
                //dfs()를 먼저 수행하여 자식 노드들이 먼저 계산되도록 한다.
                dfs(child);

                //현재 노드가 얼리아답터가 아니면 자식 노드는 무조건 얼리아답터여야 한다.
                dp[node][0] += dp[child][1];

                //현재 노드가 얼리아답터면 자식 노드는 얼리아답터이든 아니든 상관 없다.
                dp[node][1] += Math.min(dp[child][0], dp[child][1]);
            }
        }
    }
}