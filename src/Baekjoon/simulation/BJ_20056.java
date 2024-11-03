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
 * <a href = "https://www.acmicpc.net/problem/20056">백준 20056번 - 시뮬레이션 : 마법사 상어와 파이어볼</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-20056%EB%B2%88-%EB%A7%88%EB%B2%95%EC%82%AC-%EC%83%81%EC%96%B4%EC%99%80-%ED%8C%8C%EC%9D%B4%EC%96%B4%EB%B3%BC">velog</a>
 *
 * @since 2024-10-31
 */
public class BJ_20056 {

    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static List<Fireball> fireballs = new ArrayList<>();
    static Queue<Fireball>[][] map;
    static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        map = new Queue[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = new ArrayDeque<>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int X = Integer.parseInt(st.nextToken()) - 1;
            int Y = Integer.parseInt(st.nextToken()) - 1;
            int M = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());

            fireballs.add(new Fireball(X, Y, M, S, D));
        }

        for (int i = 0; i < k; i++) {
            move();
            afterMove();
        }

        int sum = 0;
        for (Fireball f : fireballs) {
            sum += f.m;
        }

        System.out.println(sum);
    }

    private static void move() {

        for (Fireball f : fireballs) {

            f.x = (f.x + dx[f.d] * (f.s % n) + n) % n;
            f.y = (f.y + dy[f.d] * (f.s % n) + n) % n;

            map[f.x][f.y].offer(f);
        }
    }

    private static void afterMove() {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                Queue<Fireball> qu = map[i][j];
                int size = qu.size();

                if (size < 2) {
                    qu.clear();
                    continue;
                }

                //2개 이상의 파이어볼이 있는 칸
                int sum_m = 0;
                int sum_s = 0;
                int even = 0, odd = 0;

                while (!qu.isEmpty()) {

                    Fireball f = qu.poll();
                    sum_m += f.m;
                    sum_s += f.s;

                    if (f.d % 2 == 0) even++;
                    else odd++;

                    //새로운 파이어볼로 나누어지기 때문에 전체 파이어볼에서 제거
                    fireballs.remove(f);
                }

                sum_m /= 5;
                sum_s /= size;

                //질량이 0 이상일 때만 진행
                if (sum_m > 0) {
                    for (int d = (even == size || odd == size) ? 0 : 1; d < 8; d += 2) {
                        fireballs.add(new Fireball(i, j, sum_m, sum_s, d));
                    }
                }
            }
        }
    }

    static class Fireball {

        int x, y, m, s, d;

        public Fireball(int x, int y, int m, int s, int d) {
            this.x = x;
            this.y = y;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
}