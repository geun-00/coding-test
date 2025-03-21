package Baekjoon.workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2931">백준 2931번 - 가스관</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2931%EB%B2%88-%EA%B0%80%EC%8A%A4%EA%B4%80">velog</a>
 * @since 2025-03-16
 */
public class BJ_2931 {
    static int r, c;
    static char[][] map;
    static boolean[][] visit;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        visit = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int[][] pos = new int[2][3];
        int index = 0;

        // 1
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == 'M' || map[i][j] == 'Z') {
                    visit[i][j] = true;

                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if (nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
                        if (map[nx][ny] == '.' || map[nx][ny] == 'M' || map[nx][ny] == 'Z') continue;

                        pos[index] = getEmptyPos(i, j, d);
                        break;
                    }
                    index++;
                }
            }
        }

        int x = pos[0][0];
        int y = pos[0][1];

        // 3
        System.out.println((x + 1) + " " + (y + 1) + " " + getBlock(pos[0][2], ((pos[1][2] + 2) % 4), x, y));
    }

    // 2
    private static int[] getEmptyPos(int x, int y, int dir) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if (map[nx][ny] == '.') {
            return new int[]{nx, ny, dir};
        }

        visit[nx][ny] = true;
        return getEmptyPos(nx, ny, getNextDir(map[nx][ny], dir));
    }

    private static int getNextDir(char c, int dir) {
        //0=좌, 1=상, 2=우, 3=하
        switch (c) {
            case '1':
                if (dir == 1) return 2;
                if (dir == 0) return 3;
                break;
            case '2':
                if (dir == 3) return 2;
                if (dir == 0) return 1;
                break;
            case '3':
                if (dir == 2) return 1;
                if (dir == 3) return 0;
                break;
            case '4':
                if (dir == 2) return 3;
                if (dir == 1) return 0;
                break;
        }

        //블록 |, -, +
        return dir;
    }

    // 4
    private static char getBlock(int dir1, int dir2, int x, int y) {
        //0=좌, 1=상, 2=우, 3=하
        if (dir1 == dir2) {
            if (dir1 == 0 || dir1 == 2) return '-';
            else return '|';
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= r || ny >= c) continue;

            if (map[nx][ny] != '.' && !visit[nx][ny]) {
                return '+';
            }
        }

        if ((dir1 == 0 && dir2 == 3) || (dir1 == 1 && dir2 == 2)) return '1';
        else if ((dir1 == 3 && dir2 == 2) || (dir1 == 0 && dir2 == 1)) return '2';
        else if ((dir1 == 2 && dir2 == 1) || (dir1 == 3 && dir2 == 0)) return '3';
        else if ((dir1 == 2 && dir2 == 3) || (dir1 == 1 && dir2 == 0)) return '4';

        return 'x';
    }
}