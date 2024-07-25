package Programmers.level3;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/43163">프로그래머스 - Lv.3 : 단어 변환</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%8B%A8%EC%96%B4-%EB%B3%80%ED%99%98">velog</a>
 *
 * @since 2024-07-25
 */
public class WordChange {

    static int min;
    static boolean[] visit;

    public static void main(String[] args) {

        System.out.println(solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
        System.out.println(solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log"}));
    }

    private static int solution(String begin, String target, String[] words) {

        min = Integer.MAX_VALUE;
        visit = new boolean[words.length];

        dfs(begin, target, words, 0);

        return min == Integer.MAX_VALUE ? 0 : min;
    }

    private static void dfs(String now, String target, String[] words, int depth) {
        if (now.equals(target)) {
            min = Math.min(min, depth);
            return;
        }

        for (int i = 0; i < words.length; i++) {

            if (visit[i]) {
                continue;
            }

            int diff = 0;

            for (int j = 0; j < now.length(); j++) {
                if (words[i].charAt(j) != now.charAt(j)) {
                    diff++;
                    if (diff >= 2) {
                        break;
                    }
                }
            }

            if (diff == 1) {
                visit[i] = true;
                dfs(words[i], target, words, depth + 1);
                visit[i] = false;
            }
        }
    }
}
