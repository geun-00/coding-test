package Programmers.level2;

import java.util.ArrayList;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/84512">프로그래머스 - Lv.2 : 모음사전</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%AA%A8%EC%9D%8C%EC%82%AC%EC%A0%84">velog</a>
 * @since 2024-07-20
 */
public class VowelDict {

    static char[] vowel = {'A', 'E', 'I', 'O', 'U'};
    static int[] weight = {781, 156, 31, 6, 1};
    static ArrayList<String> words;

    public static void main(String[] args) {

        System.out.println(solution("AAAAE"));
        System.out.println(solution("AAAE"));
        System.out.println(solution("I"));
        System.out.println(solution("EIO"));
        System.out.println(solution("UUUUU"));
    }

    private static int solution(String word) {

        int index = 0;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            for (int j = 0; j < 5; j++) {
                if (c == vowel[j]) {
                    index += weight[i] * j;
                    break;
                }
            }
        }

        return index + word.length();

//        words = new ArrayList<>();
//
//        dfs(0, "");
//
//        return words.indexOf(word);

    }

    private static void dfs(int depth, String s) {

        words.add(s);

        if (depth == 5) {
            return;
        }

        for (char c : vowel) {
            dfs(depth + 1, s + c);
        }
    }
}
