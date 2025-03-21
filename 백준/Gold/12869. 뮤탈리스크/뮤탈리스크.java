import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] scv = new int[3];
        boolean[][][] visit = new boolean[61][61][61];

        for (int i = 0; i < n; i++) {
            scv[i] = Integer.parseInt(st.nextToken());
        }

        visit[scv[0]][scv[1]][scv[2]] = true;

        Queue<State> qu = new ArrayDeque<>();
        qu.offer(new State(scv, 0));

        int[][] damage = {
            {9, 3, 1},
            {9, 1, 3},
            {3, 9, 1},
            {3, 1, 9},
            {1, 9, 3},
            {1, 3, 9}
        };

        while (!qu.isEmpty()) {
            State state = qu.poll();

            int[] arr = state.arr;
            if (arr[0] <= 0 && arr[1] <= 0 && arr[2] <= 0) {
                System.out.println(state.count);
                return;
            }

            for (int[] d : damage) {
                int na = Math.max(0, arr[0] - d[0]);
                int nb = Math.max(0, arr[1] - d[1]);
                int nc = Math.max(0, arr[2] - d[2]);

                if (!visit[na][nb][nc]) {
                    visit[na][nb][nc] = true;
                    qu.offer(new State(new int[]{na, nb, nc}, state.count + 1));
                }
            }
        }
    }

    static class State {
        int[] arr;
        int count;

        public State(int[] arr, int count) {
            this.arr = arr;
            this.count = count;
        }
    }
}