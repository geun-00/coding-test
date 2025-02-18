import java.util.*;

class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    
    public int[] solution(String[] maps) {
        int n = maps.length;
        int m = maps[0].length();
        char[][] board = new char[n][m];

        for (int i = 0; i < n; i++) {
            board[i] = maps[i].toCharArray();
        }

        boolean[][] visit = new boolean[n][m];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] != 'X' && !visit[i][j]) {
                    list.add(bfs(i, j, visit, board));
                }
            }
        }

        if (list.isEmpty()) {
            return new int[]{-1};
        }

        int[] ans = new int[list.size()];
        list.sort(null);

        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    private int bfs(int i, int j, boolean[][] visit, char[][] board) {

        int count = board[i][j] - '0';
        int n = board.length;
        int m = board[0].length;

        visit[i][j] = true;
        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(i);
        qu.offer(j);

        while (!qu.isEmpty()) {
            int x = qu.poll();
            int y = qu.poll();

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (visit[nx][ny] || board[nx][ny] == 'X') continue;

                visit[nx][ny] = true;
                qu.offer(nx);
                qu.offer(ny);
                count += board[nx][ny] - '0';
            }
        }

        return count;
    }
}