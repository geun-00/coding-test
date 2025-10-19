import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr = {
            {0, 1, 2},
            {0, 4, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 4, 6},
            {2, 5, 8},
            {3, 4, 5},
            {6, 7, 8},
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        int r = 3, c = 3;

        while (t-- > 0) {
            int bit = 0;

            for (int i = 0; i < r; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                for (int j = 0; j < c; j++) {
                    String s = st.nextToken();

                    if ("T".equals(s)) {
                        int temp = (r * c) - (i * r + j) - 1;
                        bit |= (1 << temp);
                    }
                }
            }

            System.out.println(bfs(bit));
        }
    }

    private static int bfs(int init) {
        Set<Integer> visited = new HashSet<>();
        visited.add(init);

        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{init, 0});

        while (!qu.isEmpty()) {
            int[] cur = qu.poll();
            int now = cur[0];
            int count = cur[1];

            if (now == 0 || now == (1 << 9) - 1) {
                return count;
            }

            for (int[] row : arr) {
                int next = now;

                for (int n : row) {
                    next ^= (1 << n);
                }

                if (visited.add(next)) {
                    qu.offer(new int[]{next, count + 1});
                }
            }
        }

        return -1;
    }
}