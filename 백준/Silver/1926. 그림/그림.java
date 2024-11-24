import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] arr;
    static boolean[][] visit;
    static int n, m;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int maxSize = 0;
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visit[i][j] && arr[i][j] == 1) {
                    count++;
                    maxSize = Math.max(maxSize, bfs(i, j));
                }
            }
        }

        System.out.println(count);
        System.out.println(maxSize);
    }

    private static int bfs(int i, int j) {

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(i * m + j);

        visit[i][j] = true;

        int size = 1;

        while (!qu.isEmpty()) {

            int now = qu.poll();
            int x = now / m;
            int y = now % m;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m || visit[nx][ny] || arr[nx][ny] == 0) {
                    continue;
                }

                visit[nx][ny] = true;
                qu.offer(nx * m + ny);
                size++;
            }
        }

        return size;
    }
}