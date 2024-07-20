import java.util.ArrayList;

class Solution {
    
    char[] vowel = {'A', 'E', 'I', 'O', 'U'};
    ArrayList<String> words = new ArrayList<>();
    
    public int solution(String word) {
        
        dfs(0, "");
        
        return words.indexOf(word);
    }
    
    public void dfs(int depth, String s){
        
        words.add(s);
        
        if(depth == 5) {
            return;
        }
        
        for(char c : vowel){
            dfs(depth + 1, s + c);
        }
    }
}