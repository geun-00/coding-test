import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int m, n;
    static boolean[][] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        visit = new boolean[m][n];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int x = y1; x < y2; x++) {
                for (int y = x1; y < x2; y++) {
                    visit[x][y] = true;
                }
            }
        }

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visit[i][j]) {
                    list.add(bfs(i, j));
                }
            }
        }

        list.sort(null);

        StringBuilder sb = new StringBuilder();

        sb.append(list.size()).append("\n");

        for (int i : list) {
            sb.append(i).append(" ");
        }

        System.out.print(sb);
    }

    private static int bfs(int x, int y) {

        visit[x][y] = true;

        ArrayDeque<Integer> qu = new ArrayDeque<>();
        qu.offer(x * n + y);

        int count = 1;

        while (!qu.isEmpty()) {

            int now = qu.poll();

            for (int i = 0; i < 4; i++) {

                int nx = now / n + dx[i];
                int ny = now % n + dy[i];

                if (nx < 0 || ny < 0 || nx >= m || ny >= n || visit[nx][ny]) {
                    continue;
                }

                count++;
                visit[nx][ny] = true;
                qu.offer(nx * n + ny);
            }
        }

        return count;
    }
}
