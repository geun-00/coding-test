package Baekjoon.graph;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/7576">백준 7576번 - 그래프 탐색 : 토마토</a>
 *  <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-7576%EB%B2%88-%ED%86%A0%EB%A7%88%ED%86%A0">velog</a>
 * @since 2024-06-14
 */
public class BJ_7576 {

    static int[][] box;
    static boolean[][] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int zero = 0; //익지 않은 토마토 개수
    static ArrayList<Point> tomato = new ArrayList<>(); //익은 토마토 위치 정보
    static int min_day = -1;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        box = new int[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());

                if (box[i][j] == 0) {
                    zero++;
                } else if (box[i][j] == 1) {
                    tomato.add(new Point(i, j));
                }
            }
        }

        if (zero == 0) {
            System.out.println(0);
        } else {
            bfs();
            System.out.println(min_day);
        }
    }

    private static void bfs() {
        Queue<Info> qu = new ArrayDeque<>();

        for (Point p : tomato) {
            qu.offer(new Info(p.x, p.y, 0)); //x, y, 날짜
            visit[p.x][p.y] = true;
        }

        int day = 0;
        while (!qu.isEmpty()) {
            Info now = qu.poll();

            int x = now.x;
            int y = now.y;
            day = now.day;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) { //범위 체크
                    if (!visit[nx][ny] && box[nx][ny] == 0) { //조건 체크
                        zero--;
                        visit[nx][ny] = true;
                        qu.offer(new Info(nx, ny, day + 1));
                    }
                }
            }
        }

        //익지 않은 토마토를 모두 익은 토마토로 만들었을 때만 최소 날짜 갱신
        if (zero == 0) {
            min_day = day;
        }
    }

    static class Info {
        int x, y, day;

        public Info(int x, int y, int day) {
            this.x = x;
            this.y = y;
            this.day = day;
        }
    }
}