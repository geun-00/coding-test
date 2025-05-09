import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {1, 1, 1, 0, 0, 0, -1, -1, -1};
    static int[] dy = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
    static char[][] board;
    static List<int[]> arduinos = new ArrayList<>();
    static int r, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int x = 0, y = 0;

        board = new char[r][c];

        for (int i = 0; i < r; i++) {
            board[i] = br.readLine()
                         .toCharArray();

            for (int j = 0; j < c; j++) {
                if (board[i][j] == 'I') {
                    x = i;
                    y = j;
                } else if (board[i][j] == 'R') {
                    arduinos.add(new int[]{i, j});
                }
            }
        }

        char[] arr = br.readLine()
                       .toCharArray();

        for (int i = 0; i < arr.length; i++) {
            int dir = arr[i] - '0' - 1;
            board[x][y] = '.';

            x += dx[dir];
            y += dy[dir];

            if (board[x][y] == 'R') {
                System.out.println("kraj " + (i + 1));
                return;
            }

            board[x][y] = 'I';

            if (!solve(x, y)) {
                System.out.println("kraj " + (i + 1));
                return;
            }

            for (int[] pos : arduinos) {
                board[pos[0]][pos[1]] = 'R';
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < r; i++) {
            sb.append(board[i])
              .append("\n");
        }
        System.out.print(sb);
    }

    private static boolean solve(int x, int y) {
        int[][] count = new int[r][c];

        for (int[] pos : arduinos) {
            int ax = pos[0];
            int ay = pos[1];

            board[ax][ay] = '.';

            int dir = -1;
            int minDist = Integer.MAX_VALUE;

            for (int i = 0; i < 9; i++) {
                int nx = ax + dx[i];
                int ny = ay + dy[i];
                if (nx < 0 || ny < 0 || nx >= r || ny >= c) continue;

                int dist = Math.abs(x - nx) + Math.abs(y - ny);
                if (dist < minDist) {
                    minDist = dist;
                    dir = i;
                }

                if (minDist == 0) return false;
            }

            count[ax + dx[dir]][ay + dy[dir]]++;
        }

        List<int[]> next = new ArrayList<>();

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (count[i][j] > 0) {
                    if (board[i][j] == 'I') {
                        return false;
                    }
                    if (count[i][j] == 1) {
                        board[i][j] = 'R';
                        next.add(new int[]{i, j});
                    }
                }
            }
        }

        arduinos = next;

        return true;
    }
}