import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static char[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] mem;
    static boolean[][] visit;
    static boolean flag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n][m];
        mem = new int[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
            Arrays.fill(mem[i], -1);
        }

        dfs(0, 0);

        System.out.println(flag ? -1 : mem[0][0]);
    }

    private static int dfs(int x, int y) {

        if (flag) return 0;

        if (mem[x][y] != -1) {
            return mem[x][y];
        }

        visit[x][y] = true;
        int num = board[x][y] - '0';
        mem[x][y] = 1;

        for (int i = 0; i < 4; i++) {
            int nx = x + (dx[i] * num);
            int ny = y + (dy[i] * num);

            if (nx < 0 || ny < 0 || nx >= n || ny >= m || board[nx][ny] == 'H') {
                continue;
            }
            if (visit[nx][ny]) {
                flag = true;
                return 0;
            }

            mem[x][y] = Math.max(mem[x][y], 1 + dfs(nx, ny));
        }

        visit[x][y] = false;

        return mem[x][y];
    }
}