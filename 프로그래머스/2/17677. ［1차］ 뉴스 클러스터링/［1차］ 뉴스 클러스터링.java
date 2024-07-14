import java.util.ArrayList;

class Solution {
    public int solution(String str1, String str2) {
        
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        for(int i = 0; i < str1.length() - 1; i++){
            char c1 = str1.charAt(i);
            char c2 = str1.charAt(i + 1);
            
            if(isAlpha(c1) && isAlpha(c2)){
                list1.add(str1.substring(i, i + 2));
            }
        }
        
        for(int i = 0; i < str2.length() - 1; i++){
            char c1 = str2.charAt(i);
            char c2 = str2.charAt(i + 1);
            
            if(isAlpha(c1) && isAlpha(c2)){
                list2.add(str2.substring(i, i + 2));
            }
        }
        
        int a = 0; //교집합
        int b = 0; //합집합
        
        for(String s : list1){
            if(list2.remove(s)){
                a++;
            }
            b++;
        }
        
        b += list2.size();
        
        if(b == 0){
            return 65536;
        } else { 
            return (int) (((double) a / b) * 65536);
        }
    }
    
    public boolean isAlpha(char ch){
        return 'a' <= ch && ch <= 'z';
    }
}