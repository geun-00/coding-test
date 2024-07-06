package Baekjoon.graph;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1261">백준 1261번 - 그래프 탐색 : 알고스팟</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1261%EB%B2%88-%EC%95%8C%EA%B3%A0%EC%8A%A4%ED%8C%9F">velog</a>
 * @since 2024-07-04
 */
public class BJ_1261 {

    static int[][] map;
    static int n, m;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(s.substring(j, j + 1));
            }
        }

        bfs();
    }

    private static void bfs() {

        Deque<Point> qu = new ArrayDeque<>();
        boolean[][] visit = new boolean[n][m];

        qu.offerFirst(new Point(0, 0));
        visit[0][0] = true;

        int[][] dist = new int[n][m];

        while (!qu.isEmpty()) {
            Point now = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visit[nx][ny]) {

                    if (map[nx][ny] == 0) {
                        dist[nx][ny] = dist[now.x][now.y];
                        qu.offerFirst(new Point(nx, ny));
                    }
                    else if (map[nx][ny] == 1) {
                        dist[nx][ny] = dist[now.x][now.y] + 1;
                        qu.offerLast(new Point(nx, ny));
                    }

                    visit[nx][ny] = true;
                }
            }
        }

        System.out.println(dist[n - 1][m - 1]);
    }
}
/*
    private static void bfs() {

        Queue<Point> qu = new PriorityQueue<>();
        boolean[][] visit = new boolean[n][m];

        qu.offer(new Point(0, 0, 0));
        visit[0][0] = true;

        while (!qu.isEmpty()) {
            Point now = qu.poll();

            if (now.x == n - 1 && now.y == m - 1) {
                System.out.println(now.count);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visit[nx][ny]) {

                    if (map[nx][ny] == 0) { //다음 칸이 빈칸
                        qu.offer(new Point(nx, ny, now.count));
                    }
                    else { //다음 칸이 벽
                        qu.offer(new Point(nx, ny, now.count + 1));
                        map[nx][ny] = 0;
                    }

                    visit[nx][ny] = true;
                }
            }
        }
    }

    static class Point implements Comparable<Point> {
        int x, y, count;

        public Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public int compareTo(Point o) {
            return this.count - o.count;
        }
    }
*/