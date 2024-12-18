import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        
        Queue<Integer> bridge = new ArrayDeque<>();
        
        for(int i = 0; i < bridge_length; i++){
            bridge.offer(0);
        }
        
        int b_w = 0; //bridge weight
        int time = 0;
        int t_idx = 0; //truck index
        
        while(t_idx < truck_weights.length){
            
            b_w -= bridge.poll();
            
            int t_w = truck_weights[t_idx];
            
            if(b_w + t_w <= weight) {
                b_w += t_w;
                bridge.offer(t_w);
                t_idx++;
            } else {
                bridge.offer(0);
            }
            
            time++;
        }
        
        while(b_w > 0){
            b_w -= bridge.poll();
            time++;
        }
        
        return time;
    }
}