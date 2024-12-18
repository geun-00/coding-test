package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/17281">백준 17281번 - 구현 : ⚾</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-17281%EB%B2%88-">velog</a>
 *
 * @since 2024-12-05
 */
public class BJ_17281 {

    static int[][] player;
    static int[] order = new int[9];
    static int n;
    static int ans = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        player = new int[n][9];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                player[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        order[3] = 0;
        solve(1, (1 << 3));

        System.out.println(ans);
    }

    private static void solve(int nowOrder, int visit) {

        if (nowOrder == 9) {
            ans = Math.max(ans, play());
            return;
        }

        for (int i = 0; i < 9; i++) {

            if ((visit & (1 << i)) != 0) continue;

            order[i] = nowOrder;
            solve(nowOrder + 1, visit | (1 << i));
        }
    }

    private static int play() {

        int now = 0;    //현재 타순
        int score = 0;  //정해진 타순으로 얻는 점수

        for (int inning = 0; inning < n; inning++) {

            int out = 0;        //아웃 카운트
            int base = 0b000;
            /**
             * 진루 상태를 비트로 표현
             * 0b001 = 1루 진출
             * 0b010 = 2루 진출
             * 0b100 = 3루 진출
             * 0b011 = 1, 2루 진출
             * ...
             */

            while (out < 3) {

                switch (player[inning][order[now]]) {

                    case 0:
                        out++;
                        break;
                    case 1: // 안타
                        score += (base >> 2) & 1;   //3루에 주자가 있는지 확인
                        base = ((base << 1) & 0b111) | 0b001;   //모든 주자 한 루씩 진루하고 타자 1루 진출
                        break;

                    case 2: // 2루타
                        score += (base >> 1) & 1;   //2루에 주자가 있는지 확인
                        score += (base >> 2) & 1;   //3루에 주자가 있는지 확인
                        base = ((base << 2) & 0b111) | 0b010;   //모든 주자 두 루씩 진루하고 타자 2루 진출
                        break;

                    case 3: // 3루타
                        score += Integer.bitCount(base);    //모든 루에 주자가 있다면 최대 3점 획득
                        base = 0b100;   //루에 있던 주자들은 모두 홈으로 나가고 타자 3루 진출
                        break;

                    case 4: // 홈런
                        score += Integer.bitCount(base) + 1;    //모든 루에 주자가 있다면 타자 포함 최대 4점 획득
                        base = 0b000;   //타자 포함 모든 주자가 홈으로 나간다
                        break;
                }

                now = (now + 1) % 9;
            }
        }

        return score;
    }
}