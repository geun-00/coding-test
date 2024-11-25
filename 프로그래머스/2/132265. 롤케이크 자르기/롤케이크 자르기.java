import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] topping) {
        
        HashMap<Integer, Integer> left = new HashMap<>();
        HashMap<Integer, Integer> right = new HashMap<>();
        
        for(int i : topping){
            right.put(i, right.getOrDefault(i, 0) + 1);
        }
        
        int ans = 0;
        
        for(int i : topping){
            
            left.put(i, left.getOrDefault(i, 0) + 1);
            
            right.put(i, right.get(i) - 1);
            
            if(right.get(i) == 0){
                right.remove(i);
            }
            
            if(left.size() == right.size()){
                ans++;
            }
        }
        
        return ans;
    }
}