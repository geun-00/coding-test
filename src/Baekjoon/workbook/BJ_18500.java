package Baekjoon.workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/18500">백준 18500번 - 미네랄 2</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-18500%EB%B2%88-%EB%AF%B8%EB%84%A4%EB%9E%84-2">velog</a>
 * @since 2025-03-12
 */
public class BJ_18500 {

    static int r, c;
    static char[][] cave;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        cave = new char[r][c];

        for (int i = 0; i < r; i++) {
            cave[i] = br.readLine().toCharArray();
        }

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            int height = Integer.parseInt(st.nextToken());
            solve(r - height, (i % 2 == 0));
        }

        StringBuilder sb = new StringBuilder();
        for (char[] arr : cave) {
            sb.append(arr).append("\n");
        }
        System.out.print(sb);
    }

    private static void solve(int height, boolean isLeft) {
        if (isLeft) {
            for (int i = 0; i < c; i++) {
                if (cave[height][i] == 'x') {
                    cave[height][i] = '.';
                    break;
                }
            }
        } else {
            for (int i = c - 1; i >= 0; i--) {
                if (cave[height][i] == 'x') {
                    cave[height][i] = '.';
                    break;
                }
            }
        }

        moveDown();
    }

    private static void moveDown() {
        List<Point> target = new ArrayList<>();
        boolean[][] visit = new boolean[r][c];

        // 공중에 있는 미네랄 클러스터 찾기
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (!visit[i][j] && cave[i][j] == 'x') {
                    List<Point> result = bfs(i, j, visit);
                    if (!result.isEmpty()) {
                        target = result;
                        break;
                    }
                }
            }
        }

        int minDrop = Integer.MAX_VALUE;
        boolean[][] isMineral = new boolean[r][c];

        // 떨어질 클러스터 표시
        for (Point p : target) isMineral[p.x][p.y] = true;

        // 떨어질 클러스터가 떨어질 수 있는 높이 구하기
        for (Point p : target) {
            int drop = 0;
            for (int i = p.x + 1; i < r; i++) {
                if (cave[i][p.y] == 'x' && !isMineral[i][p.y]) break;
                drop++;
            }
            minDrop = Math.min(minDrop, drop);
        }

        // 클러스터 떨어트리기
        for (Point p : target) cave[p.x][p.y] = '.';
        for (Point p : target) cave[p.x + minDrop][p.y] = 'x';
    }

    private static List<Point> bfs(int i, int j, boolean[][] visit) {
        List<Point> target = new ArrayList<>();
        target.add(new Point(i, j));

        visit[i][j] = true;
        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(i);
        qu.offer(j);
        int bottom = i; // 미네랄 클러스터 중 가장 밑에 있는 미네랄

        while (!qu.isEmpty()) {
            int x = qu.poll();
            int y = qu.poll();

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
                if (visit[nx][ny] || cave[nx][ny] == '.') continue;

                visit[nx][ny] = true;
                target.add(new Point(nx, ny));
                qu.offer(nx);
                qu.offer(ny);
                bottom = Math.max(bottom, nx);
            }
        }
        // 미네랄 클러스터 중 가장 밑에 있는 미네랄이 바닥과 떨어져 있음
        if (bottom < r - 1) {
            return target;
        }
        // 바닥과 붙어있으면 빈 리스트 반환
        return new ArrayList<>();
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
