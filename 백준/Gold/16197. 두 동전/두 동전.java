import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final char COIN = 'o';
    static final char EMPTY = '.';
    static final char WALL = '#';
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        char[][] board = new char[n + 2][m + 2];
        boolean[][][][] visit = new boolean[n + 2][m + 2][n + 2][m + 2];

        int x1 = -1, y1 = -1;
        int x2 = -1, y2 = -1;

        for (int i = 1; i <= n; i++) {
            char[] arr = br.readLine().toCharArray();

            for (int j = 1; j <= m; j++) {
                board[i][j] = arr[j - 1];

                if (board[i][j] == COIN) {
                    if (x1 == -1) {
                        x1 = i;
                        y1 = j;
                    } else {
                        x2 = i;
                        y2 = j;
                    }
                    board[i][j] = EMPTY;
                }
            }
        }

        visit[x1][y1][x2][y2] = true;
        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{x1, y1, x2, y2, 0});

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!qu.isEmpty()) {
            int[] cur = qu.poll();

            x1 = cur[0];
            y1 = cur[1];
            x2 = cur[2];
            y2 = cur[3];
            int button = cur[4];

            if (button > 10) continue;

            //두 동전이 모두 떨어진 경우
            if (isOut(x1, y1) && isOut(x2, y2)) {
                continue;
            }

            //두 동전 중 하나만 떨어진 경우
            if ((isIn(x1, y1) && isOut(x2, y2)) || (isOut(x1, y1) && isIn(x2, y2))) {
                System.out.println(button);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx1 = x1 + dx[i];
                int ny1 = y1 + dy[i];
                int nx2 = x2 + dx[i];
                int ny2 = y2 + dy[i];

                if (nx1 < 0 || ny1 < 0 || nx2 < 0 || ny2 < 0) continue;
                if (nx1 > n + 1 || ny1 > m + 1 || nx2 > n + 1 || ny2 > m + 1) continue;

                if (isIn(nx1, ny1) && board[nx1][ny1] == WALL) {
                    nx1 = x1;
                    ny1 = y1;
                }

                if (isIn(nx2, ny2) && board[nx2][ny2] == WALL) {
                    nx2 = x2;
                    ny2 = y2;
                }

                if (visit[nx1][ny1][nx2][ny2]) continue;

                visit[nx1][ny1][nx2][ny2] = true;
                qu.offer(new int[]{nx1, ny1, nx2, ny2, button + 1});
            }
        }

        System.out.println(-1);
    }

    public static boolean isIn(int x, int y) {
        return x >= 1 && x <= n && y >= 1 && y <= m;
    }

    public static boolean isOut(int x, int y) {
        return x < 1 || x > n || y < 1 || y > m;
    }
}