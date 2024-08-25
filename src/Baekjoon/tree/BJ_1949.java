package Baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1949">백준 1949번 - 트리 : 우수 마을</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1949%EB%B2%88-%EC%9A%B0%EC%88%98-%EB%A7%88%EC%9D%84">velog</a>
 * @since 2024-08-21
 */
public class BJ_1949 {

    static ArrayList<Integer>[] tree;
    static int[] nums;
    static boolean[] visit;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n + 1];
        nums = new int[n + 1];
        visit = new boolean[n + 1];
        dp = new int[n + 1][2];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
            nums[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        dfs(1);

        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    private static void dfs(int node) {

        visit[node] = true;

        dp[node][0] = 0;            //현재 노드가 우수 마을이 아닌 경우
        dp[node][1] = nums[node];   //현재 노드가 우수 마을인 경우

        for (int to : tree[node]) {
            if (!visit[to]) {

                dfs(to); //재귀

                //현재 마을이 우수 마을이 아닌 경우, 연결된 마을은 우수 마을이어도 되고 아니어도 된다.
                dp[node][0] += Math.max(dp[to][0], dp[to][1]);

                //현재 마을이 우수 마을인 경우, 연결된 마을은 우수 마을이 아니어야 한다.
                dp[node][1] += dp[to][0];
            }
        }
    }
}