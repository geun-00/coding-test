import java.util.*;
import java.io.*;

class Solution
{
    static int win;
    static int lose;
    static int[] k_cards;
    static int[] i_cards;
    static boolean[] visit;
    
	public static void main(String args[]) throws Exception
	{
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int t = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = 1; i <= t; i++) {
            
        	sb.append("#").append(i).append(" ");
            
            win = 0;
            lose = 0;
            k_cards = new int[9];
            i_cards = new int[9];
            visit = new boolean[9];
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            boolean[] seleceted = new boolean[19];
            
            for(int j = 0; j < 9; j++) {
            	k_cards[j] = Integer.parseInt(st.nextToken());
                seleceted[k_cards[j]] = true;
            }
            
            int idx = 0;
            for(int j = 1;  j < 19; j++) {
            	if(!seleceted[j]) {
                	i_cards[idx++] = j;
                }
            }
     
            solve(0, 0, 0);
            
            sb.append(win).append(" ").append(lose).append("\n");
        }
        
        System.out.println(sb);
	}
    
    public static void solve(int k_sum, int i_sum, int depth) {
    	
        if(depth == 9) {
            if(k_sum > i_sum){
            	win++;
            }
            if(k_sum < i_sum){
            	lose++;
            }
        	return;
        }
        
        for(int i = 0; i < 9; i++) {
        	if(visit[i]) continue;
            
            visit[i] = true;
            
            int k_card = k_cards[depth];
            int i_card = i_cards[i];
            int sum = k_card + i_card;
            if(k_card > i_card) {
                
            	solve(k_sum + sum, i_sum, depth + 1);
            } else {
            	solve(k_sum, i_sum + sum, depth + 1);
            }
            
            visit[i] = false;
        }
    }
}