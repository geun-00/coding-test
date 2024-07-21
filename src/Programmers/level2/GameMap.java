package Programmers.level2;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/1844">프로그래머스 - Lv.2 : 게임 맵 최단거리</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EA%B2%8C%EC%9E%84-%EB%A7%B5-%EC%B5%9C%EB%8B%A8%EA%B1%B0%EB%A6%AC">velog</a>
 *
 * @since 2024-07-19
 */
public class GameMap {
    public static void main(String[] args) {
        System.out.println(solution(new int[][]
                {
                        {1, 0, 1, 1, 1},
                        {1, 0, 1, 0, 1},
                        {1, 0, 1, 1, 1},
                        {1, 1, 1, 0, 1},
                        {0, 0, 0, 0, 1}
                }
        ));

        System.out.println(solution(new int[][]
                {
                        {1, 0, 1, 1, 1},
                        {1, 0, 1, 0, 1},
                        {1, 0, 1, 1, 1},
                        {1, 1, 1, 0, 0},
                        {0, 0, 0, 0, 1}
                }
        ));
    }

    private static int solution(int[][] maps) {

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int n = maps.length;
        int m = maps[0].length;

        boolean[][] visit = new boolean[n][m];

        Queue<Point> qu = new ArrayDeque<>();
        qu.offer(new Point(0, 0, 1));
        visit[0][0] = true;

        while (!qu.isEmpty()) {
            Point now = qu.poll();

            if (now.x == n - 1 && now.y == m - 1) {
                return now.count;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visit[nx][ny] && maps[nx][ny] == 1) {

                    visit[nx][ny] = true;
                    qu.offer(new Point(nx, ny, now.count + 1));
                }
            }
        }

        return -1;
    }

    static class Point {
        int x, y, count;

        public Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
