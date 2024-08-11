package Programmers.level3;

import java.util.Arrays;
import java.util.HashMap;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/77486">프로그래머스 : Lv.3 : 다단계 칫솔 판매</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%8B%A4%EB%8B%A8%EA%B3%84-%EC%B9%AB%EC%86%94-%ED%8C%90%EB%A7%A4">velog</a>
 * @since 2024-08-11
 */
public class SellToothBrush {

    static HashMap<String, String> toParent;
    static HashMap<String, Integer> gain;

    public static void main(String[] args) {

        System.out.println(Arrays.toString(solution(
                new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                new String[]{"young", "john", "tod", "emily", "mary"},
                new int[]{12, 4, 2, 5, 10}
        )));

        System.out.println(Arrays.toString(solution(
                new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                new String[]{"sam", "emily", "jaimie", "edward"},
                new int[]{2, 3, 5, 4}
        )));
    }

    private static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {

        toParent = new HashMap<>();
        gain = new HashMap<>();

        int n = enroll.length;
        for (int i = 0; i < n; i++) {
            toParent.put(enroll[i], referral[i]);
            gain.put(enroll[i], 0);
        }

        for (int i = 0; i < seller.length; i++) {
            solve(seller[i], amount[i] * 100);
        }

        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            result[i] = gain.get(enroll[i]);
        }

        return result;
    }

    private static void solve(String s, int amount) {

        if (s.equals("-") || amount == 0) {
            return;
        }

        gain.put(s, gain.get(s) + amount - (amount / 10));
        solve(toParent.get(s), amount / 10);
    }
}