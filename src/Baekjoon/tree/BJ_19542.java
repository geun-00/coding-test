package Baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/19542">백준 19542번 - 트리 : 전단지 돌리기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-19542%EB%B2%88-%EC%A0%84%EB%8B%A8%EC%A7%80-%EB%8F%8C%EB%A6%AC%EA%B8%B0">velog</a>
 * @since 2024-12-09
 */
public class BJ_19542 {

    static List<Integer>[] tree;
    static int ans = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken()) - 1;   //0-index
        int d = Integer.parseInt(st.nextToken());

        tree = new List[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;   //0-index
            int y = Integer.parseInt(st.nextToken()) - 1;   //0-index

            tree[x].add(y);
            tree[y].add(x);
        }

        dfs(s, -1, d);

        System.out.println(ans);
    }

    private static int dfs(int node, int parent, int d) {

        int maxDepth = 0;

        for (int next : tree[node]) {

            if (next != parent) {

                int childDepth = dfs(next, node, d) + 1;

                if (childDepth > d) {
                    ans += 2;
                }

                maxDepth = Math.max(maxDepth, childDepth);
            }
        }

        return maxDepth;
    }
}