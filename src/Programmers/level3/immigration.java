package Programmers.level3;

import java.util.Arrays;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/43238#qna">프로그래머스 - Lv.3 : 입국심사</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%9E%85%EA%B5%AD%EC%8B%AC%EC%82%AC">velog</a>
 * @since 2024-08-05
 */
public class immigration {
    public static void main(String[] args) {

        System.out.println(solution(6, new int[]{7, 10}));
    }

    private static long solution(int n, int[] times) {

        long s = 1;
        long e = (long) Arrays.stream(times).max().orElse(-1) * n;

/*
        //브루트포스
        long time = 0;
        while (true) {
            time++;
            long sum = 0;
            for (int t : times) {
                sum += time / t;
                if (sum >= n) {
                    return time;
                }
            }
        }
*/

        while (s <= e) {
            long mid = (s + e) / 2;

            long sum = 0;
            for (int time : times) {
                sum += mid / time;
                if (sum >= n) {
                    break;
                }
            }

            if (sum >= n) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }

        return s;
    }
}
