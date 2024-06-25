import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Puzzle {
        int[][] state;
        int x, y, count;

        public Puzzle(int[][] state, int x, int y, int count) {
            this.state = state;
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    static int[][] goal = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] init = new int[3][3];
        int x = 0;
        int y = 0;

        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                init[i][j] = Integer.parseInt(st.nextToken());
                if (init[i][j] == 0) {
                    x = i;
                    y = j;
                }
            }
        }

        System.out.println(bfs(new Puzzle(init, x, y, 0)));
    }

    private static int bfs(Puzzle init) {

        HashSet<String> visit = new HashSet<>();
        Queue<Puzzle> qu = new ArrayDeque<>();

        qu.offer(init);
        visit.add(arrayToString(init.state));

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!qu.isEmpty()) {
            Puzzle now = qu.poll();

            if (check(now.state)) {
                return now.count;
            }


            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];


                if (nx >= 0 && nx < 3 && ny >= 0 && ny < 3) {
                    int[][] copy = getCopy(now.state);
                    copy[now.x][now.y] = copy[nx][ny];
                    copy[nx][ny] = 0;

                    String target = arrayToString(copy);
                    if (!visit.contains(target)) {
                        visit.add(target);
                        qu.offer(new Puzzle(copy, nx, ny, now.count + 1));
                    }
                }
            }
        }
        
        return -1;
    }

    private static int[][] getCopy(int[][] arr) {

        int[][] copy = new int[3][3];

        for (int i = 0; i < 3; i++) {
            System.arraycopy(arr[i], 0, copy[i], 0, 3);
        }

        return copy;
    }

    private static boolean check(int[][] now) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (goal[i][j] != now[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    private static String arrayToString(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int[] ints : arr) {
            for (int i : ints) {
                sb.append(i);
            }
        }
        return sb.toString();
    }
}
