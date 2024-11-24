import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int ans = 0;
    static int[][] arr;
    static int[][][] spread =
            {
                    {{-1, 1, 1}, {1, 1, 1}, {-1, 0, 7}, {1, 0, 7}, {-2, 0, 2}, {2, 0, 2}, {-1, -1, 10}, {1, -1, 10}, {0, -2, 5}},   //왼쪽
                    {{-1, -1, 1}, {-1, 1, 1}, {0, -1, 7}, {0, 1, 7}, {0, -2, 2}, {0, 2, 2}, {1, -1, 10}, {1, 1, 10}, {2, 0, 5}},   //아래쪽
                    {{-1, -1, 1}, {1, -1, 1}, {-1, 0, 7}, {1, 0, 7}, {-2, 0, 2}, {2, 0, 2}, {-1, 1, 10}, {1, 1, 10}, {0, 2, 5}},   //오른쪽
                    {{1, -1, 1}, {1, 1, 1}, {0, -1, 7}, {0, 1, 7}, {0, -2, 2}, {0, 2, 2}, {-1, -1, 10}, {-1, 1, 10}, {-2, 0, 5}}    //위쪽
            };
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int x = n / 2;
        int y = n / 2;

        int dir = 0;
        int depth = 1;

        while (true) {

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < depth; j++) {

                    x += dx[dir];
                    y += dy[dir];

                    if (x == 0 && y == 0) {
                        solve(x, y, dir);
                        System.out.println(ans);
                        return;
                    }

                    solve(x, y, dir);
                }

                dir = (dir + 1) % 4;
            }

            depth++;
        }

    }

    private static void solve(int x, int y, int dir) {

        int total = arr[x][y];
        int remain = total;

        for (int[] s : spread[dir]) {

            int nx = x + s[0];
            int ny = y + s[1];
            int moved = total * s[2] / 100;

            if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                ans += moved;
            } else {
                arr[nx][ny] += moved;
            }

            remain -= moved;
        }

        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
            ans += remain;
        } else {
            arr[nx][ny] += remain;
        }

        arr[x][y] = 0;
    }
}