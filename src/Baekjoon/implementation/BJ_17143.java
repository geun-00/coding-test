package Baekjoon.implementation;

/**
 * <a href = "https://www.acmicpc.net/problem/17143">백준 17143번 - 구현 : 낚시왕</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-17143%EB%B2%88-%EB%82%9A%EC%8B%9C%EC%99%95">velog</a>
 * @since 2024-08-15
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_17143 {

    static int r, c;
    static Shark[][] map;
    static int result = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new Shark[r + 1][c + 1];

        int m = Integer.parseInt(st.nextToken());

        if (m == 0) {
            System.out.println(0);
            return;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken()); //속력
            int d = Integer.parseInt(st.nextToken()); //이동 방향
            int z = Integer.parseInt(st.nextToken()); //크기

            map[x][y] = new Shark(s, d, z);
        }

        for (int i = 1; i <= c; i++) {
            getShark(i);
            moveShark();
        }

        System.out.println(result);
    }

    private static void moveShark() {

        Shark[][] newMap = new Shark[r + 1][c + 1];

        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {

                if (map[i][j] != null) {

                    Shark shark = map[i][j];

                    int nx = i, ny = j, nDir = shark.dir;
                    int speed = shark.speed;

                    switch (shark.dir) {
                        case 1: //상
                        case 2: //하
                            speed %= 2 * (r - 1);
                            for (int k = 0; k < speed; k++) {
                                if (nDir == 1 && nx == 1) {
                                    nDir = 2;
                                } else if (nDir == 2 && nx == r) {
                                    nDir = 1;
                                }
                                nx += (nDir == 1) ? -1 : 1;
                            }
                            break;
                        case 3: //우
                        case 4: //좌
                            speed %= 2 * (c - 1);
                            for (int k = 0; k < speed; k++) {
                                if (nDir == 3 && ny == c) {
                                    nDir = 4;
                                } else if (nDir == 4 && ny == 1) {
                                    nDir = 3;
                                }
                                ny += (nDir == 4) ? -1 : 1;
                            }
                            break;
                    }

                    shark.dir = nDir; //이동 방향만 바뀐 새로운 상어

                    //비어있거나 더 큰 상어일 때만 교체
                    if (newMap[nx][ny] == null || newMap[nx][ny].size < shark.size) {
                        newMap[nx][ny] = shark;
                    }
                }
            }
        }

        map = newMap;
    }

    private static void getShark(int col) {

        for (int i = 1; i <= r; i++) {
            if (map[i][col] != null) {
                result += map[i][col].size;
                map[i][col] = null;
                break;
            }
        }
    }

    static class Shark {
        int speed, dir, size;

        public Shark(int speed, int dir, int size) {
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }
    }
}