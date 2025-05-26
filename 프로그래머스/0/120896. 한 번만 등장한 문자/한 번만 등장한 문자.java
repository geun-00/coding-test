class Solution {
    public String solution(String s) {
        String answer = "";
        char[] arr = new char[26];
        
        for (char ch : s.toCharArray()) {
            arr[ch - 'a']++;
        }
        
        for (int i = 0; i < 26; i++) {
            if (arr[i] == 1) {
                answer += (char) (i + 'a');
            }
        }
        return answer;
    }
}