import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] map = new boolean[101][101];
    static int[] dir = new int[1024];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            setDir(x, y, d, g);
        }

        System.out.println(getCount());
    }

    private static int getCount() {

        int count = 0;

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1]) {
                    count++;
                }
            }
        }

        return count;
    }

    private static void setDir(int x, int y, int d, int g) {
        dir[0] = d;
        int idx = 1;

        for (int i = 1; i <= g; i++) {
            for (int j = idx - 1; j >= 0; j--) {
                dir[idx++] = (dir[j] + 1) % 4;
            }
        }

        map[x][y] = true;
        for (int i = 0; i < idx; i++) {
            x += dx[dir[i]];
            y += dy[dir[i]];
            map[x][y] = true;
        }
    }
}
