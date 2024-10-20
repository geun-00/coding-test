package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/21608">백준 21608번 - 구현 : 상어 초등학교</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-21608%EB%B2%88-%EC%83%81%EC%96%B4-%EC%B4%88%EB%93%B1%ED%95%99%EA%B5%90">velog</a>
 *
 * @since 2024-10-14
 */
public class BJ_21608 {

    public static void main(String[] args) throws IOException {

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] order = new int[n * n + 1];      //학생의 순서
        int[][] arr = new int[n * n + 1][4];   //각 학생이 좋아하는 4명의 학생
        int[][] board = new int[n + 1][n + 1]; //각 학생의 위치

        for (int i = 1; i <= n * n; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            order[i] = s;

            for (int j = 0; j < 4; j++) {
                arr[s][j] = Integer.parseInt(st.nextToken());
            }
        }

        //첫 번째 학생 위치 지정
        board[2][2] = order[1];

        //2번째 ~ 마지막 전까지 학생 위치 지정
        for (int i = 2; i < n * n; i++) {

            int s = order[i]; //현재 학생

            //현재 학생이 위치할 수 있는 칸의 후보들
            ArrayList<Point> temp = new ArrayList<>();

            //빈 칸 중에서 좋아하는 학생이 인접한 칸과 빈 칸과 인접한 칸 개수 구하기
            for (int x = 1; x <= n; x++) {
                for (int y = 1; y <= n; y++) {

                    //이미 정해진 자리는 pass
                    if (board[x][y] > 0) continue;

                    int like = 0;
                    int empty = 0;

                    for (int d = 0; d < 4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];

                        if (nx > 0 && ny > 0 && nx <= n && ny <= n) {
                            //인접한 칸이 빈 칸
                            if (board[nx][ny] == 0) {
                                empty++;
                            } else {
                                for (int j = 0; j < 4; j++) {
                                    //인접한 칸이 좋아하는 학생의 칸
                                    if (board[nx][ny] == arr[s][j]) {
                                        like++;
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    temp.add(new Point(x, y, like, empty));
                }
            }

            //문제 조건에 따라 정렬
            temp.sort((o1, o2) -> {
                //좋아하는 학생이 인접한 칸에 가장 많은 칸
                if (o1.like != o2.like) {
                    return o2.like - o1.like;
                }
                //인접한 칸 중에서 비어있는 칸이 가장 많은 칸
                else if (o1.empty != o2.empty) {
                    return o2.empty - o1.empty;
                }
                //행의 번호가 가장 작은 칸
                else if (o1.x != o2.x) {
                    return o1.x - o2.x;
                }
                //열의 번호가 가장 작은 칸
                return o1.y - o2.y;
            });

            //정렬된 위치들 중 가장 첫번째 위치에 현재 학생 위치
            Point p = temp.get(0);
            board[p.x][p.y] = s;
        }

        //마지막 학생 위치 지정
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = order[n * n];
                    break;
                }
            }
        }

        //학생 만족도 구하기
        int result = 0;

        for (int x = 1; x <= n; x++) {
            for (int y = 1; y <= n; y++) {

                int s = board[x][y];
                int like = 0;

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx > 0 && ny > 0 && nx <= n && ny <= n) {
                        for (int j = 0; j < 4; j++) {
                            //인접한 칸이 좋아하는 학생
                            if (board[nx][ny] == arr[s][j]) {
                                like++;
                                break;
                            }
                        }
                    }
                }

                //인접한 칸에 좋아하는 학생이 앉은 수에 따라 만족도 계산
                if (like > 0) {
                    result += (int) Math.pow(10, like - 1);
                }
            }
        }

        System.out.println(result);
    }

    static class Point {

        int x, y, like, empty;

        public Point(int x, int y, int like, int empty) {
            this.x = x;
            this.y = y;
            this.like = like;
            this.empty = empty;
        }
    }
}
