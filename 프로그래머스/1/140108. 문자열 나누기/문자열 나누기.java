class Solution {
    public int solution(String s) {
        int answer = 1;

        char x = s.charAt(0);
        int length = 1;
        int different = 0;

        for (int i = 1; i < s.length(); i++) {
            if (length == different) {
                answer++;
                length = 1;
                different = 0;
                x = s.charAt(i);
            }

            else if (s.charAt(i) == x) {
                length++;
            } else {
                different++;
            }
        }

        return answer;
    }
}