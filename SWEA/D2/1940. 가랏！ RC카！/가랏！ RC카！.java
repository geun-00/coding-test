import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for(int i = 1; i <= t; i++) {

            int n = Integer.parseInt(br.readLine());

            int speed = 0;
            int dist = 0;

            for (int j = 0; j < n; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                int command = Integer.parseInt(st.nextToken());

                if (command == 0) {
                    dist += speed;
                } else if (command == 1) {
                    speed += Integer.parseInt(st.nextToken());
                    dist += speed;
                } else {
                    speed -= Integer.parseInt(st.nextToken());
                    speed = Math.max(0, speed);
                    dist += speed;
                }
            }

            System.out.println("#" + i + " " + dist);
        }
	}
}