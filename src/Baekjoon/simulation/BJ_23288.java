package Baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/23288">백준 23288번 - 시뮬레이션 : 주사위 굴리기 2</a>
 * <br>
 * <a href = "">velog</a>
 * @since 2025-05-05
 */
public class BJ_23288 {

    static final int TOP = 0;
    static final int BOTTOM = 1;
    static final int FRONT = 2;
    static final int RIGHT = 3;
    static final int BACK = 4;
    static final int LEFT = 5;

    static int[][] map, score_board;
    static boolean[][] visit;

    //동, 북, 서, 남
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, -1, 0, 1};

    static int[] dice = {1, 6, 5, 3, 2, 4};
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        score_board = new int[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visit[i][j]) {
                    bfs(i, j);
                }
            }
        }

        int dir = 0;
        int x = 0, y = 0;
        int score = 0;

        for (int i = 0; i < k; i++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                dir = (dir + 2) % 4;
                nx = x + dx[dir];
                ny = y + dy[dir];
            }

            score += score_board[nx][ny];

            rollingDice(dir);

            if (dice[BOTTOM] > map[nx][ny]) dir = (dir + 3) % 4;
            else if (dice[BOTTOM] < map[nx][ny]) dir = (dir - 3 + 4) % 4;

            x = nx;
            y = ny;
        }

        System.out.println(score);
    }

    private static void bfs(int i, int j) {
        visit[i][j] = true;

        Queue<int[]> qu = new ArrayDeque<>();
        Queue<int[]> temp = new ArrayDeque<>();
        qu.offer(new int[]{i, j});
        temp.offer(new int[]{i, j});

        int num = map[i][j];
        int count = 1;

        while (!qu.isEmpty()) {
            int[] now = qu.poll();
            int x = now[0];
            int y = now[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (visit[nx][ny] || map[nx][ny] != num) continue;

                visit[nx][ny] = true;
                qu.offer(new int[]{nx, ny});
                temp.offer(new int[]{nx, ny});
                count++;
            }
        }

        while (!temp.isEmpty()) {
            int[] now = temp.poll();
            int x = now[0];
            int y = now[1];

            score_board[x][y] = num * count;
        }
    }

    private static void rollingDice(int dir) {
        int top = dice[TOP];
        int bottom = dice[BOTTOM];
        int right = dice[RIGHT];
        int left = dice[LEFT];
        int front = dice[FRONT];
        int back = dice[BACK];

        //동
        if (dir == 0) {
            dice[TOP] = left;
            dice[BOTTOM] = right;
            dice[RIGHT] = top;
            dice[LEFT] = bottom;
        }
        //북
        else if (dir == 1) {
            dice[TOP] = front;
            dice[BOTTOM] = back;
            dice[FRONT] = bottom;
            dice[BACK] = top;
        }
        //서
        else if (dir == 2) {
            dice[TOP] = right;
            dice[BOTTOM] = left;
            dice[RIGHT] = bottom;
            dice[LEFT] = top;
        }
        //남
        else if (dir == 3) {
            dice[TOP] = back;
            dice[BOTTOM] = front;
            dice[FRONT] = top;
            dice[BACK] = bottom;
        }
    }
}
