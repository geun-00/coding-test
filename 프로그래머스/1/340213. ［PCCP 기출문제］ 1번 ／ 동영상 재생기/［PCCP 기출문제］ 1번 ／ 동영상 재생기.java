import java.util.*;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        
        StringTokenizer st = new StringTokenizer(video_len, ":");
        int video_min = stoi(st.nextToken()) * 60 + stoi(st.nextToken());
        
        st = new StringTokenizer(pos, ":");
        int pos_min = stoi(st.nextToken()) * 60 + stoi(st.nextToken());
        
        st = new StringTokenizer(op_start, ":");
        int start_min = stoi(st.nextToken()) * 60 + stoi(st.nextToken());
        
        st = new StringTokenizer(op_end, ":");
        int end_min = stoi(st.nextToken()) * 60 + stoi(st.nextToken());
        
        if(start_min <= pos_min && pos_min <= end_min) {
            pos_min = end_min;
        }
        
        for(String com : commands) {
            
            if("prev".equals(com)){
                pos_min = Math.max(0, pos_min - 10);
                
                if(start_min <= pos_min && pos_min <= end_min) {
                     pos_min = end_min;
                }
            }
            else if("next".equals(com)) {
                pos_min = Math.min(pos_min + 10, video_min);
                
                if(start_min <= pos_min && pos_min <= end_min) {
                    pos_min = end_min;
                }
            }
        }
        
        if(start_min <= pos_min && pos_min <= end_min) {
            pos_min = end_min;
        }
        
        String min = String.format("%02d", pos_min / 60);
        String sec = String.format("%02d", pos_min % 60);
        
        
        return min + ":" + sec;
    }
    
    public int stoi(String token){
        return Integer.parseInt(token);
    }
}