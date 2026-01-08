import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static char[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[] dir = {'U', 'D', 'L', 'R'};
    static int[][][] path;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n][m];
        int[] initNode = new int[3];

        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();

            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'R') {
                    initNode[0] = (i * m + j);
                    board[i][j] = '.';
                } else if (board[i][j] == 'B') {
                    initNode[1] = (i * m + j);
                    board[i][j] = '.';
                }
            }
        }

        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(initNode);

        boolean[][] visit = new boolean[n * m][n * m];
        visit[initNode[0]][initNode[1]] = true;

        int ansMove = -1;

        path = new int[n * m][n * m][3];
        int redEnd = -1;
        int blueEnd = -1;

        bfs:
        while (!qu.isEmpty()) {
            int[] node = qu.poll();

            int move = node[2];

            if (move >= 10) {
                System.out.println(-1);
                return;
            }

            int red = node[0];
            int blue = node[1];

            for (int i = 0; i < 4; i++) {
                int rx = red / m;
                int ry = red % m;
                int redMove = 0;
                while (board[rx + dx[i]][ry + dy[i]] != '#' && board[rx][ry] != 'O') {
                    rx += dx[i];
                    ry += dy[i];
                    redMove++;
                }

                int bx = blue / m;
                int by = blue % m;
                int blueMove = 0;
                while (board[bx + dx[i]][by + dy[i]] != '#' && board[bx][by] != 'O') {
                    bx += dx[i];
                    by += dy[i];
                    blueMove++;
                }

                if (board[bx][by] == 'O') {
                    continue;
                }

                if (rx == bx && ry == by) {
                    if (redMove < blueMove) {
                        bx -= dx[i];
                        by -= dy[i];
                    } else {
                        rx -= dx[i];
                        ry -= dy[i];
                    }
                }

                int nextRed = (rx * m + ry);
                int nextBlue = (bx * m + by);

                if (board[rx][ry] == 'O') {
                    path[nextRed][nextBlue][0] = red;
                    path[nextRed][nextBlue][1] = blue;
                    path[nextRed][nextBlue][2] = i;

                    redEnd = nextRed;
                    blueEnd = nextBlue;
                    ansMove = move + 1;

                    break bfs;
                }

                if (!visit[nextRed][nextBlue]) {
                    path[nextRed][nextBlue][0] = red;
                    path[nextRed][nextBlue][1] = blue;
                    path[nextRed][nextBlue][2] = i;

                    visit[nextRed][nextBlue] = true;
                    qu.offer(new int[]{nextRed, nextBlue, move + 1});
                }
            }
        }

        System.out.println(ansMove);

        if (ansMove != -1) {
            StringBuilder sb = new StringBuilder();

            int curRed = redEnd;
            int curBlue = blueEnd;

            while (curRed != initNode[0] || curBlue != initNode[1]) {
                sb.append(dir[path[curRed][curBlue][2]]);

                int redTemp = path[curRed][curBlue][0];
                int blueTemp = path[curRed][curBlue][1];

                curRed = redTemp;
                curBlue = blueTemp;
            }

            System.out.println(sb.reverse());
        }
    }
}