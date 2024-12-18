package Baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/6593">백준 6593번 - 그래프 탐색 : 상범 빌딩</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-6593%EB%B2%88-%EC%83%81%EB%B2%94-%EB%B9%8C%EB%94%A9">velog</a>
 * @since 2024-12-08
 */
public class BJ_6593 {

    static boolean[][][] visit;
    static char[][][] arr;
    static int[] dz = {-1, 1, 0, 0, 0, 0};
    static int[] dx = {0, 0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, 0, -1, 1};
    static int L, R, C;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        while (true) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if (L == 0 && R == 0 && C == 0) break;

            arr = new char[L][R][C];
            visit = new boolean[L][R][C];

            Point start = null;

            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {

                    arr[i][j] = br.readLine().toCharArray();

                    for (int k = 0; k < C; k++) {
                        if (arr[i][j][k] == 'S') {
                            start = new Point(i, j, k);
                        }
                    }
                }

                br.readLine();  //각 층 사이의 빈 줄
            }

            int x = bfs(start);

            if (x != -1) {
                sb.append(String.format("Escaped in %d minute(s).", x));
            } else {
                sb.append("Trapped!");
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static int bfs(Point start) {

        Queue<Point> qu = new ArrayDeque<>();
        qu.offer(start);

        visit[start.z][start.x][start.y] = true;

        int time = 0;

        while (!qu.isEmpty()) {

            int size = qu.size();

            for (int i = 0; i < size; i++) {

                Point now = qu.poll();

                int z = now.z;
                int x = now.x;
                int y = now.y;

                if (arr[z][x][y] == 'E') return time;

                for (int d = 0; d < 6; d++) {

                    int nz = z + dz[d];
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nz < 0 || nx < 0 || ny < 0 || nz >= L || nx >= R || ny >= C) continue;
                    if (visit[nz][nx][ny] || arr[nz][nx][ny] == '#') continue;

                    visit[nz][nx][ny] = true;
                    qu.offer(new Point(nz, nx, ny));
                }
            }

            time++;
        }

        return -1;
    }

    static class Point {
        int z, x, y;

        public Point(int z, int x, int y) {
            this.z = z;
            this.x = x;
            this.y = y;
        }
    }
}