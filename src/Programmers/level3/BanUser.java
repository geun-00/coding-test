package Programmers.level3;

import java.util.Arrays;
import java.util.HashSet;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/64064">프로그래머스 - Lv.3 : 불량 사용자</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%B6%88%EB%9F%89-%EC%82%AC%EC%9A%A9%EC%9E%90">velog</a>
 * @since 2024-08-01
 */
public class BanUser {

    static HashSet<String> set;
    static boolean[] visit;
    static String[] user;
    static String[] banned;

    public static void main(String[] args) {
        System.out.println(solution(
                new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},
                new String[]{"fr*d*", "abc1**"}
        ));
        System.out.println(solution(
                new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},
                new String[]{"*rodo", "*rodo", "******"}
        ));
        System.out.println(solution(
                new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},
                new String[]{"fr*d*", "*rodo", "******", "******"}
        ));
    }

    private static int solution(String[] user_id, String[] banned_id) {
        set = new HashSet<>();
        user = user_id;
        banned = banned_id;

        visit = new boolean[user.length];

        solve(0, "");

        return set.size();
    }

    private static void solve(int depth, String s) {
        if (depth == banned.length) {
            String[] split = s.split(" ");
            Arrays.sort(split);

            StringBuilder sb = new StringBuilder();
            for (String str : split) {
                sb.append(str);
            }

            set.add(sb.toString());
            return;
        }

        for (int i = 0; i < user.length; i++) {

            if (!visit[i] && check(user[i], banned[depth])) {
                visit[i] = true;
                solve(depth + 1, s + " " + user[i]);
                visit[i] = false;
            }
        }
    }

    private static boolean check(String user, String ban) {

        if (user.length() != ban.length()) {
            return false;
        }

        for (int i = 0; i < user.length(); i++) {
            if (ban.charAt(i) != '*' && ban.charAt(i) != user.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}
