import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static boolean[][] row_visit = new boolean[9][9];
    static boolean[][] col_visit = new boolean[9][9];
    static boolean[][] box_visit = new boolean[9][9];
    static int[][] arr = new int[9][9];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (arr[i][j] != 0) {
                    int num = arr[i][j] - 1;

                    row_visit[i][num] = true;
                    col_visit[j][num] = true;
                    box_visit[getBoxNum(i, j)][num] = true;
                }
            }
        }

        solve(0, 0);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static boolean solve(int x, int y) {
        if (x == 9) {
            return true;
        }

        int nx = (y == 8) ? x + 1 : x;
        int ny = (y == 8) ? 0 : y + 1;

        if (arr[x][y] != 0) {
            return solve(nx, ny);
        }

        for (int i = 0; i < 9; i++) {
            if (row_visit[x][i] || col_visit[y][i] || box_visit[getBoxNum(x, y)][i]) {
                continue;
            }

            arr[x][y] = i + 1;
            row_visit[x][i] = true;
            col_visit[y][i] = true;
            box_visit[getBoxNum(x, y)][i] = true;

            if (solve(nx, ny)) {
                return true;
            }

            arr[x][y] = 0;
            row_visit[x][i] = false;
            col_visit[y][i] = false;
            box_visit[getBoxNum(x, y)][i] = false;
        }

        return false;
    }

    private static int getBoxNum(int i, int j) {
        return i / 3 * 3 + j / 3;
    }
}