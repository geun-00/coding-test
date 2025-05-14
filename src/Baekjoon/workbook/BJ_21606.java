package Baekjoon.workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/21606">백준 21606번 - 아침 산책</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-21606%EB%B2%88-%EC%95%84%EC%B9%A8-%EC%82%B0%EC%B1%85">velog</a>
 * @since 2025-04-29
 */
public class BJ_21606 {
    static char[] arr;
    static List<Integer>[] tree;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        arr = br.readLine().toCharArray();

        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        int ans = 0;

        // 1
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;

            tree[u].add(v);
            tree[v].add(u);

            if (arr[u] == '1' && arr[v] == '1') {
                ans += 2;
            }
        }

        visit = new boolean[n];

        // 2
        for (int i = 0; i < n; i++) {
            if (arr[i] == '0' && !visit[i]) {
                int count = dfs(i);
                ans += count * (count - 1);
            }
        }

        System.out.println(ans);
    }

    // 3
    private static int dfs(int node) {
        visit[node] = true;
        int count = 0;

        for (int next : tree[node]) {
            if (arr[next] == '1') {
                count++;
            } else if (!visit[next]) {
                count += dfs(next);
            }
        }

        return count;
    }
}
