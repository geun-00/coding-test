package Baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/14267">백준 14267번 - 트리 : 회사 문화 1</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-14267%EB%B2%88-%ED%9A%8C%EC%82%AC-%EB%AC%B8%ED%99%94-1">velog</a>
 * @since 2024-08-30
 */
public class BJ_14267 {

    static ArrayList<Integer>[] tree;
    static int[] result;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        tree = new ArrayList[n + 1];
        result = new int[n + 1];
        visit = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            int boss = Integer.parseInt(st.nextToken());
            if (boss > 0) {
                tree[boss].add(i);
            }
        }

        //단순 직속 상사로부터 받는 칭찬 수치 저장
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            result[num] += w;
        }

        //내리 칭찬 수행
        dfs(1);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(result[i]).append(" ");
        }

        System.out.print(sb);
    }

    private static void dfs(int now) {
        visit[now] = true;
        for (int next : tree[now]) {
            if (!visit[next]) {
                result[next] += result[now];
                dfs(next);
            }
        }
    }
}
