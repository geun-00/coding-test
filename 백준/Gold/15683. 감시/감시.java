import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int n, m;
    static int min = Integer.MAX_VALUE;
    static ArrayList<Point> cctv = new ArrayList<>();
    static final int WALL = 6;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (1 <= map[i][j] && map[i][j] <= 5) {
                    cctv.add(new Point(i, j));
                }
            }
        }

        solve(0);

        System.out.println(min);
    }

    private static void solve(int depth) {
        if (depth == cctv.size()) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 0) {
                        sum++;
                    }
                }
            }
            min = Math.min(min, sum);
            return;
        }

        Point p = cctv.get(depth);
        int num = map[p.x][p.y];

        int[][] temp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                temp[i][j] = map[i][j];
            }
        }

        switch (num) {
            case 1:
                for (int i = 0; i < 4; i++) {
                    setCctv(i, p.x, p.y);
                    solve(depth + 1);
                    resetMap(temp);
                }
                break;
            case 2:
                for (int i = 0; i < 2; i++) {
                    setCctv(i, p.x, p.y);
                    setCctv(i + 2, p.x, p.y);
                    solve(depth + 1);
                    resetMap(temp);
                }
                break;
            case 3:
                for (int i = 0; i < 4; i++) {
                    setCctv(i, p.x, p.y);
                    setCctv((i + 3) % 4, p.x, p.y);
                    solve(depth + 1);
                    resetMap(temp);
                }
                break;
            case 4:
                for (int i = 0; i < 4; i++) {
                    setCctv(i, p.x, p.y);
                    setCctv((i + 1) % 4, p.x, p.y);
                    setCctv((i + 3) % 4, p.x, p.y);
                    solve(depth + 1);
                    resetMap(temp);
                }
                break;
            case 5:
                for (int i = 0; i < 4; i++) {
                    setCctv(i, p.x, p.y);
                }
                solve(depth + 1);
        }
    }

    private static void resetMap(int[][] temp) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = temp[i][j];
            }
        }
    }

    private static void setCctv(int dir, int x, int y) {
        switch (dir) {
            case 0: //위로
                for (int i = x - 1; i >= 0; i--) {
                    if (map[i][y] == WALL) {
                        break;
                    }
                    if (map[i][y] == 0) {
                        map[i][y] = -1;
                    }
                }
                break;
            case 1: //왼쪽으로
                for (int i = y - 1; i >= 0; i--) {
                    if (map[x][i] == WALL) {
                        break;
                    }
                    if (map[x][i] == 0) {
                        map[x][i] = -1;
                    }
                }
                break;
            case 2: //아래로
                for (int i = x + 1; i < n; i++) {
                    if (map[i][y] == WALL) {
                        break;
                    }
                    if (map[i][y] == 0) {
                        map[i][y] = -1;
                    }
                }
                break;
            case 3: //오른쪽으로
                for (int i = y + 1; i < m; i++) {
                    if (map[x][i] == WALL) {
                        break;
                    }
                    if (map[x][i] == 0) {
                        map[x][i] = -1;
                    }
                }
        }
    }
}
