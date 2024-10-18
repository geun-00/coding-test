import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		String[] grade = {"D0", "C-", "C0", "C+", "B-", "B0", "B+", "A-", "A0", "A+"};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 1; i <= t; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            double[] scores = new double[n];

            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                double sum = (a * 0.35) + (b * 0.45) + (c * 0.2);
                scores[j] = sum;
            }

            double target = scores[k - 1];

            Arrays.sort(scores);

            int index = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (scores[j] == target) {
                    index = j;
                    break;
                }
            }

            System.out.println("#" + i + " " + grade[index / (n / 10)]);
        }
	}
}