import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
        StringBuilder sb = new StringBuilder();
        
		boolean[] arr = new boolean[1_000_001];
			
        for(int i = 2; i <= Math.sqrt(1_000_000); i++) {
            if(!arr[i]) {
                
                for(int j = i * 2; j <= 1_000_000; j += i) {
                    arr[j] = true;
                }
            }
        }
			
        for(int i = 2; i <= 1_000_000; i++) {
            if(!arr[i]) {
                sb.append(i).append(" ");
            }
        }

        System.out.println(sb);
	}
}