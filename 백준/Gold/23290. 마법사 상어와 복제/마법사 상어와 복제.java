import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int N = 4;

    static int[] fishdx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] fishdy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] sharkdx = {-1, 0, 1, 0};
    static int[] sharkdy = {0, -1, 0, 1};
    static int maxExcludeFishes;
    static int[] maxExcludePath = new int[3], path = new int[3];
    static boolean[][] visited = new boolean[N][N];

    static Queue<Integer>[][] arr = new Queue[N][N];
    static int[][] smell = new int[N][N];
    static int sharkX, sharkY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = new ArrayDeque<>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;

            arr[x][y].add(d);
        }

        st = new StringTokenizer(br.readLine());
        sharkX = Integer.parseInt(st.nextToken()) - 1;
        sharkY = Integer.parseInt(st.nextToken()) - 1;

        for (int turn = 1; turn <= s; turn++) {
            Queue<Integer>[][] copied = copy();

            moveFishes();
            moveShark(turn);
            removeSmell(turn);
            copyMagic(copied);
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans += arr[i][j].size();
            }
        }
        System.out.println(ans);
    }

    private static Queue<Integer>[][] copy() {
        Queue<Integer>[][] copied = new Queue[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copied[i][j] = new ArrayDeque<>();
                copied[i][j].addAll(arr[i][j]);
            }
        }

        return copied;
    }

    private static void moveFishes() {
        Queue<Integer>[][] nextArr = new Queue[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                nextArr[i][j] = new ArrayDeque<>();
            }
        }

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {

                Queue<Integer> fishQu = arr[x][y];
                int size = fishQu.size();

                for (int s = 0; s < size; s++) {
                    int dir = fishQu.poll();
                    boolean canMove = false;

                    for (int d = 0; d < 8; d++) {
                        int nx = x + fishdx[dir];
                        int ny = y + fishdy[dir];

                        if (nx < 0 || ny < 0 || nx >= N || ny >= N || (nx == sharkX && ny == sharkY) || smell[nx][ny] > 0) {
                            dir = (dir - 1 + 8) % 8;
                            continue;
                        }

                        nextArr[nx][ny].add(dir);
                        canMove = true;
                        break;
                    }

                    if (!canMove) {
                        nextArr[x][y].add(dir);
                    }
                }
            }
        }

        arr = nextArr;
    }

    private static void moveShark(int turn) {
        maxExcludeFishes = -1;
        path = new int[3];
        maxExcludePath = new int[3];

        dfs(sharkX, sharkY, 0);

        for (int d : maxExcludePath) {
            sharkX += sharkdx[d];
            sharkY += sharkdy[d];

            if (!arr[sharkX][sharkY].isEmpty()) {
                smell[sharkX][sharkY] = turn;
                arr[sharkX][sharkY].clear();
            }
        }
    }

    private static void dfs(int x, int y, int depth) {
        if (depth == 3) {
            int fishes = calcFishes();
            if (fishes > maxExcludeFishes) {
                maxExcludeFishes = fishes;
                maxExcludePath = path.clone();
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + sharkdx[i];
            int ny = y + sharkdy[i];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                continue;
            }

            path[depth] = i;
            dfs(nx, ny, depth + 1);
        }
    }

    private static int calcFishes() {
        boolean[][] visited = new boolean[N][N];
        int x = sharkX;
        int y = sharkY;
        int sum = 0;

        for (int d : path) {
            x += sharkdx[d];
            y += sharkdy[d];

            if (x < 0 || y < 0 || x >= N || y >= N) {
                return -1;
            }

            if (!visited[x][y]) {
                visited[x][y] = true;
                sum += arr[x][y].size();
            }
        }

        return sum;
    }

    private static void removeSmell(int turn) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (smell[i][j] > 0 && smell[i][j] == (turn - 2)) {
                    smell[i][j] = 0;
                }
            }
        }
    }

    private static void copyMagic(Queue<Integer>[][] copied) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                while (!copied[i][j].isEmpty()) {
                    arr[i][j].add(copied[i][j].poll());
                }
            }
        }
    }
}