import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= t; tc++) {

            sb.append("#").append(tc).append(" ");

            String s = br.readLine();

            int n = s.length();
            char[] arr = new char[n];

            for (int i = 0; i < n; i++) {
                arr[i] = s.charAt(i);
            }

            int min = getNum(arr);
            int max = getNum(arr);

            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    swap(arr, i, j);
                    if (arr[0] == '0') {
                        swap(arr, i, j);
                        continue;
                    }
                    int num = getNum(arr);
                    min = Math.min(min, num);
                    max = Math.max(max, num);
                    swap(arr, i, j);
                }
            }

            sb.append(min).append(" ").append(max);

            sb.append("\n");
        }

        System.out.print(sb);
	}
    
    public static int getNum(char[] arr) {

        String s = "";
        for (char c : arr) {
            s += c;
        }

        return Integer.parseInt(s);
    }

    public static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}