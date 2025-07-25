import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        char[][] arr = new char[r][c];

        for (int i = 0; i < r; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        List<int[]> pos = new ArrayList<>();
        int sx = 11;
        int sy = 11;
        int ex = 0;
        int ey = 0;

        for (int x = 0; x < r; x++) {
            for (int y = 0; y < c; y++) {
                if (arr[x][y] == 'X') {
                    int count = 0;

                    for (int i = 0; i < 4; i++) {
                        int nx = x + dx[i];
                        int ny = y + dy[i];

                        if (nx < 0 || ny < 0 || nx >= r || ny >= c || arr[nx][ny] == '.') {
                            count++;
                        }
                    }

                    if (count < 3) {
                        pos.add(new int[]{x, y});
                        sx = Math.min(sx, x);
                        sy = Math.min(sy, y);
                        ex = Math.max(ex, x);
                        ey = Math.max(ey, y);
                    }
                }
            }
        }

        char[][] ans = new char[(ex - sx + 1)][(ey - sy + 1)];
        for (char[] row : ans) {
            Arrays.fill(row, '.');
        }

        for (int[] land : pos) {
            int x = land[0];
            int y = land[1];

            ans[x - sx][y - sy] = 'X';
        }

        StringBuilder sb = new StringBuilder();
        for (char[] row : ans) {
            sb.append(row).append("\n");
        }

        System.out.print(sb);
    }
}