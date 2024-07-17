package Baekjoon.implementation;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/13460">백준 13460번 - 구현 : 구슬 탈출 2</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-13460%EB%B2%88-%EA%B5%AC%EC%8A%AC-%ED%83%88%EC%B6%9C-2">velog</a>
 * @since 2024-07-16
 */
public class BJ_13460 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[][] map;
    static boolean[][][][] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Point red = null;
        Point blue = null;

        map = new char[n][m];
        visit = new boolean[n][m][n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {

                map[i][j] = s.charAt(j);

                if (map[i][j] == 'R') {
                    red = new Point(i, j); //처음 빨간 구슬의 위치
                } else if (map[i][j] == 'B') {
                    blue = new Point(i, j); //처음 파란 구슬의 위치
                }
            }
        }

        System.out.println(solve(red, blue));
    }

    private static int solve(Point r, Point b) {

        Queue<State> qu = new ArrayDeque<>();

        qu.offer(new State(r.x, r.y, b.x, b.y, 0));
        visit[r.x][r.y][b.x][b.y] = true;

        while (!qu.isEmpty()) {

            State now = qu.poll();

            //10번 이하로 움직여서 빨간 구슬을 빼낼 수 없다.
            if (now.count >= 10) {
                return -1;
            }

            for (int i = 0; i < 4; i++) {

                int rx = now.rx;
                int ry = now.ry;
                int bx = now.bx;
                int by = now.by;

                //빨간 구슬 기울이기
                while (map[rx + dx[i]][ry + dy[i]] != '#' && map[rx][ry] != 'O') {
                    rx += dx[i];
                    ry += dy[i];
                }

                //파란 구슬 기울이기
                while (map[bx + dx[i]][by + dy[i]] != '#' && map[bx][by] != 'O') {
                    bx += dx[i];
                    by += dy[i];
                }

                //두 구슬이 동시에 구멍에 빠지면 실패
                if (map[bx][by] == 'O' && map[rx][ry] == 'O') {
                    continue;
                }

                //빨간 구슬이 구멍에 도착한 경우
                //지금까지 이동한 횟수와 지금 이동한 1번을 더해서 반환한다.
                if (map[rx][ry] == 'O') {
                    return now.count + 1;
                }

                //두 구슬은 같은 위치에 있을 수 없다.
                //두 구슬이 같은 위치에 도착했다면 각 구슬의 이동 거리를 계산하여
                //늦게 도착한 구슬이 먼저 도착한 구슬의 앞에 위치하도록 한다.
                if (rx == bx && ry == by) {

                    //빨간 구슬 이동 거리
                    int moveRed = Math.abs(rx - now.rx) + Math.abs(ry - now.ry);
                    //파란 구슬 이동 거리
                    int moveBlue = Math.abs(bx - now.bx) + Math.abs(by - now.by);

                    if (moveRed > moveBlue) { //파란 구슬이 먼저 도착한 경우
                        rx -= dx[i];
                        ry -= dy[i];
                    } else {                  //빨간 구슬이 먼저 도착한 경우
                        bx -= dx[i];
                        by -= dy[i];
                    }
                }

                if (!visit[rx][ry][bx][by]) {
                    visit[rx][ry][bx][by] = true;
                    qu.offer(new State(rx, ry, bx, by, now.count + 1));
                }
            }
        }

        return -1;
    }

    static class State {
        int rx, ry;
        int bx, by;
        int count;

        public State(int rx, int ry, int bx, int by, int count) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.count = count;
        }
    }
}
