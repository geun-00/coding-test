package Baekjoon.implementation;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/16234">백준 16234번 - 구현 : 인구 이동</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-16234%EB%B2%88-%EC%9D%B8%EA%B5%AC-%EC%9D%B4%EB%8F%99">velog</a>
 * @since 2024-07-03
 */
public class BJ_16234 {

    static int[][] land;
    static int n, l, r;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visit;
    static ArrayList<Point> list;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        land = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                land[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int days = 0;

        while (true) {

            visit = new boolean[n][n];
            boolean check = false;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visit[i][j]) {

                        list = new ArrayList<>();
                        int sum = bfs(i, j); //연합 가능한 나라의 총 인구수

                        if (!list.isEmpty()) {

                            //인구 이동이 가능하면 인구 이동을 한다.
                            for (Point p : list) {
                                int temp = sum / list.size();
                                land[p.x][p.y] = temp;
                            }

                            check = true; //한 턴에 한번이라도 인구 이동이 발생하면 다음 턴 가능
                        }
                    }
                }
            }

            if (!check) {
                break;
            }

            days++;
        }

        System.out.println(days);
    }

    private static int bfs(int x, int y) {

        Queue<Point> qu = new ArrayDeque<>();

        qu.offer(new Point(x, y));

        int sum = 0;

        while (!qu.isEmpty()) {
            Point now = qu.poll();

            for (int i = 0; i < 4; i++) {

                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visit[nx][ny]) {

                    int diff = Math.abs(land[now.x][now.y] - land[nx][ny]); //인접한 두 나라의 인구차

                    if (l <= diff && diff <= r) {
                        visit[nx][ny] = true;
                        qu.offer(new Point(nx, ny));
                        list.add(new Point(nx, ny));
                        sum += land[nx][ny];
                    }
                }
            }
        }

        return sum;
    }
}
