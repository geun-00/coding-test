import java.util.*;

class Solution {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
       final int N = 100;

        int[][] map = new int[N + 1][N + 1];
        boolean[][] visit = new boolean[N + 1][N + 1];

        for (int[] a : rectangle) {
            int x1 = a[0] * 2;
            int y1 = a[1] * 2;
            int x2 = a[2] * 2;
            int y2 = a[3] * 2;

            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {

                    if (x == x1 || x == x2 || y == y1 || y == y2) {
                        if (map[x][y] == 0) {
                            map[x][y] = 1;
                        }
                    }
                    else {
                        map[x][y] = 2;
                    }
                }
            }
        }

        Queue<Point> qu = new ArrayDeque<>();
        qu.offer(new Point(characterX * 2, characterY * 2, 0));

        visit[characterX * 2][characterY * 2] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!qu.isEmpty()) {

            Point now = qu.poll();

            int x = now.x;
            int y = now.y;
            int move = now.move;

            if (x == itemX * 2 && y == itemY * 2) {
                return move / 2;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx > N || ny > N || map[nx][ny] != 1 || visit[nx][ny]) {
                    continue;
                }

                visit[nx][ny] = true;
                qu.offer(new Point(nx, ny, move + 1));
            }
        }

        return 0;
    }

    static class Point {

        int x, y, move;

        public Point(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }
}