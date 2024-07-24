package Baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href = "https://www.acmicpc.net/problem/2665">백준 2665번 - 그래프 탐색 : 미로만들기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2665%EB%B2%88-%EB%AF%B8%EB%A1%9C%EB%A7%8C%EB%93%A4%EA%B8%B0">velog</a>
 * @since 2024-07-22
 */
public class BJ_2665 {

    static char[][] map;
    static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        map = new char[n][n];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Deque<Point> qu = new ArrayDeque<>();
        boolean[][] visit = new boolean[n][n];

        qu.offer(new Point(0, 0, 0));
        visit[0][0] = true;

        while (!qu.isEmpty()) {
            Point now = qu.poll();

            if (now.x == n - 1 && now.y == n - 1) {
                return now.count;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visit[nx][ny]) {
                    if (map[nx][ny] == '0') {
                        qu.offerLast(new Point(nx, ny, now.count + 1));
                    } else {
                        qu.offerFirst(new Point(nx, ny, now.count));
                    }
                    visit[nx][ny] = true;
                }
            }
        }

        return -1;
    }

    static class Point {
        int x, y, count;

        public Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}