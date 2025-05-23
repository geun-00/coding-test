import java.util.ArrayList;
import java.util.List;

class Solution {
    int n;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    public int solution(int[][] game_board, int[][] table) {
        n = game_board.length;
        List<List<int[]>> gameBlocks = getBlocks(game_board, 0);
        List<List<int[]>> tableBlocks = getBlocks(table, 1);

        int ans = 0;
        boolean[] used = new boolean[tableBlocks.size()];

        iter:
        for (List<int[]> empty : gameBlocks) {

            for (int i = 0; i < tableBlocks.size(); i++) {
                List<int[]> block = tableBlocks.get(i);

                if (used[i] || empty.size() != block.size()) {
                    continue;
                }

                for (int j = 0; j < 4; j++) {
                    if (canCover(empty, block)) {
                        ans += block.size();
                        used[i] = true;
                        continue iter;
                    }
                    block = rotate(block);
                }
            }
        }

        return ans;
    }

    private List<List<int[]>> getBlocks(int[][] arr, int target) {
        boolean[][] visit = new boolean[n][n];
        List<List<int[]>> blocks = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visit[i][j] && arr[i][j] == target) {
                    List<int[]> temp = new ArrayList<>();
                    dfs(i, j, visit, arr, target, temp);
                    blocks.add(convert(temp));
                }
            }
        }

        return blocks;
    }

    private void dfs(int x, int y, boolean[][] visit, int[][] arr, int target, List<int[]> temp) {
        temp.add(new int[]{x, y});
        visit[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
            if (visit[nx][ny] || arr[nx][ny] != target) continue;

            dfs(nx, ny, visit, arr, target, temp);
        }
    }

    private boolean canCover(List<int[]> empty, List<int[]> block) {
        for (int i = 0; i < block.size(); i++) {
            int[] gameBlock = empty.get(i);
            int[] tableBlock = block.get(i);

            if (gameBlock[0] != tableBlock[0] || gameBlock[1] != tableBlock[1]) {
                return false;
            }
        }
        return true;
    }

    private List<int[]> rotate(List<int[]> block) {
        List<int[]> temp = new ArrayList<>();
        for (int[] pos : block) {
            temp.add(new int[]{pos[1], -pos[0]});
        }
        return convert(temp);
    }

    private List<int[]> convert(List<int[]> temp) {
        int minX = 51;
        int minY = 51;
        for (int[] pos : temp) {
            minX = Math.min(minX, pos[0]);
            minY = Math.min(minY, pos[1]);
        }

        for (int[] pos : temp) {
            pos[0] -= minX;
            pos[1] -= minY;
        }
        
        temp.sort((a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        return temp;
    }
}