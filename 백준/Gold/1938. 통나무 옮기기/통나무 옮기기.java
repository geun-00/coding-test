import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Main {

    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
    static char[][] map;
    static int n;
    static Set<String> visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new char[n][n];

        int bIdx = 0;
        int eIdx = 0;
        int[][] bbb = new int[3][2];
        int[][] eee = new int[3][2];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();

            for (int j = 0; j < n; j++) {
                if (map[i][j] == 'B') {
                    bbb[bIdx][0] = i;
                    bbb[bIdx][1] = j;
                    bIdx++;
                } else if (map[i][j] == 'E') {
                    eee[eIdx][0] = i;
                    eee[eIdx][1] = j;
                    eIdx++;
                }
            }
        }

        System.out.println(bfs(bbb, eee));
    }

    public static int bfs(int[][] bbb, int[][] eee) {
        visited = new HashSet<>();
        visited.add(Arrays.deepToString(bbb));

        Queue<State> qu = new ArrayDeque<>();
        qu.offer(new State(bbb, 0));

        while (!qu.isEmpty()) {
            State cur = qu.poll();
            int[][] pos = cur.pos;
            int move = cur.move;

            if (Arrays.deepEquals(pos, eee)) {
                return move;
            }

            if (canMove(pos, 0)) {
                int[][] up = move(pos, 0);
                if (canVisit(up)) {
                    qu.offer(new State(up, move + 1));
                }
            }

            if (canMove(pos, 1)) {
                int[][] down = move(pos, 1);
                if (canVisit(down)) {
                    qu.offer(new State(down, move + 1));
                }
            }

            if (canMove(pos, 2)) {
                int[][] left = move(pos, 2);
                if (canVisit(left)) {
                    qu.offer(new State(left, move + 1));
                }
            }

            if (canMove(pos, 3)) {
                int[][] right = move(pos, 3);
                if (canVisit(right)) {
                    qu.offer(new State(right, move + 1));
                }
            }

            if (canTurn(pos)) {
                int[][] turn = new int[3][2];

                if (pos[1][0] == pos[0][0]) {
                    turn(turn, 0, pos, 1);
                } else if (pos[1][1] == pos[0][1]) {
                    turn(turn, 1, pos, 0);
                }

                if (canVisit(turn)) {
                    qu.offer(new State(turn, move + 1));
                }
            }
        }

        return 0;
    }

    private static void turn(int[][] turn, int i, int[][] pos, int j) {
        turn[0][i] = pos[1][i] - 1;
        turn[1][i] = pos[1][i];
        turn[2][i] = pos[1][i] + 1;

        turn[0][j] = turn[1][j] = turn[2][j] = pos[1][j];
    }

    private static boolean canTurn(int[][] pos) {
        int x = pos[1][0];
        int y = pos[1][1];

        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= n || map[nx][ny] == '1') {
                return false;
            }
        }

        return true;
    }

    private static boolean canVisit(int[][] pos) {
        return visited.add(Arrays.deepToString(pos));
    }

    private static boolean canMove(int[][] pos, int dir) {
        for (int i = 0; i < 3; i++) {
            int nx = pos[i][0] + dx[dir];
            int ny = pos[i][1] + dy[dir];

            if (nx < 0 || ny < 0 || nx >= n || ny >= n || map[nx][ny] == '1') {
                return false;
            }
        }

        return true;
    }

    private static int[][] move(int[][] pos, int dir) {
        int[][] temp = new int[3][2];

        for (int i = 0; i < 3; i++) {
            temp[i][0] = pos[i][0] + dx[dir];
            temp[i][1] = pos[i][1] + dy[dir];
        }

        return temp;
    }

    static class State {
        int[][] pos;
        int move;

        public State(int[][] pos, int move) {
            this.pos = pos;
            this.move = move;
        }
    }
}