import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static char[][] map;
    static int r, c;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        visit = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int count = 0;
        for (int i = 0; i < r; i++) {
            count += solve(i, 0);
        }

        System.out.println(count);
    }

    private static int solve(int x, int y) {

        visit[x][y] = true;

        if (y == c - 1) {
            return 1;
        }

        if (x - 1 >= 0 && map[x - 1][y + 1] == '.' && !visit[x - 1][y + 1]) {
            if (solve(x - 1, y + 1) == 1) {
                return 1;
            }
        }

        if (map[x][y + 1] == '.' && !visit[x][y + 1]) {
            if (solve(x, y + 1) == 1) {
                return 1;
            }
        }

        if (x + 1 < r && map[x + 1][y + 1] == '.' && !visit[x + 1][y + 1]) {
            if (solve(x + 1, y + 1) == 1) {
                return 1;
            }
        }

        return 0;
    }
}
