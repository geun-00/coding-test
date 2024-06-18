package Programmers.level2;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/12951">프로그래머스 - Lv.2 : JadenCase 문자열 만들기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-JadenCase-%EB%AC%B8%EC%9E%90%EC%97%B4-%EB%A7%8C%EB%93%A4%EA%B8%B0">velog</a>
 * @since 2024-06-18
 */
public class JadenCase {
    public static void main(String[] args) {

        System.out.println(solution("3people unFollowed me"));
        System.out.println(solution("for the last week"));
    }

    private static String solution(String s) {

        StringBuilder sb = new StringBuilder();
        boolean newWord = true; //새로운 단어 시작

        for (char c : s.toCharArray()) {
            if (c == ' ') {
                sb.append(" ");
                newWord = true;
            } else {
                if (newWord) {
                    sb.append(Character.toUpperCase(c));
                } else {
                    sb.append(Character.toLowerCase(c));
                }
                newWord = false;
            }
        }

        return sb.toString();
    }
}
