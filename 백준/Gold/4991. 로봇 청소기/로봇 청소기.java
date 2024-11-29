import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] order;
    static char[][] arr;
    static int w, h;
    static ArrayList<Integer> dirtyPos;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] cleanerDist;
    static int[][] dirtyDist;

    static int ans;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        whileLoop:
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) {
                break;
            }

            ans = Integer.MAX_VALUE;
            arr = new char[h][w];

            int cleanerPos = 0;
            dirtyPos = new ArrayList<>();

            for (int i = 0; i < h; i++) {
                char[] chars = br.readLine().toCharArray();

                for (int j = 0; j < w; j++) {
                    arr[i][j] = chars[j];

                    int idx = i * w + j;

                    if (arr[i][j] == 'o') {
                        cleanerPos = idx;
                    } else if (arr[i][j] == '*') {
                        dirtyPos.add(idx);
                    }
                }
            }

            if (dirtyPos.isEmpty()) {
                sb.append(0).append("\n");
                continue;
            }

            int n = dirtyPos.size();

            cleanerDist = new int[n];
            dirtyDist = new int[n][n];

            if (!bfs(cleanerPos)) {
                sb.append(-1).append("\n");
                continue;
            }

            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {

                    int start = dirtyPos.get(i);
                    int target = dirtyPos.get(j);

                    int dist = bfs(start, target);

                    if (dist == -1) {
                        sb.append(-1).append("\n");
                        continue whileLoop;
                    }

                    dirtyDist[i][j] = dirtyDist[j][i] = dist;
                }
            }

            order = new int[n];
            getCombinations(0, 0, n);

            sb.append(ans).append("\n");

        }

        System.out.print(sb);
    }

    private static void getCombinations(int idx, int visit, int n) {

        if (idx == n) {
            calculateMin(n);
            return;
        }

        for (int i = 0; i < n; i++) {

            if ((visit & (1 << i)) != 0) {
                continue;
            }

            order[idx] = i;
            getCombinations(idx + 1, visit | (1 << i), n);
        }
    }

    private static void calculateMin(int n) {

        int sum = cleanerDist[order[0]];

        for (int i = 0; i < n - 1; i++) {
            sum += dirtyDist[order[i]][order[i + 1]];
        }

        ans = Math.min(ans, sum);
    }

    private static int bfs(int start, int target) {

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(start);

        boolean[] visit = new boolean[w * h];
        visit[start] = true;

        int moved = 0;

        while (!qu.isEmpty()) {

            int size = qu.size();

            for (int i = 0; i < size; i++) {

                int now = qu.poll();
                int x = now / w;
                int y = now % w;

                int idx = x * w + y;

                if (idx == target) {
                    return moved;
                }

                for (int d = 0; d < 4; d++) {

                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    idx = nx * w + ny;

                    if (rangeCheck(nx, ny) || visit[idx] || arr[nx][ny] == 'x') {
                        continue;
                    }

                    visit[idx] = true;
                    qu.offer(idx);
                }
            }

            moved++;
        }

        return -1;
    }

    private static boolean bfs(int start) {

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(start);

        boolean[] visit = new boolean[w * h];
        visit[start] = true;

        int moved = 0;
        int found = 0;

        while (!qu.isEmpty()) {

            int size = qu.size();

            for (int i = 0; i < size; i++) {

                int now = qu.poll();
                int x = now / w;
                int y = now % w;

                int idx = x * w + y;

                if (dirtyPos.contains(idx)) {
                    cleanerDist[dirtyPos.indexOf(idx)] = moved;
                    found++;

                    if (found == dirtyPos.size()) {
                        return true;
                    }
                }


                for (int d = 0; d < 4; d++) {

                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    idx = nx * w + ny;

                    if (rangeCheck(nx, ny) || visit[idx] || arr[nx][ny] == 'x') {
                        continue;
                    }

                    visit[idx] = true;
                    qu.offer(idx);
                }
            }

            moved++;
        }

        return false;
    }

    private static boolean rangeCheck(int nx, int ny) {
        return nx < 0 || ny < 0 || nx >= h || ny >= w;
    }
}