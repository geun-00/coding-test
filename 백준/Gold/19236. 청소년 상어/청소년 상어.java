import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[][] map;
    static Fish[] fish;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[5][5];
        fish = new Fish[17];

        for (int i = 1; i <= 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 4; j++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken()) - 1;

                map[i][j] = a;
                fish[a] = new Fish(i, j, b);
            }
        }

        Shark init = new Shark(1, 1, fish[map[1][1]].dir, map[1][1]);
        fish[map[1][1]] = null;
        map[1][1] = 0;

        solve(init);

        System.out.println(max);
    }

    private static void solve(Shark shark) {

        int[][] copy_map = new int[5][5];
        Fish[] copy_fish = new Fish[17];

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                copy_map[i][j] = map[i][j];
            }
        }

        for (int i = 1; i <= 16; i++) {
            Fish f = fish[i];
            if (f != null) {
                copy_fish[i] = new Fish(f.x, f.y, f.dir);
            } else {
                copy_fish[i] = null;
            }
        }

        //물고기 이동
        moveFish(shark);

        boolean moved = false;

        for (int i = 1; i <= 3; i++) {

            int nx = shark.x + dx[shark.dir] * i;
            int ny = shark.y + dy[shark.dir] * i;

            if (nx < 1 || ny < 1 || nx > 4 || ny > 4 || map[nx][ny] == 0) {
                continue;
            }

            moved = true;

            int temp = map[nx][ny];
            map[shark.x][shark.y] = 0;
            map[nx][ny] = 0;
            Fish eaten = fish[temp];
            fish[temp] = null;

            solve(new Shark(nx, ny, eaten.dir, shark.sum + temp));

            map[nx][ny] = temp;
            map[shark.x][shark.y] = 0;
            fish[temp] = eaten;
        }

        if (!moved) {
            max = Math.max(max, shark.sum);
        }

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                map[i][j] = copy_map[i][j];
            }
        }

        for (int i = 1; i <= 16; i++) {
            fish[i] = copy_fish[i];
        }
    }

    private static void moveFish(Shark shark) {

        for (int i = 1; i <= 16; i++) {

            Fish f = fish[i];

            if (f == null) continue;

            for (int j = 0; j < 8; j++) {

                int dir = (f.dir + j) % 8;

                int nx = f.x + dx[dir];
                int ny = f.y + dy[dir];

                if (nx < 1 || ny < 1 || nx > 4 || ny > 4 || (nx == shark.x && ny == shark.y)) {
                    continue;
                }

                if (map[nx][ny] != 0) {
                    int temp = map[nx][ny];
                    Fish temp_f = fish[temp];
                    fish[temp] = new Fish(f.x, f.y, temp_f.dir);
                    map[f.x][f.y] = temp;
                } else {
                    map[f.x][f.y] = 0;
                }

                map[nx][ny] = i;
                fish[i] = new Fish(nx, ny, dir);
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

        public void eat(int num) {
            this.sum += num;
        }
    }
}