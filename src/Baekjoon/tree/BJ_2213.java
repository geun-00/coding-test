package Baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2213">백준 2213번 - 트리 : 트리의 독립집합</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2213%EB%B2%88-%ED%8A%B8%EB%A6%AC%EC%9D%98-%EB%8F%85%EB%A6%BD%EC%A7%91%ED%95%A9">velog</a>
 * @since 2024-09-07
 */
public class BJ_2213 {

    static ArrayList<Integer>[] tree;
    static int[] weight;
    static boolean[] visit;
    static int[][] dp;

    static ArrayList<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n + 1];
        weight = new int[n + 1];
        visit = new boolean[n + 1];
        dp = new int[n + 1][2];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
            weight[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            tree[n1].add(n2);
            tree[n2].add(n1);
        }

        dfs(1); //임의의 노드부터 시작

        //선택했을 때와 선택하지 않았을 때 중 최댓값 출력
        System.out.println(Math.max(dp[1][0], dp[1][1]));

        visit = new boolean[n + 1];
        getResult(1, dp[1][1] > dp[1][0]);

        //정렬 후 출력
        result.sort(null);
        for (int num : result) {
            System.out.print(num + " ");
        }
    }

    private static void getResult(int node, boolean select) {

        visit[node] = true;

        if (select) {
            result.add(node);
        }

        for (int next : tree[node]) {
            if (!visit[next]) {

                //부모 노드를 선택한 경우 자식 노드는 선택할 수 없다.
                if (select) {
                    getResult(next, false);
                }
                //부모 노드를 선택하지 않은 경우 자식 노드는 선택 할 수 있고 안 할 수도 있다.
                else {
                    getResult(next, dp[next][1] > dp[next][0]);
                }
            }
        }
    }

    private static void dfs(int node) {

        visit[node] = true;

        dp[node][0] = 0;            //노드를 선택하지 않은 경우
        dp[node][1] = weight[node]; //노드를 선택한 경우

        for (int next : tree[node]) {
            if (!visit[next]) {

                dfs(next); //자식 노드부터 먼저 탐색

                dp[node][0] += Math.max(dp[next][0], dp[next][1]);
                dp[node][1] += dp[next][0];
            }
        }
    }
}
