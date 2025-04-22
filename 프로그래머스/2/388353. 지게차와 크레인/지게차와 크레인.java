import java.util.*;

class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    
    public int solution(String[] storage, String[] requests) {
        int n = storage.length;
        int m = storage[0].length();

        char[][] map = new char[n + 2][m + 2];
        boolean[][] outside = new boolean[n + 2][m + 2];

        for (char[] arr : map) Arrays.fill(arr, '.');
        for (boolean[] out : outside) Arrays.fill(out, true);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i + 1][j + 1] = storage[i].charAt(j);
                outside[i + 1][j + 1] = false;
            }
        }

        for (String req : requests) {
            char target = req.charAt(0);

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if (map[i][j] == target) {
                        if (req.length() == 2) {
                            map[i][j] = '.';
                        }
                        else {
                            int count = 0;
                            for (int d = 0; d < 4; d++) {
                                if (outside[i + dx[d]][j + dy[d]]) {
                                    count++;
                                }
                            }
                            if (count > 0) map[i][j] = '.';
                        }
                    }
                }
            }

            updateOutside(outside, map, n, m);
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (map[i][j] != '.') {
                    ans++;
                }
            }
        }

        return ans;
    }

    private void updateOutside(boolean[][] outside, char[][] map, int n, int m) {
        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{0, 0});

        boolean[][] visit = new boolean[n + 2][m + 2];
        visit[0][0] = true;

        while (!qu.isEmpty()) {
            int[] cur = qu.poll();
            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx <= n + 1 && ny <= m + 1) {
                    if (map[nx][ny] == '.' && !visit[nx][ny]) {
                        visit[nx][ny] = true;
                        qu.offer(new int[]{nx, ny});
                        outside[nx][ny] = true;
                    }
                }
            }
        }
    }
}