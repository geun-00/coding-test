import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;

public class Main {

    static final int N = 5;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] pick = new int[7];
    static char[][] arr;

    static ArrayList<Integer> pos = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        arr = new char[N][N];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        System.out.println(getAns(0, 0));
    }

    private static int getAns(int start, int depth) {

        if (depth == 7) {
            return check() ? 1 : 0;
        }

        int ans = 0;

        for (int i = start; i < N * N; i++) {
            pick[depth] = i;
            ans += getAns(i + 1, depth + 1);
        }

        return ans;
    }

    private static boolean check() {

        int countS = 0;

        for (int p : pick) {

            int x = p / N;
            int y = p % N;

            if (arr[x][y] == 'S') countS++;
        }

        if (countS < 4) return false;

        return bfs();
    }

    private static boolean bfs() {

        Queue<Integer> qu = new ArrayDeque<>();
        HashSet<Integer> selected = new HashSet<>();
        HashSet<Integer> visit = new HashSet<>();

        for (int p : pick) {
            selected.add(p);
        }

        visit.add(pick[0]);
        qu.offer(pick[0]);

        int connectedCount = 1;

        while (!qu.isEmpty()) {

            int now = qu.poll();
            int x = now / N;
            int y = now % N;

            for (int i = 0; i < 4; i++) {

                int nx = x + dx[i];
                int ny = y + dy[i];
                int next = nx * N + ny;

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                if (selected.contains(next) && visit.add(next)) {
                    qu.offer(next);
                    connectedCount++;
                }
            }
        }

        return connectedCount == 7;
    }

}