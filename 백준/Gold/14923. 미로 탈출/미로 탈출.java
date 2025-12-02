import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int hx = Integer.parseInt(st.nextToken()) - 1;
        int hy = Integer.parseInt(st.nextToken()) - 1;

        st = new StringTokenizer(br.readLine());
        int ex = Integer.parseInt(st.nextToken()) - 1;
        int ey = Integer.parseInt(st.nextToken()) - 1;

        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{hx, hy, 1, 0});

        boolean[][][] visited = new boolean[n][m][2];
        visited[hx][hy][1] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!qu.isEmpty()) {
            int[] node = qu.poll();
            int x = node[0];
            int y = node[1];
            int chance = node[2];
            int d = node[3];

            if (x == ex && y == ey) {
                System.out.println(d);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }
                if (arr[nx][ny] == 0) {
                    if (!visited[nx][ny][chance]) {
                        visited[nx][ny][chance] = true;
                        qu.offer(new int[]{nx, ny, chance, d + 1});
                    }
                } else {
                    if (chance > 0 && !visited[nx][ny][0]) {
                        visited[nx][ny][0] = true;
                        qu.offer(new int[]{nx, ny, 0, d + 1});
                    }
                }
            }
        }

        System.out.println(-1);
    }
}