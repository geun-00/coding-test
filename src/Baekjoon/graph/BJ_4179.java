package Baekjoon.graph;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/4179">백준 4179번 - 그래프 탐색 : 불!</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-4179%EB%B2%88-%EB%B6%88">velog</a>
 * @since 2024-10-17
 */
public class BJ_4179 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        char[][] map = new char[r][c];
        boolean[][] visit = new boolean[r][c]; //0=불, 1=지훈

        Queue<Point> fires = new ArrayDeque<>();
        Queue<Jihun> jihun = new ArrayDeque<>();

        for (int i = 0; i < r; i++) {

            char[] arr = br.readLine().toCharArray();

            for (int j = 0; j < c; j++) {

                map[i][j] = arr[j];

                if (map[i][j] == 'J') {
                    jihun.offer(new Jihun(i, j, 0));
                    visit[i][j] = true;
                }
                else if (map[i][j] == 'F') {
                    fires.offer(new Point(i, j));
                }
            }
        }

        while (!jihun.isEmpty()) {

            //불 먼저 확산
            int fireSize = fires.size();

            for (int i = 0; i < fireSize; i++) {
                Point f = fires.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = f.x + dx[d];
                    int ny = f.y + dy[d];

                    if (nx >= 0 && ny >= 0 && nx < r && ny < c && map[nx][ny] == '.') {
                        map[nx][ny] = 'F';
                        fires.offer(new Point(nx, ny));
                    }
                }
            }

            //지훈 이동
            int jihunSize = jihun.size();

            for (int i = 0; i < jihunSize; i++) {
                Jihun j = jihun.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = j.x + dx[d];
                    int ny = j.y + dy[d];

                    //가장자리 탈출
                    if (nx < 0 || ny < 0 || nx >= r || ny >= c) {
                        System.out.println(j.count + 1);
                        return;
                    }

                    if (map[nx][ny] == '.' && !visit[nx][ny]) {
                        visit[nx][ny] = true;
                        jihun.offer(new Jihun(nx, ny, j.count + 1));
                    }
                }
            }
        }

        System.out.println("IMPOSSIBLE");

    }

    static class Jihun {

        int x, y, count;

        public Jihun(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
