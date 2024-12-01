class Solution {
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {

        int video_min = convert(video_len);
        int pos_min = convert(pos);
        int start_min = convert(op_start);
        int end_min = convert(op_end);
              
        pos_min = posCheck(pos_min, start_min, end_min);
                
        for(String com : commands) {
            
            if("prev".equals(com)) {
                
                pos_min = Math.max(0, pos_min - 10);                
                pos_min = posCheck(pos_min, start_min, end_min);
            }
            
            else if("next".equals(com)) {
                
                pos_min = Math.min(pos_min + 10, video_min);            
                pos_min = posCheck(pos_min, start_min, end_min);
            }
        }
        
        pos_min = posCheck(pos_min, start_min, end_min);
        
        String min = String.format("%02d", pos_min / 60);
        String sec = String.format("%02d", pos_min % 60);        
        
        return min + ":" + sec;
    }
    
    public int convert(String s){
        
        String[] arr = s.split(":");
        
        return Integer.parseInt(arr[0]) * 60 + Integer.parseInt(arr[1]);
    }
    
    public int posCheck(int pos_min, int start_min, int end_min) {
        
        if(start_min <= pos_min && pos_min <= end_min) {
            return end_min;
        }
        
        return pos_min;
    }
}