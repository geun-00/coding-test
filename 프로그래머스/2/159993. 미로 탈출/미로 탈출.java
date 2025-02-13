import java.util.*;

class Solution {
    public int solution(String[] maps) {
        int startX = 0, startY = 0;
        int leverX = 0, leverY = 0;
        int exitX = 0, exitY = 0;

        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length(); j++) {
                char c = maps[i].charAt(j);
                if (c == 'S') {
                    startX = i;
                    startY = j;
                } else if (c == 'L') {
                    leverX = i;
                    leverY = j;
                } else if (c == 'E') {
                    exitX = i;
                    exitY = j;
                }
            }
        }

        int startToLever = bfs(maps, startX, startY, leverX, leverY);
        int leverToExit = bfs(maps, leverX, leverY, exitX, exitY);

        if (startToLever == -1 || leverToExit == -1) {
            return -1;
        }

        return startToLever + leverToExit;
    }
    
    private int bfs(String[] maps, int sx, int sy, int ex, int ey) {

        int n = maps.length;
        int m = maps[0].length();

        boolean[][] visit = new boolean[n][m];
        visit[sx][sy] = true;

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(sx);
        qu.offer(sy);
        qu.offer(0);

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!qu.isEmpty()) {
            int x = qu.poll();
            int y = qu.poll();
            int count = qu.poll();

            if (x == ex && y == ey) {
                return count;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }
                if (visit[nx][ny] || maps[nx].charAt(ny) == 'X') {
                    continue;
                }
                visit[nx][ny] = true;
                qu.offer(nx);
                qu.offer(ny);
                qu.offer(count + 1);
            }
        }

        return -1;
    }
}