import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(dijkstra(arr, n, m));
    }

    private static int dijkstra(int[][] arr, int n, int m) {
        int[][] dist = new int[n][m];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(row -> row[0]));

        if (arr[0][0] != -1) {
            dist[0][0] = arr[0][0];
            pq.offer(new int[]{arr[0][0], 0, 0});
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cost = cur[0];
            int x = cur[1];
            int y = cur[2];

            if (cost > dist[x][y]) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m || arr[nx][ny] == -1) {
                    continue;
                }

                if (dist[x][y] + arr[nx][ny] < dist[nx][ny]) {
                    dist[nx][ny] = dist[x][y] + arr[nx][ny];
                    pq.offer(new int[]{dist[nx][ny], nx, ny});
                }
            }
        }

        return dist[n - 1][m - 1] == Integer.MAX_VALUE ? -1 : dist[n - 1][m - 1];
    }
}