import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static boolean[] visit;
    static int[] arr;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n, m;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        visit = new boolean[n * m];
        arr = new int[n * m];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;

            arr[getPoint(r, c)] = 1;
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int p = getPoint(i, j);

                if (arr[p] == 1 && !visit[p]) {
                    ans = Math.max(ans, bfs(p));
                }
            }
        }

        System.out.println(ans);
    }

    private static int bfs(int p) {

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(p);

        visit[p] = true;

        int size = 1;

        while (!qu.isEmpty()) {

            int now = qu.poll();
            int x = now / m;
            int y = now % m;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                int next = getPoint(nx, ny);

                if (visit[next] || arr[next] == 0) continue;

                visit[next] = true;
                qu.offer(next);
                size++;
            }
        }

        return size;
    }

    private static int getPoint(int x, int y) {
        return x * m + y;
    }
}