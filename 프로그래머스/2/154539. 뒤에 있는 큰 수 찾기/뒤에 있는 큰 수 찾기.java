import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        
        int n = numbers.length;
        
        int[] answer = new int[n];
        
        Deque<Integer> stack = new ArrayDeque<>();
        
        for(int i = n - 1; i >= 0; i--){
            
            while(!stack.isEmpty() && stack.peek() <= numbers[i]){
                stack.pop();
            }
            
            if(stack.isEmpty()) {
                answer[i] = -1;
            } else {
                answer[i] = stack.peek();
            }
            
            stack.push(numbers[i]);
        }
        
        return answer;
    }
}