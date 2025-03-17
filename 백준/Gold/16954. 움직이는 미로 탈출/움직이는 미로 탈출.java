import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    static final int R = 8, C = 8;
    static final int[] dx = {0, -1, 1, 0, 0, -1, 1, 1, -1};
    static final int[] dy = {0, 0, 0, -1, 1, 1, 1, -1, -1};
    static char[][] chess;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        chess = new char[R][C];

        for (int i = 0; i < R; i++) {
            chess[i] = br.readLine().toCharArray();
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{R - 1, 0});

        while (!qu.isEmpty()) {
            boolean[][] visit = new boolean[R][C];
            int size = qu.size();

            for (int i = 0; i < size; i++) {
                int[] now = qu.poll();
                int x = now[0], y = now[1];

                if (chess[x][y] == '#') continue;

                if (x == 0 && y == C - 1) {
                    return 1;
                }

                for (int d = 0; d < dx.length; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                    if (visit[nx][ny] || chess[nx][ny] == '#') continue;

                    visit[nx][ny] = true;
                    qu.offer(new int[]{nx, ny});
                }                
            }
            
            move();
        }

        return 0;
    }

    private static void move() {
        for (int i = R - 1; i > 0; i--) {
            for (int j = 0; j < C; j++) {
                chess[i][j] = chess[i - 1][j];
            }
        }
        for (int i = 0; i < C; i++) {
            chess[0][i] = '.';
        }
    }
}