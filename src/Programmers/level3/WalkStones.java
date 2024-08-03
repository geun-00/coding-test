package Programmers.level3;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/64062?language=java">프로그래머스 - Lv.3 : 징검다리 건너기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%A7%95%EA%B2%80%EB%8B%A4%EB%A6%AC-%EA%B1%B4%EB%84%88%EA%B8%B0">velog</a>
 * @since 2024-08-02
 */
public class WalkStones {

    static int[] arr;
    public static void main(String[] args) {

        System.out.println(solution(new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3));
    }

    private static int solution(int[] stones, int k) {

        arr = stones;

        int min = Integer.MAX_VALUE;
        int max = 0;

        for (int stone : stones) {
            min = Math.min(min, stone);
            max = Math.max(max, stone);
        }

        int s = min;
        int e = max;
        int result = 0;

        while (s <= e) {
            int mid = (s + e) / 2;

            if (check(mid, k)) {
                s = mid + 1;
                result = Math.max(result, mid);
            } else {
                e = mid - 1;
            }
        }

        return result;
    }

    private static boolean check(int mid, int k) {

        int count = 0;

        for (int i : arr) {
            if (i - mid < 0) {
                count++;
                if (count == k) {
                    return false;
                }
            } else {
                count = 0;
            }
        }

        return true;
    }
}
