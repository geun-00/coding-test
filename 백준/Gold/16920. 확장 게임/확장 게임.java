import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] arr = new int[p + 1];
        Queue<int[]>[] queues = new Queue[p + 1];

        for (int i = 1; i <= p; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            queues[i] = new ArrayDeque<>();
        }

        map = new char[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();

            for (int j = 0; j < m; j++) {
                if (Character.isDigit(map[i][j])) {
                    queues[map[i][j] - '0'].offer(new int[]{i, j});
                }
            }
        }

        while (true) {
            boolean flag = false;

            for (int player = 1; player <= p; player++) {
                int steps = arr[player];
                Queue<int[]> nextQu = new ArrayDeque<>();

                Queue<int[]> curQu = queues[player];

                for (int s = 0; s < steps && !curQu.isEmpty(); s++) {
                    int size = curQu.size();

                    for (int i = 0; i < size; i++) {
                        int[] cur = curQu.poll();
                        int x = cur[0];
                        int y = cur[1];

                        for (int d = 0; d < 4; d++) {
                            int nx = x + dx[d];
                            int ny = y + dy[d];

                            if (nx < 0 || ny < 0 || nx >= n || ny >= m || map[nx][ny] != '.') continue;

                            flag = true;
                            nextQu.offer(new int[]{nx, ny});
                            curQu.offer(new int[]{nx, ny});
                            map[nx][ny] = map[x][y];
                        }
                    }

                }

                queues[player] = nextQu;
            }

            if (!flag) break;
        }

        for (int i = 1; i <= p; i++) {
            int count = 0;

            for (int x = 0; x < n; x++) {
                for (int y = 0; y < m; y++) {
                    if (map[x][y] - '0' == i) {
                        count++;
                    }
                }
            }

            System.out.print(count + " ");
        }
    }
}