package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/14891">백준 14891번 - 구현 : 톱니바퀴</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-14891%EB%B2%88-%ED%86%B1%EB%8B%88%EB%B0%94%ED%80%B4">velog</a>
 * @since 2024-07-22
 */
public class BJ_14891 {

    static String[] gears = new String[5];
    static boolean[] rotation; //톱니바퀴 회전 여부

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 1; i <= 4; i++) {
            gears[i] = br.readLine();
        }

        int k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            rotation = new boolean[5];

            int num = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            //회전하면 번호가 바뀌기 때문에 미리 변수에 저장시키기
            char gear1_2 = gears[1].charAt(2);

            char gear2_2 = gears[2].charAt(2);
            char gear2_6 = gears[2].charAt(6);

            char gear3_2 = gears[3].charAt(2);
            char gear3_6 = gears[3].charAt(6);

            char gear4_6 = gears[4].charAt(6);

            rotate(num, r); //해당 톱니바퀴 회전

            switch (num) {
                /**
                 * 1번 톱니바퀴
                 * 맞닿은 2번은 극이 다르면 반대 방향으로 회전
                 * 한 칸 떨어져있는 3번은 2번 회전여부에 따라 1번과 같은 방향으로 회전
                 * 두 칸 떨어져있는 4번은 3번 회전여부에 따라 1번과 반대 방향으로 회전
                 */
                case 1:
                    if (gear1_2 != gear2_6) {
                        rotate(2, -r);
                    }
                    if (gear2_2 != gear3_6 && rotation[2]) {
                        rotate(3, r);
                    }
                    if (gear3_2 != gear4_6 && rotation[3]) {
                        rotate(4, -r);
                    }
                    break;
                case 2:
                    /**
                     * 2번 톱니바퀴
                     * 맞닿은 1, 3번은 극이 다르면 반대 방향으로 회전
                     * 두 칸 떨어져있는 4번은 3번 회전여부에 따라 2번과 같은 방향으로 회전
                     */
                    if (gear1_2 != gear2_6) {
                        rotate(1, -r);
                    }
                    if (gear2_2 != gear3_6) {
                        rotate(3, -r);
                    }
                    if (gear3_2 != gear4_6 && rotation[3]) {
                        rotate(4, r);
                    }
                    break;
                case 3:
                    /**
                     * 3번 톱니바퀴
                     * 맞닿은 2, 4번은 극이 다르면 반대 방향으로 회전
                     * 두 칸 떨어져있는 1번은 2번 회전여부에 따라 3번과 같은 방향으로 회전
                     */
                    if (gear2_2 != gear3_6) {
                        rotate(2, -r);
                    }
                    if (gear3_2 != gear4_6) {
                        rotate(4, -r);
                    }
                    if (gear1_2 != gear2_6 && rotation[2]) {
                        rotate(1, r);
                    }
                    break;
                case 4:
                    /**
                     * 4번 톱니바퀴
                     * 맞닿은 3번은 극이 다르면 반대 방향으로 회전
                     * 한 칸 떨어져있는 2번은 3번 회전여부에 따라 4번과 같은 방향으로 회전
                     * 두 칸 떨어져있는 1번은 2번 회전여부에 따라 4번과 반대 방향으로 회전
                     */
                    if (gear3_2 != gear4_6) {
                        rotate(3, -r);
                    }
                    if (gear2_2 != gear3_6 && rotation[3]) {
                        rotate(2, r);
                    }
                    if (gear1_2 != gear2_6 && rotation[2]) {
                        rotate(1, -r);
                    }
                    break;
            }
        }

        //최종 점수 출력
        System.out.println(
                ((gears[1].charAt(0) - '0')) +
                ((gears[2].charAt(0) - '0') * 2) +
                ((gears[3].charAt(0) - '0') * 4) +
                ((gears[4].charAt(0) - '0') * 8)
        );
    }

    static void rotate(int num, int r) {

        rotation[num] = true;

        //시계 방향
        if (r == 1) {
            gears[num] = gears[num].charAt(7) + gears[num].substring(0, 7);

        //반시계 방향
        } else if (r == -1) {
            gears[num] = gears[num].substring(1) + gears[num].charAt(0);
        }
    }
}