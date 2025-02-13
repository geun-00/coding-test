package Baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/21609">백준 21609번 - 시뮬레이션 : 상어 중학교</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-21609%EB%B2%88-%EC%83%81%EC%96%B4-%EC%A4%91%ED%95%99%EA%B5%90">velog</a>
 * @since 2025-02-03
 */
public class BJ_21609 {

    static final int EMPTY = -2;
    static final int BLACK = -1;
    static final int RAINBOW = 0;
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static int[][] map;
    static int n, m;
    static BlockGroup maxBlock = null;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;

        while (true) {
            maxBlock = new BlockGroup();

            if (!findBlockGroup()) break;
            ans += removeBlockGroup();
            gravity();
            rotate();
            gravity();
        }

        System.out.println(ans);
    }

    private static boolean findBlockGroup() {

        boolean found = false;

        boolean[][] visit = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] > 0 && !visit[i][j]) {

                    BlockGroup group = bfs(i, j, visit, map[i][j]);

                    if (group.blocks.size() < 2) continue;

                    found = true;
                    changeMaximum(group);
                }
            }
        }

        return found;
    }

    private static void changeMaximum(BlockGroup group) {
        if (maxBlock.blocks.size() < group.blocks.size()) {
            maxBlock = group;
        }
        else if (maxBlock.blocks.size() == group.blocks.size()) {

            if (maxBlock.rainbow < group.rainbow) {
                maxBlock = group;
            }
            else if(maxBlock.rainbow == group.rainbow) {

                int[] cur = maxBlock.getStandardBlock();
                int[] temp = group.getStandardBlock();

                if (cur[0] < temp[0]) {
                    maxBlock = group;
                }
                else if (cur[0] == temp[0] && cur[1] < temp[1]) {
                    maxBlock = group;
                }
            }
        }
    }

    private static int removeBlockGroup() {

        List<int[]> blocks = maxBlock.blocks;

        for (int[] block : blocks) {
            map[block[0]][block[1]] = EMPTY;
        }

        return blocks.size() * blocks.size();
    }

    private static void gravity() {

        for (int i = 0; i < n; i++) {

            Queue<Integer> col = new ArrayDeque<>();

            int index = n - 1; //채워질 시작 인덱스

            for (int j = n - 1; j >= 0; j--) {
                if (map[j][i] >= 0) {
                    col.offer(map[j][i]);
                    map[j][i] = EMPTY;
                }
                else if (map[j][i] == BLACK) {
                    while (!col.isEmpty()) {
                        map[index--][i] = col.poll();
                    }
                    index = j - 1;
                }
            }

            while (!col.isEmpty()) {
                map[index--][i] = col.poll();
            }
        }
    }

    /**
     * 시계 방향 90도 - (i, j) -> (j, n - 1 - i)
     *
     * 반시계 방향 90도 - (i, j) -> (n - 1 - j, i)
     */
    private static void rotate() {

        int[][] newMap = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newMap[n - 1 - j][i] = map[i][j];
            }
        }

        map = newMap;
    }

    private static BlockGroup bfs(int sx, int sy, boolean[][] visit, int blockNum) {

        visit[sx][sy] = true;

        BlockGroup group = new BlockGroup();
        group.blocks.add(new int[]{sx, sy});

        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{sx, sy});

        List<int[]> rainbows = new ArrayList<>();

        while (!qu.isEmpty()) {

            int[] temp = qu.poll();
            int x = temp[0];
            int y = temp[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if (visit[nx][ny] || map[nx][ny] == BLACK) continue;

                if (map[nx][ny] == RAINBOW || map[nx][ny] == blockNum) {
                    visit[nx][ny] = true;

                    int[] next = {nx, ny};
                    group.blocks.add(next);
                    qu.offer(next);

                    if (map[nx][ny] == RAINBOW){
                        group.rainbow++;
                        rainbows.add(next);
                    }
                }
            }
        }

        for (int[] block : rainbows) {
            visit[block[0]][block[1]] = false;
        }

        return group;
    }

    static class BlockGroup {

        List<int[]> blocks = new ArrayList<>();
        int rainbow;

        public int[] getStandardBlock() {
            return blocks.stream()
                         .filter(block -> map[block[0]][block[1]] != RAINBOW)
                         .min((o1, o2) -> {
                             if (o1[0] != o2[0]) return o1[0] - o2[0];
                             return o1[1] - o2[1];
                         })
                         .orElseThrow();
        }
    }
}
