package Baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/7569">백준 7569번 - 그래프 탐색 : 토마토</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-7569%EB%B2%88-%ED%86%A0%EB%A7%88%ED%86%A0">velog</a>
 * @since 2024-06-23
 */
public class BJ_7569 {

    static int m, n, h;
    static int[][][] map;

    static int[] dx = {-1, 1, 0, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};
    static ArrayList<Point> tomatoes = new ArrayList<>(); //익은 토마토 위치 리스트

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new int[h][n][m];

        int zero = 0; //익지 않은 토마토 개수

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < m; k++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                    if (map[i][j][k] == 0) {
                        zero++;
                    } else if (map[i][j][k] == 1) {
                        tomatoes.add(new Point(i, j, k));
                    }
                }
            }
        }

        if (zero == 0) { //저장될 때부터 모든 토마토가 익어있는 상태
            System.out.println(0);
        } else {
            System.out.println(bfs(zero));
        }
    }

    private static int bfs(int zero) {

        Queue<Point> qu = new ArrayDeque<>();
        boolean[][][] visit = new boolean[h][n][m];

        for (Point t : tomatoes) {
            qu.offer(new Point(t.z, t.x, t.y));
            visit[t.z][t.x][t.y] = true;
        }

        int days = 0;

        while (!qu.isEmpty()) {
            days++;

            int size = qu.size();

            for (int i = 0; i < size; i++) {
                Point now = qu.poll();

                for (int j = 0; j < 6; j++) {
                    int nz = now.z + dz[j];
                    int nx = now.x + dx[j];
                    int ny = now.y + dy[j];

                    if (nz >= 0 && nz < h && nx >= 0 && nx < n && ny >= 0 && ny < m) { //범위 체크
                        if (!visit[nz][nx][ny] && map[nz][nx][ny] == 0) { //조건 체크

                            visit[nz][nx][ny] = true;
                            map[nz][nx][ny] = 1;
                            zero--;

                            qu.offer(new Point(nz, nx, ny));
                        }
                    }
                }
            }

        }

        return zero == 0 ? days - 1 : -1;
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