class Solution {
    public String solution(String s, int n) {
        
        StringBuilder sb = new StringBuilder();
        
        for(char c : s.toCharArray()) {
            
            if(c == ' '){
                sb.append(" ");
                continue;
            }
            
            int first = Character.isUpperCase(c) ? 'A' : 'a';
            
            sb.append((char) ((c - first + n) % 26 + first));
            
        }
        
        return sb.toString();
    }
}