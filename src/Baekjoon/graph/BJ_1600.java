package Baekjoon.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1600">백준 1600번 - 그래프 탐색 : 말이 되고픈 원숭이</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1600%EB%B2%88-%EB%A7%90%EC%9D%B4-%EB%90%98%EA%B3%A0%ED%94%88-%EC%9B%90%EC%88%AD%EC%9D%B4">velog</a>
 * @since 2024-10-22
 */
public class BJ_1600 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int k = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[][] map = new int[h][w];

        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Point> qu = new ArrayDeque<>();
        qu.offer(new Point(0, 0, 0, 0));

        boolean[][][] visit = new boolean[h][w][k + 1];
        visit[0][0][0] = true;

        //원숭이 움직임
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        //말 움직임
        int[] hx = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] hy = {1, 2, 2, 1, -1, -2, -2, -1};

        while (!qu.isEmpty()) {

            Point now = qu.poll();

            if (now.x == h - 1 && now.y == w - 1) {
                bw.write(String.valueOf(now.count));
                bw.flush();
                return;
            }

            if (now.k < k) {
                for (int i = 0; i < 8; i++) {
                    int nx = now.x + hx[i];
                    int ny = now.y + hy[i];

                    if (nx >= 0 && ny >= 0 && nx < h && ny < w) {
                        if (!visit[nx][ny][now.k + 1] && map[nx][ny] == 0) {
                            qu.offer(new Point(nx, ny, now.k + 1, now.count + 1));
                            visit[nx][ny][now.k + 1] = true;
                        }
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < h && ny < w) {
                    if (!visit[nx][ny][now.k] && map[nx][ny] == 0) {
                        qu.offer(new Point(nx, ny, now.k, now.count + 1));
                        visit[nx][ny][now.k] = true;
                    }
                }
            }
        }

        bw.write(String.valueOf(-1));
        bw.flush();

        bw.close();
        br.close();
    }

    static class Point {

        int x, y;
        int k, count;

        public Point(int x, int y, int k, int count) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.count = count;
        }
    }
}
