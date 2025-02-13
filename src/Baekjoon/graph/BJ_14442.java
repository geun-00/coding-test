package Baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/14442">백준 14442번 - 그래프 탐색 : 벽 부수고 이동하기 2</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-14442%EB%B2%88-%EB%B2%BD-%EB%B6%80%EC%88%98%EA%B3%A0-%EC%9D%B4%EB%8F%99%ED%95%98%EA%B8%B0-2">velog</a>
 * @since 2025-01-30
 */
public class BJ_14442 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        char[][] map = new char[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        Queue<State> qu = new ArrayDeque<>();
        qu.offer(new State(0, 0, 1, 0));

        boolean[][][] visit = new boolean[n][m][k + 1];
        visit[0][0][0] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!qu.isEmpty()) {

            State s = qu.poll();

            int x = s.x;
            int y = s.y;
            int move = s.move;
            int wall = s.wall;

            if (x == n - 1 && y == m - 1) {
                System.out.println(move);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }

                if (map[nx][ny] == '0') {
                    if (!visit[nx][ny][wall]) {
                        visit[nx][ny][wall] = true;
                        qu.offer(new State(nx, ny, move + 1, wall));
                    }
                }
                else {
                    if (wall < k && !visit[nx][ny][wall + 1]) {
                        visit[nx][ny][wall + 1] = true;
                        qu.offer(new State(nx, ny, move + 1, wall + 1));
                    }
                }
            }
        }

        System.out.println(-1);
    }

    static class State {

        int x, y;
        int move;
        int wall;

        public State(int x, int y, int move, int wall) {
            this.x = x;
            this.y = y;
            this.move = move;
            this.wall = wall;
        }
    }
}
