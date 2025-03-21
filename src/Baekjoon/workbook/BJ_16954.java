package Baekjoon.workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * <a href = "https://www.acmicpc.net/problem/16954">백준 16954번 - 움직이는 미로 탈출</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-16954%EB%B2%88-%EC%9B%80%EC%A7%81%EC%9D%B4%EB%8A%94-%EB%AF%B8%EB%A1%9C-%ED%83%88%EC%B6%9C">velog</a>
 * @since 2025-03-17
 */
public class BJ_16954 {

    static final int R = 8, C = 8;
    static final int[] dx = {0, -1, 1, 0, 0, -1, 1, 1, -1};
    static final int[] dy = {0, 0, 0, -1, 1, 1, 1, -1, -1};
    static char[][] chess;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        chess = new char[R][C];

        for (int i = 0; i < R; i++) {
            chess[i] = br.readLine().toCharArray();
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{R - 1, 0}); // 1

        while (!qu.isEmpty()) {
            // 2
            boolean[][] visit = new boolean[R][C];
            int size = qu.size();

            for (int i = 0; i < size; i++) {
                int[] now = qu.poll();
                int x = now[0], y = now[1];

                // 3
                if (chess[x][y] == '#') continue;
                if (x == 0 && y == C - 1) return 1;

                // 4
                for (int d = 0; d < dx.length; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                    if (visit[nx][ny] || chess[nx][ny] == '#') continue;

                    visit[nx][ny] = true;
                    qu.offer(new int[]{nx, ny});
                }
            }

            move(); // 5
        }

        return 0;
    }

    private static void move() {
        for (int i = R - 1; i > 0; i--) {
            for (int j = 0; j < C; j++) {
                chess[i][j] = chess[i - 1][j];
            }
        }
        for (int i = 0; i < C; i++) {
            chess[0][i] = '.';
        }
    }
}
