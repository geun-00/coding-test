import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static final int WHITE = 0;
    static final int RED = 1;
    static final int BLUE = 2;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static int n;
    static int[][] board;
    static Horse[] horseArr;
    static Deque<Integer>[][] horseList;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        board = new int[n][n];
        horseList = new ArrayDeque[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                horseList[i][j] = new ArrayDeque<>();
            }
        }

        horseArr = new Horse[k];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;

            horseArr[i] = new Horse(x, y, d);
            horseList[x][y].offer(i);
        }

        int turns = 0;

        while (turns <= 1000) {

            turns++;

            for (int i = 0; i < k; i++) {
                Horse h = horseArr[i];

                int x = h.x;
                int y = h.y;
                int dir = h.dir;

                Deque<Integer> stk = horseList[x][y];

                int nx = x + dx[dir];
                int ny = y + dy[dir];

                List<Integer> target = new ArrayList<>();
                while (!stk.isEmpty() && stk.peek() != i) {
                    target.add(stk.pop());
                }
                target.add(stk.pop());
                if (isBlocked(nx, ny)) {

                    dir = getOppositeDir(dir);
                    nx = x + dx[dir];
                    ny = y + dy[dir];

                    if (isBlocked(nx, ny)) {
                        if (horsesMove(target, x, y, false)) {
                            System.out.println(turns);
                            return;
                        }
                        horseArr[i].setDir(dir);
                    } else {
                        if (horsesMove(target, nx, ny, board[nx][ny] == RED)) {
                            System.out.println(turns);
                            return;
                        }
                        horseArr[i].setDir(dir);
                    }

                } else {
                    if (horsesMove(target, nx, ny, board[nx][ny] == RED)) {
                        System.out.println(turns);
                        return;
                    }
                }
            }
        }

        System.out.println(-1);
    }

    private static boolean horsesMove(List<Integer> target, int nx, int ny, boolean isRed) {

        if (isRed) Collections.reverse(target);

        for (int i = target.size() - 1; i >= 0; i--) {
            int targetIdx = target.get(i);
            horseArr[targetIdx].move(nx, ny);
            horseList[nx][ny].push(targetIdx);
        }

        return horseList[nx][ny].size() >= 4;
    }

    private static boolean isBlocked(int x, int y) {
        return x < 0 || y < 0 || x >= n || y >= n || board[x][y] == BLUE;
    }

    private static int getOppositeDir(int dir) {
        return (dir % 2 == 0) ? dir + 1 : dir - 1;
    }

    static class Horse {

        int x, y, dir;

        public Horse(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        public void move(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void setDir(int dir) {
            this.dir = dir;
        }
    }
}