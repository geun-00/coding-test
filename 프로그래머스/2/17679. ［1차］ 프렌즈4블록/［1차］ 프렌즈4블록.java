import java.util.*;

class Solution {
    public int solution(int m, int n, String[] board) {
        final char empty = 'x';

        char[][] map = new char[m][n];
        for (int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        }

        int ans = 0;
        Queue<Character> qu = new ArrayDeque<>();
        boolean[][] mark = new boolean[m][n];

        while (true) {

            boolean flag = true;

            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {

                    if (map[i][j] == empty) continue;

                    if (map[i][j] == map[i + 1][j] &&
                        map[i][j] == map[i][j + 1] &&
                        map[i][j] == map[i + 1][j + 1]) {

                        flag = false;
                        mark[i][j] = mark[i + 1][j] = mark[i][j + 1] = mark[i + 1][j + 1] = true;
                    }
                }
            }

            if (flag) break;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (mark[i][j]) {
                        ans++;
                        map[i][j] = empty;
                        mark[i][j] = false;
                    }
                }
            }

            for (int i = 0; i < n; i++) {

                for (int j = m - 1; j >= 0; j--) {
                    if (map[j][i] != empty) {
                        qu.offer(map[j][i]);
                    }
                }

                for (int j = m - 1; j >= 0; j--) {
                    if (qu.isEmpty()) {
                        map[j][i] = empty;
                    } else {
                        map[j][i] = qu.poll();
                    }
                }
            }
        }

        return ans;
    }
}