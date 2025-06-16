import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static final int DESTROYED = -1;
    static int n;
    static int[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static List<int[]> paths;
    static int[] ans = new int[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        paths = getPaths();
        int m = Integer.parseInt(st.nextToken());

        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            destroyIce(d, s);
            moveMarble();
            explosion();
            changeMarble();
        }


        int result = 0;
        for (int i = 1; i <= 3; i++) {
            result += (ans[i] * i);
        }
        System.out.println(result);
    }

    private static void destroyIce(int d, int s) {
        int x = (n / 2);
        int y = (n / 2);

        for (int i = 0; i < s; i++) {
            x += dx[d];
            y += dy[d];
            board[x][y] = DESTROYED;
        }
    }

    private static void moveMarble() {
        int[][] newBoard = new int[n][n];

        Queue<Integer> qu = new ArrayDeque<>();
        for (int[] path : paths) {
            int x = path[0];
            int y = path[1];
            if (board[x][y] != DESTROYED) {
                qu.offer(board[x][y]);
            }
        }

        for (int[] path : paths) {
            if (qu.isEmpty()) break;

            int x = path[0];
            int y = path[1];
            newBoard[x][y] = qu.poll();
        }

        board = newBoard;
    }

    private static void explosion() {
        while (true) {
            boolean flag = false;

            int[] pos = paths.get(0);
            int x = pos[0];
            int y = pos[1];
            int num = board[x][y];
            int length = 1;
            int start = 0;

            for (int i = 1; i < paths.size(); i++) {
                pos = paths.get(i);
                x = pos[0];
                y = pos[1];

                if (board[x][y] == 0) break;

                if (num != board[x][y]) {
                    if (length >= 4) {
                        flag = true;
                        ans[num] += length;
                        for (int j = start; j < i; j++) {
                            pos = paths.get(j);
                            board[pos[0]][pos[1]] = DESTROYED;
                        }
                    }
                    length = 1;
                    num = board[x][y];
                    start = i;
                } else {
                    length++;
                }
            }

            // 마지막 그룹 처리
            if (length >= 4) {
                flag = true;
                ans[num] += length;
                for (int j = start; j < paths.size(); j++) {
                    pos = paths.get(j);
                    if (board[pos[0]][pos[1]] == num) {
                        board[pos[0]][pos[1]] = DESTROYED;
                    }
                }
            }

            moveMarble();
            if (!flag) break;
        }
    }

    private static void changeMarble() {
        List<Integer> groups = new ArrayList<>();
        int[] pos = paths.get(0);
        int x = pos[0];
        int y = pos[1];
        
        // 첫 번째 위치가 0이면 바로 종료
        if (board[x][y] == 0) {
            board = new int[n][n];
            return;
        }
        
        int num = board[x][y];
        int length = 1;
    
        for (int j = 1; j < paths.size(); j++) {
            pos = paths.get(j);
            x = pos[0];
            y = pos[1];
    
            if (board[x][y] == 0) {
                // 마지막 그룹 추가
                if (length > 0) {
                    groups.add(length);
                    groups.add(num);
                }
                break;
            }
    
            if (num != board[x][y]) {
                groups.add(length);
                groups.add(num);
                length = 1;
                num = board[x][y];
            } else {
                length++;
            }
        }
        
        // 마지막 그룹이 처리되지 않은 경우 추가
        if (length > 0 && board[x][y] != 0) {
            groups.add(length);
            groups.add(num);
        }
    
        int[][] newBoard = new int[n][n];
        for (int j = 0; j < Math.min(paths.size(), groups.size()); j++) {
            pos = paths.get(j);
            x = pos[0];
            y = pos[1];
            newBoard[x][y] = groups.get(j);
        }
        board = newBoard;
    }

    private static List<int[]> getPaths() {
        //좌, 하, 우, 상
        int[] tx = {0, 1, 0, -1};
        int[] ty = {-1, 0, 1, 0};

        List<int[]> path = new ArrayList<>();

        int x = n / 2;
        int y = n / 2;
        int len = 1;
        int dir = 0;

        move:
        while (true) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < len; j++) {
                    x += tx[dir];
                    y += ty[dir];

                    if (x < 0 || y < 0 || x >= n || y >= n) {
                        break move;
                    }

                    path.add(new int[]{x, y});
                }
                dir = (dir + 1) % 4;
            }

            len++;
        }

        return path;
    }
}