package Programmers.level2;

import java.util.Arrays;
import java.util.HashSet;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/12981">프로그래머스 - Lv.2 : 영어 끝말잇기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%98%81%EC%96%B4-%EB%81%9D%EB%A7%90%EC%9E%87%EA%B8%B0">velog</a>
 * @since 2024-06-27
 */

public class EnglishWords {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(3, new String[]{"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"})));
        System.out.println(Arrays.toString(solution(5, new String[]{"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"})));
        System.out.println(Arrays.toString(solution(2, new String[]{"hello", "one", "even", "never", "now", "world", "draw"})));
    }

    private static int[] solution(int n, String[] words) {

        HashSet<String> used = new HashSet<>();
        used.add(words[0]);

        char prev = words[0].charAt(words[0].length() - 1);

        for (int i = 1; i < words.length; i++) {
            if (used.contains(words[i]) || prev != words[i].charAt(0)) {

                return new int[]{i % n + 1, i / n + 1};
            }

            used.add(words[i]);
            prev = words[i].charAt(words[i].length() - 1);
        }

        return new int[]{0, 0};
    }
}
