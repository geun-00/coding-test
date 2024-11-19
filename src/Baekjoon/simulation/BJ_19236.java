package Baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/19236">백준 19236번 - 시뮬레이션 : 청소년 상어</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-19236%EB%B2%88-%EC%B2%AD%EC%86%8C%EB%85%84-%EC%83%81%EC%96%B4">velog</a>
 *
 * @since 2024-11-14
 */
public class BJ_19236 {

    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

    static int[][] map;
    static Fish[] fish;

    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[4][4];    //(x, y) 위치에 있는 물고기의 번호
        fish = new Fish[17];    //각 물고기의 정보

        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 4; j++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken()) - 1;

                map[i][j] = a;
                fish[a] = new Fish(i, j, b);
            }
        }

        int firstFishNum = map[0][0];

        Shark init = new Shark(0, 0, fish[firstFishNum].dir, firstFishNum);

        fish[firstFishNum] = null;
        map[0][0] = 0;

        solve(init);

        System.out.println(max);
    }

    private static void solve(Shark shark) {

        //현재 상태 백업
        int[][] copy_map = getCopyMap();
        Fish[] copy_fish = getCopyFish();

        //물고기 이동
        moveFish(shark);

        boolean moved = false;

        //최대 3칸까지 이동
        for (int i = 1; i <= 3; i++) {

            int nx = shark.x + dx[shark.dir] * i;
            int ny = shark.y + dy[shark.dir] * i;

            if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4 || map[nx][ny] == 0) {
                continue;
            }

            moved = true;

            int temp = map[nx][ny];     //먹을 물고기가 있는 위치

            map[shark.x][shark.y] = 0;
            map[nx][ny] = 0;

            Fish eaten = fish[temp];
            fish[temp] = null;

            //상어 이동
            solve(new Shark(nx, ny, eaten.dir, shark.sum + temp));

            //복구
            map[nx][ny] = temp;
            fish[temp] = eaten;
        }

        if (!moved) {
            max = Math.max(max, shark.sum);
        }

        //원상복구
        restore(copy_map, copy_fish);
    }

    private static void restore(int[][] copy_map, Fish[] copy_fish) {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                map[i][j] = copy_map[i][j];
            }
        }

        for (int i = 1; i <= 16; i++) {
            fish[i] = copy_fish[i];
        }
    }

    private static Fish[] getCopyFish() {

        Fish[] copy_fish = new Fish[17];

        for (int i = 1; i <= 16; i++) {
            copy_fish[i] = fish[i];
        }

        return copy_fish;
    }

    private static int[][] getCopyMap() {

        int[][] copy_map = new int[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                copy_map[i][j] = map[i][j];
            }
        }

        return copy_map;
    }

    private static void moveFish(Shark shark) {

        //번호가 작은 물고기부터 순서대로 이동
        for (int i = 1; i <= 16; i++) {

            Fish f = fish[i];

            if (f == null) continue;

            //반시계 방향으로 45도씩 회전시키며 이동할 수 있는 칸 탐색
            for (int j = 0; j < 8; j++) {

                int dir = (f.dir + j) % 8;

                int nx = f.x + dx[dir];
                int ny = f.y + dy[dir];

                //경계를 넘거나 상어가 있는 칸 continue
                if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4 || (nx == shark.x && ny == shark.y)) {
                    continue;
                }

                //다른 물고기가 있는 칸
                if (map[nx][ny] != 0) {

                    int swapFishNum = map[nx][ny];       //바뀔 위치에 있는 물고기의 번호
                    Fish swapFish = fish[swapFishNum];   //바뀔 위치에 있는 물고기

                    //바뀔 위치에 현재 물고기의 위치 정보와 기존 물고기 방향 정보 새롭게 저장
                    fish[swapFishNum] = new Fish(f.x, f.y, swapFish.dir);
                    map[f.x][f.y] = swapFishNum;
                }
                //빈 칸
                else {
                    map[f.x][f.y] = 0;
                }

                //swap 되고 이동한 위치에 현재 물고기 정보 저장
                map[nx][ny] = i;
                fish[i] = new Fish(nx, ny, dir);

                //한바퀴 회전하기 전에 이동 가능하므로 break
                break;
            }
        }
    }

    static class Fish {

        int x, y, dir;

        public Fish(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    static class Shark {

        int x, y, dir, sum;

        public Shark(int x, int y, int dir, int sum) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.sum = sum;
        }
    }
}