package Baekjoon.tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/17073">백준 17073번 - 트리 : 나무 위의 빗물</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-17073%EB%B2%88-%EB%82%98%EB%AC%B4-%EC%9C%84%EC%9D%98-%EB%B9%97%EB%AC%BC">velog</a>
 * @since 2024-10-23
 */
public class BJ_17073 {

    static int leaves = 0;
    static boolean[] visit;
    static ArrayList<Integer>[] tree;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        visit = new boolean[n + 1];
        tree = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            tree[u].add(v);
            tree[v].add(u);
        }

//        dfs(1); //시간 초과

        for (int i = 2; i < n + 1; i++) {
            if (tree[i].size() == 1) {
                leaves++;
            }
        }

        bw.write(String.format("%.10f", w * 1.0 / leaves));

        bw.close();
        br.close();
    }

    //시간 초과 발생
    private static void dfs(int node) {

        int child = 0;

        visit[node] = true;

        for (int next : tree[node]) {
            if (!visit[next]) {
                child++;
                dfs(next);
            }
        }

        if (child == 0) {
            leaves++;
        }
    }
}
