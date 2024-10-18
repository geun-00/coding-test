import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 1; i <= n; i++) {

            String s = String.valueOf(i);

            int count = 0;
            for (int j = 0; j < s.length(); j++) {
                int num = s.charAt(j) - '0';
                if (num == 3 || num == 6 || num == 9) {
                    count++;
                }
            }

            if (count == 0) {
                System.out.print(i + " ");
            } else {
                String temp = "";
                for (int j = 0; j < count; j++) {
                    temp += "-";
                }
                System.out.print(temp + " ");
            }
	}
}
}