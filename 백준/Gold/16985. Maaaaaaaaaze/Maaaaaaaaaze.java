import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int N = 5;

    static int ans = -1;
    static int[] dz = {-1, 1, 0, 0, 0, 0};
    static int[] dx = {0, 0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, 0, -1, 1};
    static int[][][] boards = new int[N][N][N];
    static int[][][] maze = new int[N][N][N];
    static int[] order = new int[N];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < N; k++) {
                    boards[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        solve(0, 0);
        System.out.println(ans);
    }

    private static void solve(int depth, int visit) {
        if (depth == N) {
            setMaze();
            simulation(0);
            return;
        }

        for (int i = 0; i < N; i++) {
            if ((1 << i & visit) == 0) {
                order[depth] = i;
                solve(depth + 1, visit | 1 << i);
            }
        }
    }

    private static void setMaze() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    maze[i][j][k] = boards[order[i]][j][k];
                }
            }
        }
    }

    private static void simulation(int depth) {
        if (depth == N) {
            int move = bfs();

            if (move == -1) return;
            if (ans == -1 || move < ans) {
                ans = move;
            }
            return;
        }

        for (int i = 0; i < N - 1; i++) {
            simulation(depth + 1);
            rotate(depth);
        }
    }

    private static int bfs() {
        if (maze[0][0][0] == 0 || maze[N - 1][N - 1][N - 1] == 0) {
            return -1;
        }

        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{0, 0, 0, 0});

        boolean[][][] visit = new boolean[N][N][N];
        visit[0][0][0] = true;

        while (!qu.isEmpty()) {
            int[] cur = qu.poll();
            int z = cur[0];
            int x = cur[1];
            int y = cur[2];
            int move = cur[3];

            if (z == N - 1 && x == N - 1 && y == N - 1) {
                return move;
            }

            for (int i = 0; i < 6; i++) {
                int nz = z + dz[i];
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nz < 0 || nz >= N || nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (visit[nz][nx][ny] || maze[nz][nx][ny] == 0) continue;

                visit[nz][nx][ny] = true;
                qu.offer(new int[]{nz, nx, ny, move + 1});
            }
        }

        return -1;
    }

    private static void rotate(int depth) {
        int[][] temp = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[j][N - 1 - i] = maze[depth][i][j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                maze[depth][i][j] = temp[i][j];
            }
        }
    }
}