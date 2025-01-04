package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/9328">백준 9328번 - 구현 : 열쇠</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-9328%EB%B2%88-%EC%97%B4%EC%87%A0">velog</a>
 *
 * @since 2024-12-18
 */
public class BJ_9328 {

    static final char EMPTY = '.';
    static final char WALL = '*';
    static final char DOCUMENT = '$';

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int h, w;

    static char[][] map;
    static boolean[] isGetKey;
    static ArrayList<Point>[] lazy;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            h = Integer.parseInt(st.nextToken()) + 2;
            w = Integer.parseInt(st.nextToken()) + 2;

            map = new char[h][w];
            isGetKey = new boolean[26];
            lazy = new ArrayList[26];

            for (int i = 0; i < lazy.length; i++) {
                lazy[i] = new ArrayList<>();
            }

            for (char[] row : map) {
                Arrays.fill(row, EMPTY);
            }

            for (int i = 1; i <= h - 2; i++) {
                char[] arr = br.readLine().toCharArray();
                for (int j = 1; j <= w - 2; j++) {
                    map[i][j] = arr[j - 1];
                }
            }

            char[] keys = br.readLine().toCharArray();

            int index = 0;
            while (index < keys.length && keys[index] != '0') {
                isGetKey[keys[index++] - 'a'] = true;
            }

            sb.append(bfs()).append("\n");
        }

        System.out.print(sb);
    }

    private static int bfs() {

        Queue<Point> qu = new ArrayDeque<>();
        qu.offer(new Point(0, 0));

        boolean[][] visit = new boolean[h][w];
        visit[0][0] = true;

        int count = 0;

        while (!qu.isEmpty()) {

            Point now = qu.poll();
            int x = now.x;
            int y = now.y;

            for (int i = 0; i < 4; i++) {

                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= h || ny >= w || visit[nx][ny] || map[nx][ny] == WALL) {
                    continue;
                }

                //다음 칸
                char ch = map[nx][ny];

                //다음 칸이 문서
                if (ch == DOCUMENT) {
                    count++;
                    map[nx][ny] = EMPTY;
                }

                //다음 칸이 대문자(=문)
                if (Character.isUpperCase(ch)) {
                    if (isGetKey[ch - 'A']) {
                        map[nx][ny] = EMPTY;
                    } else {
                        //지연 처리를 위해 저장
                        lazy[ch - 'A'].add(new Point(nx, ny));
                        continue;
                    }
                }

                //다음 칸이 소문자(=열쇠)
                if (Character.isLowerCase(ch)) {

                    map[nx][ny] = EMPTY;
                    isGetKey[ch - 'a'] = true;

                    //지연 처리
                    for (Point p : lazy[ch - 'a']) {
                        visit[p.x][p.y] = true;
                        qu.offer(new Point(p.x, p.y));
                    }
                }

                visit[nx][ny] = true;
                qu.offer(new Point(nx, ny));
            }
        }

        return count;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}