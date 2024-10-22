import java.util.*;
import java.io.*;

class Solution
{
    static int h, w;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int dir;
    static int x, y;
    
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            map = new char[h][w];

            for (int i = 0; i < h; i++) {
                char[] arr = br.readLine().toCharArray();
                for (int j = 0; j < w; j++) {

                    map[i][j] = arr[j];

                    switch (map[i][j]) {
                        case '^':
                            dir = 0;
                            x = i; y = j;
                            break;
                        case 'v':
                            dir = 1;
                            x = i; y = j;
                            break;
                        case '<':
                            dir = 2;
                            x = i; y = j;
                            break;
                        case '>':
                            dir = 3;
                            x = i; y = j;
                            break;
                    }
                }
            }

            int n = Integer.parseInt(br.readLine());

            char[] arr = br.readLine().toCharArray();

            for (char c : arr) {
                switch (c) {
                    case 'U':
                        up();
                        break;
                    case 'D':
                        down();
                        break;
                    case 'L':
                        left();
                        break;
                    case 'R':
                        right();
                        break;
                    case 'S':
                        shoot();
                        break;
                }
            }
            System.out.print("#" + tc + " ");

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
        }
	}
    
    static void shoot() {

        int tx = x + dx[dir];
        int ty = y + dy[dir];

        while (tx >= 0 && ty >= 0 && tx < h && ty < w) {

            if (map[tx][ty] == '*') {
                map[tx][ty] = '.';
                break;
            }

            if (map[tx][ty] == '#') {
                break;
            }

            tx += dx[dir];
            ty += dy[dir];
        }
    }

    static void up() {

        dir = 0;
        map[x][y] = '^';
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if (nx < 0 || ny < 0 || nx >= h || ny >= w) {
            return;
        }

        if (map[nx][ny] == '.') {
            map[x][y] = '.';
            map[nx][ny] = '^';
            x = nx;
            y = ny;
        }
    }

    static void down() {

        dir = 1;
        map[x][y] = 'v';
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if (nx < 0 || ny < 0 || nx >= h || ny >= w) {
            return;
        }

        if (map[nx][ny] == '.') {
            map[x][y] = '.';
            map[nx][ny] = 'v';
            x = nx;
            y = ny;
        }
    }

    static void left() {

        dir = 2;
        map[x][y] = '<';
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if (nx < 0 || ny < 0 || nx >= h || ny >= w) {
            return;
        }

        if (map[nx][ny] == '.') {
            map[x][y] = '.';
            map[nx][ny] = '<';
            x = nx;
            y = ny;
        }
    }

    static void right() {

        dir = 3;
        map[x][y] = '>';
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if (nx < 0 || ny < 0 || nx >= h || ny >= w) {
            return;
        }

        if (map[nx][ny] == '.') {
            map[x][y] = '.';
            map[nx][ny] = '>';
            x = nx;
            y = ny;
        }
    }
}