package Programmers.level2;

import java.util.ArrayList;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/17677">프로그래머스 - Lv.2 : 뉴스 클러스터링</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-1%EC%B0%A8-%EB%89%B4%EC%8A%A4-%ED%81%B4%EB%9F%AC%EC%8A%A4%ED%84%B0%EB%A7%81">velog</a>
 *
 * @since 2024-07-14
 */
public class NewsClustering {
    public static void main(String[] args) {

        System.out.println(solution("FRANCE", "french"));
        System.out.println(solution("handshake", "shake hands"));
        System.out.println(solution("aa1+aa2", "AAAA12"));
        System.out.println(solution("E=M*C^2", "e=m*c^2"));
    }

    private static int solution(String str1, String str2) {

        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();

        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        for (int i = 0; i < str1.length() - 1; i++) {
            char c1 = str1.charAt(i);
            char c2 = str1.charAt(i + 1);

            if (isAlpha(c1) && isAlpha(c2)) {
                list1.add(str1.substring(i, i + 2));
            }
        }

        for (int i = 0; i < str2.length() - 1; i++) {
            char c1 = str2.charAt(i);
            char c2 = str2.charAt(i + 1);

            if (isAlpha(c1) && isAlpha(c2)) {
                list2.add(str2.substring(i, i + 2));
            }
        }

        int a = 0; //교집합
        int b = 0; //합집합

        for (String s : list1) {
            if (list2.remove(s)) {
                a++;
            }
            b++;
        }

        b += list2.size();

        if (b == 0) {
            return 65536;
        }

        return (int) (((double) a / b) * 65536);
    }

    private static boolean isAlpha(char ch) {
        return 'a' <= ch && ch <= 'z';
    }
}
