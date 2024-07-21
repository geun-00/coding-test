package Programmers.level2;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/92335">프로그래머스 - Lv.2 : k진수에서 소수 개수 구하기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-k%EC%A7%84%EC%88%98%EC%97%90%EC%84%9C-%EC%86%8C%EC%88%98-%EA%B0%9C%EC%88%98-%EA%B5%AC%ED%95%98%EA%B8%B0">velog</a>
 * @since 2024-07-18
 */
public class KBase {
    public static void main(String[] args) {
        System.out.println(solution(437674, 3));
        System.out.println(solution(110011, 10));
        System.out.println(solution(797161, 3));
    }

    private static int solution(int n, int k) {

        String s = Integer.toString(n, k);
        String[] split = s.split("0");

        int count = 0;

        for (String str : split) {
            if (str.isEmpty()) {
                continue;
            }

            if (isPrime(Long.parseLong(str))) {
                count++;
            }
        }

        return count;
    }

    private static boolean isPrime(long n) {

        if (n == 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
}
