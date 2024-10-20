import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        for(int i = 1; i <= t; i++) {
        	int n = sc.nextInt();
            System.out.println("#" + i + " " + (n / 3));
        }
	}
}