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
 * <a href = "https://www.acmicpc.net/problem/14502">백준 14502번 - 구현 : 연구소</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-14502%EB%B2%88-%EC%97%B0%EA%B5%AC%EC%86%8C">velog</a>
 * @since 2024-06-15
 */
public class BJ_14502 {

    static int[][] map;
    static int n, m;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Point> virus = new ArrayList<>();
    static ArrayList<Point> zero = new ArrayList<>();
    static int[] wall = new int[3];
    static int zero_count = 0;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 2) {
                    virus.add(new Point(i, j));
                } else if (map[i][j] == 0) {
                    zero.add(new Point(i, j));
                    zero_count++;
                }
            }
        }

        setWall(0, 0);

        System.out.println(max);
    }

    private static void setWall(int depth, int start) { //조합을 통해 3개의 벽을 세우는 모든 경우의 수 확인

        if (depth == 3) { //3개의 벽을 세우면 bfs 실행 후 최대 크기 갱신하고 리턴
            bfs(getNewMap());
            return;
        }

        for (int i = start; i < zero.size(); i++) {
            wall[depth] = i;
            setWall(depth + 1, i + 1);
        }
    }

    private static void bfs(int[][] map) {

        Queue<Point> qu = new ArrayDeque<>();
        boolean[][] visit = new boolean[n][m];

        for (Point v : virus) {
            qu.offer(new Point(v.x, v.y));
            visit[v.x][v.y] = true;
        }

        int count = zero_count - 3;

        while (!qu.isEmpty()) {
            Point now = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) { //범위 체크
                    if (!visit[nx][ny] && map[nx][ny] == 0) { //조건 체크
                        visit[nx][ny] = true;
                        map[nx][ny] = 2;
                        qu.offer(new Point(nx, ny));
                        count--;
                    }
                }
            }
        }

        max = Math.max(max, count);
    }

    private static int[][] getNewMap() {

        int[][] new_map = new int[n][m];
        for (int i = 0; i < n; i++) {
            System.arraycopy(map[i], 0, new_map[i], 0, m);
        }

        for (int i = 0; i < 3; i++) {
            Point p = zero.get(wall[i]);
            new_map[p.x][p.y] = 1;
        }

        return new_map;
    }
}
