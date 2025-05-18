import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static char[][] board;
    static boolean[][][][] visit;
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n][m];
        visit = new boolean[n][m][n][m];

        Queue<Node> qu = new ArrayDeque<>();
        int redX = 0, redY = 0, blueX = 0, blueY = 0;

        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();

            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'R') {
                    redX = i;
                    redY = j;
                } else if (board[i][j] == 'B') {
                    blueX = i;
                    blueY = j;
                }
            }
        }

        qu.offer(new Node(redX, redY, blueX, blueY, 0));

        while (!qu.isEmpty()) {
            Node now = qu.poll();

            if (now.count >= 10) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int rx = now.rx;
                int ry = now.ry;
                int redCount = 0;
                boolean isRedGoal = false;

                while (board[rx + dx[i]][ry + dy[i]] != '#') {
                    rx += dx[i];
                    ry += dy[i];
                    redCount++;

                    if (board[rx][ry] == 'O') {
                        isRedGoal = true;
                        break;
                    }
                }

                int bx = now.bx;
                int by = now.by;
                int blueCount = 0;
                boolean isBlueGoal = false;

                while (board[bx + dx[i]][by + dy[i]] != '#') {
                    bx += dx[i];
                    by += dy[i];
                    blueCount++;

                    if (board[bx][by] == 'O') {
                        isBlueGoal = true;
                        break;
                    }
                }

                if (isBlueGoal) continue;

                if (isRedGoal) {
                    System.out.println(1);
                    return;
                }

                if (rx == bx && ry == by) {
                    if (redCount < blueCount) {
                        bx -= dx[i];
                        by -= dy[i];
                    } else {
                        rx -= dx[i];
                        ry -= dy[i];
                    }
                }

                if (!visit[rx][ry][bx][by]) {
                    visit[rx][ry][bx][by] = true;
                    qu.offer(new Node(rx, ry, bx, by, now.count + 1));
                }
            }
        }

        System.out.println(0);
    }

    public static class Node {
        int rx, ry;
        int bx, by;
        int count;

        public Node(int rx, int ry, int bx, int by, int count) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.count = count;
        }
    }
}