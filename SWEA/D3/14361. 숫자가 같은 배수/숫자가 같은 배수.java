import java.util.*;
import java.io.*;

class Solution
{
    static int[] arr;
    
	public static void main(String args[]) throws Exception
	{
		StringBuilder sb = new StringBuilder();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= t ; tc++) {
			
			sb.append("#").append(tc).append(" ");
			
			int n = Integer.parseInt(br.readLine());
            int temp = n;
			arr = new int[10];
			
			while(n > 0) {
				arr[n % 10]++;
				n /= 10;
			}
			
			String ans = "impossible";
			
			for(int i = 2; i <= 9; i++) {
				if(check(temp * i)) {
					ans = "possible";
					break;
				}
			}
			
			sb.append(ans);
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
    
    public static boolean check(int num) {
		
		int[] temp = new int[10];
		
		while(num > 0) {
			temp[num % 10]++;
			num /= 10;
		}
		
		for (int i = 0; i < 10; i++) {
			if(arr[i] != temp[i]) {
				return false;
			}
		}
		
		return true;
	}
}