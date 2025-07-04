import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        
        int n = words.length;
        
        Queue<State> qu = new ArrayDeque<>();
        qu.offer(new State(begin, 0));
        
        boolean[] visit = new boolean[n];
        
        while(!qu.isEmpty()) {
            
            State state = qu.poll();
            
            if(state.word.equals(target)) return state.step;
            
            for(int i = 0; i < n; i++) {
                
                if(visit[i] || !check(state.word, words[i])){
                    continue;
                }
                
                visit[i] = true;
                qu.offer(new State(words[i], state.step + 1));
            }
        }
        
        return 0;
    }
    
    public boolean check(String word, String next) {
        
        int diff = 0;
        
        for(int i = 0; i < word.length(); i++) {
            if(word.charAt(i) != next.charAt(i)){
                diff++;
            }
        }
        
        return diff == 1;
    }
    
    static class State {
        String word;
        int step;
        
        public State(String word, int step) {
            this.word = word;
            this.step = step;
        }
    }
}