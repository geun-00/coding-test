import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dz = {-1, 1, 0, 0, 0, 0};
    static int[] dx = {0, 0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, 0, -1, 1};
    static int ans = -1;
    static int[][][] boards = new int[5][5][5];
    static int[][][] maze = new int[5][5][5];
    static int[] order = new int[5];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 5; k++) {
                    boards[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        solve(0, 0);
        System.out.println(ans);
    }

    private static void solve(int depth, int visit) {
        if (depth == 5) {
            setMaze();
            simulation(0);
            return;
        }

        for (int i = 0; i < 5; i++) {
            if ((1 << i & visit) == 0) {
                order[depth] = i;
                solve(depth + 1, visit | 1 << i);
            }
        }
    }

    private static void setMaze() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    maze[i][j][k] = boards[order[i]][j][k];
                }
            }
        }
    }

    private static void simulation(int depth) {
        if (depth == 5) {
            int move = bfs();
            if (move == -1) return;
            if (ans == -1 || move < ans) {
                ans = move;
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            simulation(depth + 1);
            rotate(depth);
        }
    }

    private static int bfs() {
        if (maze[0][0][0] == 0 || maze[4][4][4] == 0) {
            return -1;
        }

        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{0, 0, 0, 0});

        boolean[][][] visit = new boolean[5][5][5];
        visit[0][0][0] = true;

        while (!qu.isEmpty()) {
            int[] cur = qu.poll();
            int z = cur[0];
            int x = cur[1];
            int y = cur[2];
            int move = cur[3];

            if (z == 4 && x == 4 && y == 4) {
                return move;
            }

            for (int i = 0; i < 6; i++) {
                int nz = z + dz[i];
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nz < 0 || nz >= 5 || nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
                if (visit[nz][nx][ny] || maze[nz][nx][ny] == 0) continue;

                visit[nz][nx][ny] = true;
                qu.offer(new int[]{nz, nx, ny, move + 1});
            }
        }

        return -1;
    }

    private static void rotate(int depth) {
        int[][] temp = new int[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                temp[j][4 - i] = maze[depth][i][j];
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                maze[depth][i][j] = temp[i][j];
            }
        }
    }
}