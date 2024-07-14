package Baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1707">백준 1707번 - 그래프 탐색 : 이분 그래프</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1707%EB%B2%88-%EC%9D%B4%EB%B6%84-%EA%B7%B8%EB%9E%98%ED%94%84">velog</a>
 * @since 2024-07-13
 */
public class BJ_1707 {

    static ArrayList<Integer>[] graph;
    static int[] colors; //0=미방문, 1=색깔1, -1=색깔2
    static boolean check;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());

        while (k-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            graph = new ArrayList[v + 1];
            colors = new int[v + 1];
            check = false;

            for (int i = 1; i <= v; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());

                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());

                graph[v1].add(v2);
                graph[v2].add(v1);
            }

            //부분 그래프가 있을 수 있기 때문에 반복문으로 구한다.
            for (int i = 1; i <= v; i++) {
                if (check) {
                    break;
                }
                if (colors[i] == 0) {
                    dfs(i, 1);
//                    bfs(i, 1);
                }
            }

            System.out.println(check ? "NO" : "YES");
        }
    }

    private static void dfs(int now, int color) {
        colors[now] = color;

        for (int next : graph[now]) {
            if (colors[next] == color) {
                check = true;
                return;
            }

            if (colors[next] == 0) {
                dfs(next, -color);
            }
        }
    }

    private static void bfs(int now, int color) {
        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(now);
        colors[now] = color;

        while (!qu.isEmpty()) {
            int n = qu.poll();

            for (int next : graph[n]) {
                if (colors[next] == 0) {
                    qu.offer(next);
                    colors[next] = -colors[n];
                } else if (colors[n] == colors[next]) {
                    check = true;
                    return;
                }
            }
        }
    }
}
