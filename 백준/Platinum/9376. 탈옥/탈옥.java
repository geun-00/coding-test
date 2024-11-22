import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int h, w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        int[] points = new int[2];

        while (t-- > 0) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            map = new char[h + 2][w + 2];

            for (int i = 0; i < h + 2; i++) {
                Arrays.fill(map[i], '.');
            }

            int idx = 0;

            for (int i = 1; i <= h; i++) {
                char[] arr = br.readLine().toCharArray();

                for (int j = 1; j <= w; j++) {

                    map[i][j] = arr[j - 1];

                    if (map[i][j] == '$') {
                        points[idx++] = i * (w + 2) + j;
                    }
                }
            }

            int[][] arr1 = bfs(points[0]);
            int[][] arr2 = bfs(points[1]);
            int[][] arr3 = bfs(0);

            int min = Integer.MAX_VALUE;

            for (int i = 0; i < h + 2; i++) {
                for (int j = 0; j < w + 2; j++) {

                    if (map[i][j] == '*') continue;

                    int sum = arr1[i][j] + arr2[i][j] + arr3[i][j];

                    if (map[i][j] == '#') {
                        sum -= 2;
                    }

                    min = Math.min(min, sum);
                }
            }

            sb.append(min).append("\n");
        }

        System.out.print(sb);
    }

    private static int[][] bfs(int p) {

        int[][] arr = new int[h + 2][w + 2];

        for (int i = 0; i < h + 2; i++) {
            Arrays.fill(arr[i], 9999);
        }

        boolean[][] visit = new boolean[h + 2][w + 2];

        int x = p / (w + 2);
        int y = p % (w + 2);
        visit[x][y] = true;
        arr[x][y] = 0;

        Deque<Integer> qu = new ArrayDeque<>();
        qu.offer(p);

        while (!qu.isEmpty()) {

            int now = qu.poll();
            x = now / (w + 2);
            y = now % (w + 2);

            for (int i = 0; i < 4; i++) {

                int nx = x + dx[i];
                int ny = y + dy[i];
                int next = nx * (w + 2) + ny;

                if (nx < 0 || ny < 0 || nx >= h + 2 || ny >= w + 2 || map[nx][ny] == '*' || visit[nx][ny]) {
                    continue;
                }

                visit[nx][ny] = true;

                if (map[nx][ny] == '.' || map[nx][ny] == '$') {

                    arr[nx][ny] = arr[x][y];
                    qu.offerFirst(next);
                } else if (map[nx][ny] == '#') {
                    arr[nx][ny] = arr[x][y] + 1;
                    qu.offerLast(next);
                }
            }
        }

        return arr;
    }
}