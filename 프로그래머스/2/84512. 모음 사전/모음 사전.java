class Solution {
    public int solution(String word) {
        char[] vowels = {'A', 'E', 'I', 'O', 'U'};
        int[] weights = {781, 156, 31, 6, 1};
        
        int index = 0;
        
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            for (int j = 0; j < 5; j++) {
                if (c == vowels[j]) {
                    index += weights[i] * j;
                    break;
                }
            }
        }
        
        return index + word.length();
    }
}
