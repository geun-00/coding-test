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
        
        //0, 0에서 시작하면 직선과 코너를 구분하기 애매하다고 생각해 한 칸 이동한 상태에서 탐색 시작
        if (board[0][1] == 0) {
            qu.offer(new Car(0, 1, 100, 3)); //오른쪽
            cost[0][1][3] = 100;
        }
        if (board[1][0] == 0) {
            qu.offer(new Car(1, 0, 100, 1)); //아래
            cost[1][0][1] = 100;
        }
        
        while (!qu.isEmpty()) {
            
            Car now = qu.poll();

            if (now.x == n - 1 && now.y == n - 1) {
                min = Math.min(min, now.cost);
                continue;
            }

            //0=위, 1=아래, 2=왼쪽, 3=오른쪽
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n && board[nx][ny] == 0) {
                    
                    int newCost = now.cost + 100;
                    //코너 추가 비용
                    if (now.dir != i) {
                        newCost += 500;
                    }
                    //현재 방향으로의 이동이 최소 비용일 때만 유망한 경로라고 판단하고 큐에 추가
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
    int x, y; //위치
    int cost; //현재 비용
    int dir; //이동 방향

    public Car(int x, int y, int cost, int dir) {
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.dir = dir;
    }
}