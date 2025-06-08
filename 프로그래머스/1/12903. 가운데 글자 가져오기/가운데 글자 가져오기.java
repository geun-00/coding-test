class Solution {
    public String solution(String s) {
        if (s.length() % 2 == 0) {
            int m = s.length() / 2 - 1;
            return s.substring(m, m + 2);
        }
        
        return s.charAt(s.length() / 2) + "";
    }
}