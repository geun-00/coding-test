package Baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1613">백준 1613번 - 다익스트라 : 역사</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1613%EB%B2%88-%EC%97%AD%EC%82%AC">velog</a>
 * @since 2025-01-15
 */
public class BJ_1613 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        final int inf = 100_000;
        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) dist[i][j] = inf;
            }
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            dist[a][b] = 1;
        }

        for (int p = 0; p < n; p++) {
            for (int s = 0; s < n; s++) {
                for (int e = 0; e < n; e++) {
                    dist[s][e] = Math.min(dist[s][e], dist[s][p] + dist[p][e]);
                }
            }
        }

        int s = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            int ans = 0;

            if (dist[a][b] < dist[b][a]) {
                ans = -1;
            }
            else if (dist[a][b] > dist[b][a]) {
                ans = 1;
            }

            sb.append(ans).append("\n");
        }

        System.out.print(sb);
    }
}