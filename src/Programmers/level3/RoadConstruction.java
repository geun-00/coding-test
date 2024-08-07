package Programmers.level3;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/67259">프로그래머스 - Lv.3 : 경주로 건설</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EA%B2%BD%EC%A3%BC%EB%A1%9C-%EA%B1%B4%EC%84%A4">velog</a>
 * @since 2024-08-06
 */
public class RoadConstruction {

    public static void main(String[] args) {

        System.out.println(solution(new int[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0},
        }));


        System.out.println(solution(new int[][]{
                {0, 0, 1, 0},
                {0, 0, 0, 0},
                {0, 1, 0, 1},
                {1, 0, 0, 0},
        }));
        System.out.println(solution(new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0},
        }));
        System.out.println(solution(new int[][]{
                {0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 0},
                {0, 0, 1, 0, 0, 0},
                {1, 0, 0, 1, 0, 1},
                {0, 1, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0},
        }));
    }

    private static int solution(int[][] board) {

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

            //0=위, 1=아래, 2=왼쪽, 3=오른쪽
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

    static class Car {
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
}
