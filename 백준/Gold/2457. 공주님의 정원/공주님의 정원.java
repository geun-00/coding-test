import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Flower[] flowers = new Flower[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int blossomMonth = Integer.parseInt(st.nextToken());
            int blossomDay = Integer.parseInt(st.nextToken());
            int fallMonth = Integer.parseInt(st.nextToken());
            int fallDay = Integer.parseInt(st.nextToken());

            flowers[i] = new Flower(
                    blossomMonth * 100 + blossomDay,
                    fallMonth * 100 + fallDay
            );
        }

        Arrays.sort(flowers);

        int start = 301;
        int end = 1201;
        int curEnd = 0;
        int count = 0;
        int index = 0;

        while (start < end) {

            boolean found = false;
            int nextEnd = curEnd;

            while (index < n && flowers[index].blossomDay <= start) {
                if (flowers[index].fallDay > nextEnd) {
                    nextEnd = flowers[index].fallDay;
                    found = true;
                }
                index++;
            }

            if (!found) {
                System.out.println(0);
                return;
            }

            count++;
            start = nextEnd;
            curEnd = nextEnd;
        }
        System.out.println(count);

    }

    static class Flower implements Comparable<Flower> {
        int blossomDay;
        int fallDay;

        public Flower(int blossomDay, int fallDay) {
            this.blossomDay = blossomDay;
            this.fallDay = fallDay;
        }

        @Override
        public int compareTo(Flower o) {
           
            return this.blossomDay - o.blossomDay;
        }
    }
}