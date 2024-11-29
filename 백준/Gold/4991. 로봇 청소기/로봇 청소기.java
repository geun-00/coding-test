import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static ArrayList<Integer> dirtyPos;     //더러운 칸의 위치
    static int[] order;                     //더러운 칸을 이동하는 순서
    static int[] cleanerDist;               //로봇 청소기에서 각 더러운 칸까지의 최단 경로
    static int[][] dirtyDist;               //더러운 칸끼리 최단 경로

    static char[][] arr;
    static int w, h;

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

            dirtyPos = new ArrayList<>();
            int cleanerPos = 0;

            for (int i = 0; i < h; i++) {

                char[] chars = br.readLine().toCharArray();

                for (int j = 0; j < w; j++) {
                    arr[i][j] = chars[j];

                    int idx = i * w + j;

                    if (arr[i][j] == 'o') {
                        cleanerPos = idx;
                    }
                    else if (arr[i][j] == '*') {
                        dirtyPos.add(idx);
                    }
                }
            }

            //처음부터 이동할 필요 없는 경우
            if (dirtyPos.isEmpty()) {
                sb.append(0).append("\n");
                continue;
            }

            int n = dirtyPos.size();

            cleanerDist = new int[n];
            dirtyDist = new int[n][n];

            //로봇 청소기 위치에서 각 더러운 칸까지 최단 경로를 구한다.
            //만약 모든 더러운 칸을 방문할 수 없다면 -1 출력
            if (!bfs(cleanerPos)) {
                sb.append(-1).append("\n");
                continue;
            }

            //더러운 칸끼리 최단 경로를 구한다.
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {

                    int start = dirtyPos.get(i);
                    int target = dirtyPos.get(j);

                    int dist = bfs(start, target);

                    //방문할 수 없다면 -1 출력
                    if (dist == -1) {
                        sb.append(-1).append("\n");
                        continue whileLoop;
                    }

                    dirtyDist[i][j] = dirtyDist[j][i] = dist;
                }
            }

            order = new int[n];
            //백트래킹으로 이동 순서에 대한 모든 조합 확인
            getCombinations(0, 0, n);

            sb.append(ans).append("\n");
        }

        System.out.print(sb);
    }

    /**
     * 로봇 청소기 위치에서 시작하는 BFS
     * @param start 로봇 청소기 위치
     * @return 모든 더러운 칸을 방문할 수 있는지 여부
     */
    private static boolean bfs(int start) {

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(start);

        boolean[] visit = new boolean[w * h];
        visit[start] = true;

        int moved = 0;  //이동 횟수
        int found = 0;  //더러운 칸 만난 횟수

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

                searchAdjNode(x, y, visit, qu);
            }

            moved++;
        }

        return false;
    }

    /**
     * 각 더러운 칸에서 시작하는 BFS
     * @param start 시작 더러운 칸
     * @param target 방문할 더러운 칸
     * @return 최소 이동 횟수, 방문할 수 없으면 -1
     */
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

                searchAdjNode(x, y, visit, qu);
            }

            moved++;
        }

        return -1;
    }

    private static void searchAdjNode(int x, int y, boolean[] visit, Queue<Integer> qu) {

        for (int d = 0; d < 4; d++) {

            int nx = x + dx[d];
            int ny = y + dy[d];

            int idx = nx * w + ny;

            if (rangeCheck(nx, ny) || visit[idx] || arr[nx][ny] == 'x') {
                continue;
            }

            visit[idx] = true;
            qu.offer(idx);
        }
    }

    private static void getCombinations(int idx, int visit, int n) {

        if (idx == n) {
            calculateMin(n);
            return;
        }

        for (int i = 0; i < n; i++) {

            if ((visit & (1 << i)) != 0) continue;

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

    private static boolean rangeCheck(int nx, int ny) {
        return nx < 0 || ny < 0 || nx >= h || ny >= w;
    }
}