import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] board;
    static List<List<int[]>> enemies = new ArrayList<>();
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];

        List<int[]> empties = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                if (board[i][j] == 0) {
                    empties.add(new int[]{i, j});
                }
            }
        }

        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && board[i][j] == 2) {
                    bfsForEnemies(i, j);
                }
            }
        }

        int ans = 0;

        for (int i = 0; i < empties.size() - 1; i++) {
            int x1 = empties.get(i)[0];
            int y1 = empties.get(i)[1];

            for (int j = i + 1; j < empties.size(); j++) {
                int x2 = empties.get(j)[0];
                int y2 = empties.get(j)[1];

                board[x1][y1] = 1;
                board[x2][y2] = 1;

                ans = Math.max(ans, solve());

                board[x1][y1] = 0;
                board[x2][y2] = 0;
            }
        }

        System.out.println(ans);
    }

    private static int solve() {
        int count = 0;

        for (List<int[]> e : enemies) {
            boolean flag = false;

            for (int[] pos : e) {
                int x = pos[0];
                int y = pos[1];

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                        continue;
                    }

                    if (board[nx][ny] == 0) {
                        flag = true;
                        break;
                    }
                }

                if (flag) {
                    count -= e.size();
                    break;
                }
            }

            count += e.size();
        }

        return count;
    }

    private static void bfsForEnemies(int i, int j) {
        visited[i][j] = true;

        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{i, j});

        List<int[]> group = new ArrayList<>();
        group.add(new int[]{i, j});

        while (!qu.isEmpty()) {
            int[] node = qu.poll();
            int x = node[0];
            int y = node[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }
                if (visited[nx][ny] || board[nx][ny] != 2) {
                    continue;
                }

                visited[nx][ny] = true;
                qu.offer(new int[]{nx, ny});
                group.add(new int[]{nx, ny});
            }
        }

        enemies.add(group);
    }
}