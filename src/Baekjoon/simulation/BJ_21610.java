package Baekjoon.simulation;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/21610">백준 21610번 - 시뮬레이션 : 마법사 상어와 비바라기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-21610%EB%B2%88-%EB%A7%88%EB%B2%95%EC%82%AC-%EC%83%81%EC%96%B4%EC%99%80-%EB%B9%84%EB%B0%94%EB%9D%BC%EA%B8%B0">velog</a>
 *
 * @since 2024-10-28
 */
public class BJ_21610 {

    static int n;
    static int[][] map;
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static Queue<Point> cloud = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cloud.offer(new Point(n - 1, 0));
        cloud.offer(new Point(n - 1, 1));
        cloud.offer(new Point(n - 2, 0));
        cloud.offer(new Point(n - 2, 1));

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            boolean[][] visit = new boolean[n][n];

            //구름 d 방향으로 s 만큼 이동
            move(d, s, visit);

            //각 구름에서 비 내리기
            rained();

            //물복사버그
            copyBug();

            //새로운 구름 저장
            getNewCloud(visit);
        }

        int sum = getSum();

        System.out.println(sum);
    }

    private static void move(int d, int s, boolean[][] visit) {

        int size = cloud.size();

        for (int j = 0; j < size; j++) {

            Point c = cloud.poll();

            int nx = (c.x + dx[d] * (s % n) + n) % n;
            int ny = (c.y + dy[d] * (s % n) + n) % n;

            visit[nx][ny] = true;
            cloud.offer(new Point(nx, ny));
        }
    }

    private static void rained() {

        for (Point c : cloud) {
            map[c.x][c.y] += 1;
        }
    }

    private static void copyBug() {

        while (!cloud.isEmpty()) {

            Point c = cloud.poll();

            int count = 0;

            for (int j = 1; j < 8; j += 2) {

                int nx = c.x + dx[j];
                int ny = c.y + dy[j];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;

                if (map[nx][ny] > 0) count++;
            }

            map[c.x][c.y] += count;
        }
    }

    private static void getNewCloud(boolean[][] visit) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visit[i][j] && map[i][j] >= 2) {
                    map[i][j] -= 2;
                    cloud.offer(new Point(i, j));
                }
            }
        }
    }

    private static int getSum() {

        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += map[i][j];
            }
        }
        return sum;
    }
}
