import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] board = new char[n][m];

        int srx = 0, sry = 0;
        int sbx = 0, sby = 0;

        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();

            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'R') {
                    board[i][j] = '.';
                    srx = i;
                    sry = j;
                } else if (board[i][j] == 'B') {
                    board[i][j] = '.';
                    sbx = i;
                    sby = j;
                }
            }
        }

        boolean[][][][] visited = new boolean[n][m][n][m];
        visited[srx][sry][sbx][sby] = true;

        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{srx, sry, sbx, sby, 0});

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!qu.isEmpty()) {
            int[] node = qu.poll();
            int rx = node[0];
            int ry = node[1];
            int bx = node[2];
            int by = node[3];
            int move = node[4];

            for (int i = 0; i < 4; i++) {
                int nrx = rx;
                int nry = ry;
                int redMove = 0;
                boolean redCanExit = false;

                while (board[nrx + dx[i]][nry + dy[i]] != '#') {
                    nrx += dx[i];
                    nry += dy[i];
                    redMove++;

                    if (board[nrx][nry] == 'O') {
                        redCanExit = true;
                        break;
                    }
                }

                int nbx = bx;
                int nby = by;
                int blueMove = 0;
                boolean blueCanExit = false;

                while (board[nbx + dx[i]][nby + dy[i]] != '#') {
                    nbx += dx[i];
                    nby += dy[i];
                    blueMove++;

                    if (board[nbx][nby] == 'O') {
                        blueCanExit = true;
                        break;
                    }
                }

                if (blueCanExit) {
                    continue;
                }

                if (redCanExit) {
                    System.out.println(move + 1);
                    return;
                }

                if (nrx == nbx && nry == nby) {
                    if (redMove < blueMove) {
                        nbx -= dx[i];
                        nby -= dy[i];
                    } else {
                        nrx -= dx[i];
                        nry -= dy[i];
                    }
                }

                if (!visited[nrx][nry][nbx][nby]) {
                    visited[nrx][nry][nbx][nby] = true;
                    qu.offer(new int[]{nrx, nry, nbx, nby, move + 1});
                }
            }
        }

        System.out.println(-1);
    }
}