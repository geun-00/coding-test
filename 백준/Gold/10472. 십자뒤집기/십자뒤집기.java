import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Main {

    static int[][] arr = {
            {8, 7, 5},
            {8, 7, 6, 4},
            {7, 6, 3},
            {8, 5, 4, 2},
            {7, 5, 4, 3, 1},
            {6, 4, 3, 0},
            {5, 2, 1},
            {4, 2, 1, 0},
            {3, 1, 0},
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int p = Integer.parseInt(br.readLine());
        while (p-- > 0) {
            int bit = 0;

            for (int i = 0; i < 3; i++) {
                String s = br.readLine();
                for (int j = 0; j < 3; j++) {
                    if (s.charAt(j) == '*') {
                        bit |= (1 << 8 - (i * 3 + j));
                    }
                }
            }

            System.out.println(bfs(bit));
        }
    }

    private static int bfs(int bit) {
        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{bit, 0});

        Set<Integer> visited = new HashSet<>();
        visited.add(bit);

        while (!qu.isEmpty()) {
            int[] cur = qu.poll();

            int curBit = cur[0];
            int click = cur[1];

            if (curBit == 0) {
                return click;
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int temp = 0;

                    for (int b : arr[(i * 3 + j)]) {
                        temp |= (1 << b);
                    }

                    int nextBit = curBit ^ temp;
                    if (visited.add(nextBit)) {
                        qu.offer(new int[]{nextBit, click + 1});
                    }
                }
            }
        }

        return -1;
    }
}