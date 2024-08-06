import java.util.*;

class Solution {
    public int solution(int[][] board) {
        
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int min = Integer.MAX_VALUE;
        int n = board.length;
        
        Queue<Car> qu = new ArrayDeque<>();

        int[][][] cost = new int[n][n][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 4; k++) {
                    cost[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        
        if (board[0][1] == 0) {
            qu.offer(new Car(0, 1, 100, 3));
            cost[0][1][3] = 100;
        }
        if (board[1][0] == 0) {
            qu.offer(new Car(1, 0, 100, 1));
            cost[1][0][1] = 100;
        }
        
        while (!qu.isEmpty()) {
            Car now = qu.poll();

            if (now.x == n - 1 && now.y == n - 1) {
                min = Math.min(min, now.cost);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n && board[nx][ny] == 0) {
                    int newCost = now.cost + 100;
                    if (now.dir != i) {
                        newCost += 500;
                    }
                    if (cost[nx][ny][i] > newCost) {
                        cost[nx][ny][i] = newCost;
                        qu.offer(new Car(nx, ny, newCost, i));
                    }
                }
            }
        }

        return min;
    }
}

class Car {
    int x, y;
    int cost;
    int dir;

    public Car(int x, int y, int cost, int dir) {
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.dir = dir;
    }
}