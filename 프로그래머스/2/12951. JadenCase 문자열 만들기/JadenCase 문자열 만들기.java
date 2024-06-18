class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        boolean newWord = true;

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