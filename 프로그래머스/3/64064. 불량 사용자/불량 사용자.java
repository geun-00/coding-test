import java.util.*;

class Solution {
    
    HashSet<String> set;
    boolean[] visit;
    String[] user;
    String[] banned;
    
    public int solution(String[] user_id, String[] banned_id) {
        
        set = new HashSet<>();
        user = user_id;
        banned = banned_id;

        visit = new boolean[user.length];

        solve(0, "");

        return set.size();
    }
    
    public void solve(int depth, String s){
        if (depth == banned.length) {
            String[] split = s.split("");
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
                solve(depth + 1, s + user[i]);
                visit[i] = false;
            }
        }
    }
    
    public boolean check(String user, String ban) {

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