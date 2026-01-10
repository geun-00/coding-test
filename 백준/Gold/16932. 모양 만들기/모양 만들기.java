import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visit;
    static int[][] arr, map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        map = new int[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int num = 1;
        Map<Integer, Integer> sizeMap = new HashMap<>();

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (arr[x][y] == 1 && !visit[x][y]) {
                    int size = bfs(x, y, num);
                    sizeMap.put(num, size);
                    num++;
                }
            }
        }

        int ans = 0;
        Set<Integer> set = new HashSet<>();

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (arr[x][y] == 1) continue;

                set.clear();
                int count = 1;

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx < 0 || ny < 0 || nx >= n | ny >= m) continue;

                    if (set.add(map[nx][ny])) {
                        count += sizeMap.getOrDefault(map[nx][ny], 0);
                    }
                }

                ans = Math.max(ans, count);
            }
        }

        System.out.println(ans);
    }

    private static int bfs(int x, int y, int num) {
        visit[x][y] = true;
        map[x][y] = num;

        int size = 1;

        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{x, y});

        while (!qu.isEmpty()) {
            int[] node = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node[0] + dx[i];
                int ny = node[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= n | ny >= m) continue;
                if (visit[nx][ny] || arr[nx][ny] == 0) continue;

                visit[nx][ny] = true;
                map[nx][ny] = num;
                qu.offer(new int[]{nx, ny});
                size++;
            }
        }

        return size;
    }
}