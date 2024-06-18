package Baekjoon.graph;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * <a href = "https://www.acmicpc.net/problem/10026">백준 10026번 - 그래프 탐색 : 적록색약</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-10026%EB%B2%88-%EC%A0%81%EB%A1%9D%EC%83%89%EC%95%BD">velog</a>
 * @since 2024-06-18
 */
public class BJ_10026 {

    static char[][] map;
    static int n;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        visit = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();

            for (int j = 0; j < n; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        int no = 0; //적록색약이 아닌 사람이 본 구역의 개수

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visit[i][j]) {
                    bfs(i, j, map[i][j]);
                    no++;
                }
            }
        }

        //적록색약인 사람이 본 구역의 개수를 구하기 위한 작업
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 'G') {
                    map[i][j] = 'R';
                }
            }
        }

        visit = new boolean[n][n];

        int yes = 0; //적록색약인 사람이 본 구역의 개수

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visit[i][j]) {
                    bfs(i, j, map[i][j]);
                    yes++;
                }
            }
        }

        System.out.println(no + " " + yes);
    }

    private static void bfs(int x, int y, char target) {
        Queue<Point> qu = new ArrayDeque<>();
        qu.offer(new Point(x, y));
        visit[x][y] = true;

        while (!qu.isEmpty()) {
            Point now = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n) { //범위 체크
                    if (!visit[nx][ny] && map[nx][ny] == target) { //조건 체크
                        visit[nx][ny] = true;
                        qu.offer(new Point(nx, ny));
                    }
                }
            }
        }
    }
}
