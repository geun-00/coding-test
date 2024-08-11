package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/15684">백준 15684번 - 구현 : 사다리 조작</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-15684%EB%B2%88-%EC%82%AC%EB%8B%A4%EB%A6%AC-%EC%A1%B0%EC%9E%91">velog</a>
 * @since 2024-08-10
 */
public class BJ_15684 {

    static String[][] ladder;
    static int n, m, h;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        ladder = new String[h + 1][n + 1];

        for (int i = 1; i <= h; i++) {
            Arrays.fill(ladder[i], "|");
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            ladder[a][b] = "|-";
        }

        //0~3개 가로선 추가
        for (int i = 0; i <= 3; i++) {
            solve(i, 0, 1);
        }

        System.out.println(-1);
    }

    /**
     * @param target 설치할 추가 가로선 개수
     * @param depth  현재까지 설치한 추가 가로선 개수
     * @param row    가로선 번호
     */
    private static void solve(int target, int depth, int row) {
        if (depth == target) {
            /**
             * 목표 개수만큼 가로선을 추가로 설치했다면 제대로 조작되었는지 확인한다.
             * 제대로 조작되었다면 설치한 가로선 개수를 출력하고 프로그램 종료
             * 적은 개수부터 탐색하므로 바로 종료해도 된다.
             */
            if (check()) {
                System.out.println(target);
                System.exit(0);
            }
            return;
        }

        for (int i = row; i <= h; i++) {
            for (int j = 1; j < n; j++) {
                //가로선은 연속해서 있으면 안되기 때문에 현재, 이전, 다음 세로선 확인
                if (ladder[i][j].equals("|") && ladder[i][j - 1].equals("|") && ladder[i][j + 1].equals("|")) {

                    /**
                     * 가로선 추가
                     * 같은 가로에 또 가로선을 놓을 수 있는지 확인
                     * 다른 경우의 수를 보기 위해 추가했던 가로선 제거
                     */
                    ladder[i][j] = "|-";
                    solve(target, depth + 1, i);
                    ladder[i][j] = "|";
                }
            }
        }
    }

    private static boolean check() {

        for (int i = 1; i <= n; i++) {
            int now = i;
            for (int j = 1; j <= h; j++) {
                //현재 내려가는 세로선에서 다음 세로선으로 연결된 가로선이 있는 경우
                if (ladder[j][now].equals("|-")) {
                    now++;
                }
                //이전 세로선에서 현재 세로선으로 연결된 가로선이 있는 경우
                else if (ladder[j][now - 1].equals("|-")) {
                    now--;
                }
            }

            //한개의 세로선이라도 같은 세로선으로 도착하지 않으면 불가능
            if (now != i) {
                return false;
            }
        }

        return true;
    }
}
