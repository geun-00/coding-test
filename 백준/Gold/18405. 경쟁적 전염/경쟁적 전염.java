import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int[][] map;
    static Queue<Integer>[] arr;
    static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        arr = new ArrayDeque[k + 1];

        for (int i = 1; i <= k; i++) {
            arr[i] = new ArrayDeque<>();
        }

        for (int i = 0; i < n; i++) {

            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {

                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] != 0) {
                    arr[map[i][j]].offer(i * n + j);
                }

            }
        }

        st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken()) - 1;
        int Y = Integer.parseInt(st.nextToken()) - 1;

        while (S-- > 0) {

            for (int i = 1; i <= k; i++) {

                int size = arr[i].size();

                for (int j = 0; j < size; j++) {

                    int now = arr[i].poll();

                    int x = now / n;
                    int y = now % n;

                    for (int d = 0; d < 4; d++) {

                        int nx = x + dx[d];
                        int ny = y + dy[d];

                        if (nx < 0 || ny < 0 || nx >= n || ny >= n || map[nx][ny] != 0) {
                            continue;
                        }

                        map[nx][ny] = i;
                        arr[i].offer(nx * n + ny);
                    }
                }
            }
        }

        System.out.println(map[X][Y]);
    }
}