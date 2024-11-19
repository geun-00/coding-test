package Baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1486">백준 1486번 - 다익스트라 : 등산</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1486%EB%B2%88-%EB%93%B1%EC%82%B0">velog</a>
 * @since 2024-11-18
 */
public class BJ_1486 {

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        int[][] dist = new int[n * m][n * m];

        for (int i = 0; i < n; i++) {

            char[] arr = br.readLine().toCharArray();

            for (int j = 0; j < m; j++) {
                char c = arr[j];

                if ('a' <= c && c <= 'z') {
                    map[i][j] = c - 'a' + 26;
                }

                if ('A' <= c && c <= 'Z') {
                    map[i][j] = c - 'A';
                }
            }
        }

        for (int i = 0; i < n * m; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        //상하좌우 연결 가능한 곳 연결
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {

                int now = x * m + y;

                for (int i = 0; i < 4; i++) {

                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                        continue;
                    }

                    int next = nx * m + ny;
                    int diff = Math.abs(map[x][y] - map[nx][ny]);

                    if (diff <= t) {
                        if (map[x][y] >= map[nx][ny]) {
                            dist[now][next] = 1;
                        } else {
                            dist[now][next] = diff * diff;
                        }
                    }
                }
            }
        }

        //최단 거리 구하기
        for (int k = 0; k < n * m; k++) {
            for (int s = 0; s < n * m; s++) {
                for (int e = 0; e < n * m; e++) {
                    if (dist[s][k] != INF && dist[k][e] != INF) {
                        dist[s][e] = Math.min(dist[s][e], dist[s][k] + dist[k][e]);
                    }
                }
            }
        }

        int max = map[0][0];

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {

                int target = x * m + y;

                if (dist[0][target] == INF || dist[target][0] == INF) {
                    continue;
                }

                if (dist[0][target] + dist[target][0] <= d) {
                    max = Math.max(max, map[x][y]);
                }
            }
        }

        System.out.println(max);
    }
}