import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		HashMap<String, Character> map = new HashMap<>();
        map.put("0001101", '0');
        map.put("0011001", '1');
        map.put("0010011", '2');
        map.put("0111101", '3');
        map.put("0100011", '4');
        map.put("0110001", '5');
        map.put("0101111", '6');
        map.put("0111011", '7');
        map.put("0110111", '8');
        map.put("0001011", '9');

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            char[][] arr = new char[n][m];
            for (int i = 0; i < n; i++) {
                arr[i] = br.readLine().toCharArray();
            }

            StringBuilder target = new StringBuilder();
            for (int i = 0; i < n; i++) {
                boolean flag = false;
                for (int j = m - 1; j >= 0; j--) {
                    if (arr[i][j] == '1') {
                        for (int k = j - 56 + 1; k <= j; k++) {
                            target.append(arr[i][k]);
                        }
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    break;
                }
            }

            String s = target.toString();
            StringBuilder code = new StringBuilder();
            for (int i = 0; i < s.length(); i += 7) {
                code.append(map.get(s.substring(i, i + 7)));
            }

            String c = '0'+ code.toString();

            int oddSum = 0;
            int evenSum = 0;
            int sum = 0;

            for (int i = 1; i < c.length(); i++) {
                int num = c.charAt(i) - '0';
                if (i % 2 == 1) {
                    oddSum += num;
                } else {
                    evenSum += num;
                }
                sum += num;
            }

            int res;
            if ((oddSum * 3 + evenSum) % 10 == 0) {
                res = sum;
            } else {
                res = 0;
            }


            System.out.println("#" + tc + " " + res);
        }
	}
}