import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Main {
    static final int N = 5;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> pieces = new ArrayList<>();

        int initBit = 0;
        for (int i = 0; i < N; i++) {
            char[] arr = br.readLine().toCharArray();

            for (int j = 0; j < N; j++) {
                if (arr[j] == '*') {
                    int pos = i * N + j;
                    initBit |= (1 << pos);

                    pieces.add(pos);
                }
            }
        }

        int[] pos = pieces.stream().mapToInt(i -> i).toArray();
        System.out.println(bfs(pos));
    }

    private static int bfs(int[] initPos) {
        Queue<Node> qu = new ArrayDeque<>();
        qu.offer(new Node(initPos, 0));

        Set<String> visited = new HashSet<>();
        visited.add(Arrays.toString(initPos));

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!qu.isEmpty()) {
            Node cur = qu.poll();

            int[] pos = cur.pos;
            int moveCount = cur.moveCount;

            if (isConnected(pos)) {
                return moveCount;
            }

            for (int i = 0; i < pos.length; i++) {
                int x = pos[i] / N;
                int y = pos[i] % N;

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                    int next = nx * N + ny;

                    if (!isValid(pos, next)) continue;

                    int[] nextPos = pos.clone();
                    nextPos[i] = next;

                    if (visited.add(Arrays.toString(nextPos))) {
                        qu.offer(new Node(nextPos, moveCount + 1));
                    }
                }
            }
        }

        return 0;
    }

    private static boolean isValid(int[] pos, int nextPos) {
        for (int p : pos) {
            if (p == nextPos) {
                return false;
            }
        }

        return true;
    }

    private static boolean isConnected(int[] pos) {
        boolean[] used = new boolean[pos.length];
        used[0] = true;

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(0);

        while (!qu.isEmpty()) {
            int cur = qu.poll();
            int x = pos[cur] / N;
            int y = pos[cur] % N;

            for (int i = 0; i < pos.length; i++) {
                if (used[i]) continue;

                int nx = pos[i] / N;
                int ny = pos[i] % N;

                if ((Math.abs(x - nx) + Math.abs(y - ny)) == 1) {
                    used[i] = true;
                    qu.offer(i);
                }
            }
        }

        for (boolean use : used) {
            if (!use) return false;
        }

        return true;
    }

    static class Node {
        int[] pos;
        int moveCount;

        public Node(int[] pos, int moveCount) {
            this.pos = pos;
            this.moveCount = moveCount;
        }
    }
}