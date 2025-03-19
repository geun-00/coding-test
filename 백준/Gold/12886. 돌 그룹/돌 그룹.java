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

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int total = A + B + C;

        if (total % 3 != 0) {
            System.out.println(0);
            return;
        }

        boolean[][] visit = new boolean[1501][1501];
        visit[A][B] = true;
        visit[A][C] = true;
        visit[B][C] = true;

        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{A, B, C});

        while (!qu.isEmpty()) {
            int[] stone = qu.poll();
            int a = stone[0];
            int b = stone[1];
            int c = stone[2];

            if (a == b && b == c) {
                System.out.println(1);
                return;
            }

            int[][] pairs = {{a, b}, {a, c}, {b, c}};

            for (int[] pair : pairs) {
                if (pair[0] != pair[1]) {
                    int x = pair[0];
                    int y = pair[1];

                    int nx = (x > y) ? x - y : x + x;
                    int ny = (x > y) ? y + y : y - x;
                    int nz = total - (nx + ny);

                    if (!visit[nx][ny]) {
                        visit[nx][ny] = true;
                        qu.offer(new int[]{nx, ny, nz});
                    }
                }
            }
        }

        System.out.println(0);
    }
}
