package Baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1761">백준 1761번 - 트리 : 정점들의 거리</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1761%EB%B2%88-%EC%A0%95%EC%A0%90%EB%93%A4%EC%9D%98-%EA%B1%B0%EB%A6%AC">velog</a>
 * @since 2024-11-20
 */
public class BJ_1761 {

    static int[] dist;          //루트에서 각 노드까지 거리
    static int[] depth;         //각 노드의 깊이
    static int[][] parent;      //각 노드의 2^0 ~ 2^k 번째 부모
    static boolean[] visit;
    static ArrayList<Pair>[] tree;

    static int k;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        k = 1;

        while ((1 << k) < n) {
            k++;
        }

        dist = new int[n];
        depth = new int[n];
        parent = new int[k][n];
        visit = new boolean[n];
        tree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            tree[a].add(new Pair(b, c));
            tree[b].add(new Pair(a, c));
        }

        //각 노드의 깊이와 2^0번째 부모, 루트 노드로부터 거리 구하기(초깃값)
        bfs();

        //2^1 ~ 2^k 번째 부모 구하기
        for (int i = 1; i < k; i++) {
            for (int j = 0; j < n; j++) {
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
            }
        }

        int m = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            //루트에서 a와 b까지 거리를 더한 후 중복되는 경로 고려해 제거하기
            sb.append(dist[a] + dist[b] - (2 * dist[getLCA(a, b)])).append("\n");
        }

        System.out.print(sb);
    }

    private static void bfs() {

        ArrayDeque<Integer> qu = new ArrayDeque<>();
        qu.offer(0);

        visit[0] = true;

        while (!qu.isEmpty()) {

            int now = qu.poll();

            for (Pair p : tree[now]) {

                int next = p.idx;

                if (!visit[next]) {

                    visit[next] = true;
                    qu.offer(next);

                    parent[0][next] = now;
                    depth[next] = depth[now] + 1;
                    dist[next] = dist[now] + p.val;
                }
            }
        }
    }

    private static int getLCA(int a, int b) {

        //무조건 b 노드의 깊이가 깊게 맞추기
        if (depth[a] > depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        for (int i = k - 1; i >= 0; i--) {

            if ((1 << i) <= depth[b] - depth[a]) {
                b = parent[i][b];
            }
        }

        for (int i = k - 1; i >= 0; i--) {

            if (a == b) break;

            if (parent[i][a] != parent[i][b]) {
                a = parent[i][a];
                b = parent[i][b];
            }
        }

        return a != b ? parent[0][a] : a;
    }

    static class Pair {

        int idx, val;

        public Pair(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }
}