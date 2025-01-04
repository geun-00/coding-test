package Baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/17837">백준 17837번 - 시뮬레이션 : 새로운 게임 2</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-17837%EB%B2%88-%EC%83%88%EB%A1%9C%EC%9A%B4-%EA%B2%8C%EC%9E%84-2">velog</a>
 *
 * @since 2024-12-27
 */
public class BJ_17837 {

    static final int RED = 1;
    static final int BLUE = 2;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static int n;
    static int[][] board;
    static Unit[] unitArr;
    static Deque<Integer>[][] unitList;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        board = new int[n][n];
        unitList = new ArrayDeque[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                unitList[i][j] = new ArrayDeque<>();
            }
        }

        unitArr = new Unit[k];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;

            unitArr[i] = new Unit(x, y, d);
            unitList[x][y].offer(i);
        }

        int turns = 0;

        while (turns <= 1000) {

            turns++;

            for (int i = 0; i < k; i++) {
                if (solve(unitArr[i], i)) {
                    System.out.println(turns);
                    return;
                }
            }
        }

        System.out.println(-1);
    }

    private static boolean solve(Unit u, int unitIndex) {

        int x = u.x;
        int y = u.y;
        int dir = u.dir;

        int nx = x + dx[dir];
        int ny = y + dy[dir];

        Deque<Integer> target = getTarget(unitIndex, x, y);

        if (isBlocked(nx, ny)) {

            dir = (dir % 2 == 0) ? dir + 1 : dir - 1;
            nx = x + dx[dir];
            ny = y + dy[dir];
            unitArr[unitIndex].setDir(dir);

            if (isBlocked(nx, ny)) {
                return horsesMove(target, x, y, false);
            }
        }

        return horsesMove(target, nx, ny, board[nx][ny] == RED);
    }

    private static Deque<Integer> getTarget(int unitIndex, int x, int y) {

        Deque<Integer> units = unitList[x][y];
        Deque<Integer> target = new ArrayDeque<>();

        while (!units.isEmpty() && units.peek() != unitIndex) {
            target.add(units.pop());
        }
        target.add(units.pop());

        return target;
    }

    private static boolean horsesMove(Deque<Integer> target, int nx, int ny, boolean isRed) {

        while (!target.isEmpty()) {
            int targetIdx = (isRed) ? target.pollFirst() : target.pollLast();
            unitArr[targetIdx].move(nx, ny);
            unitList[nx][ny].push(targetIdx);
        }

        return unitList[nx][ny].size() >= 4;
    }

    private static boolean isBlocked(int x, int y) {
        return x < 0 || y < 0 || x >= n || y >= n || board[x][y] == BLUE;
    }

    static class Unit {

        int x, y, dir;

        public Unit(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        public void move(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void setDir(int dir) {
            this.dir = dir;
        }
    }
}