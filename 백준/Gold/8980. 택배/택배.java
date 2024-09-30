import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); //마을 수
        int c = Integer.parseInt(st.nextToken()); //트럭의 용량

        int m = Integer.parseInt(br.readLine()); //보내는 박스 개수

        Box[] boxes = new Box[m];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int amount = Integer.parseInt(st.nextToken());

            boxes[i] = new Box(from, to, amount);
        }

        Arrays.sort(boxes);

        int[] capacity = new int[n + 1];
        Arrays.fill(capacity, c);

        int total = 0;

        for (Box box : boxes) {

            int from = box.from;
            int to = box.to;
            int amount = box.amount;

            int max = Integer.MAX_VALUE;
            for (int i = from; i < to; i++) {
                max = Math.min(max, capacity[i]);
            }

            max = Math.min(max, amount);

            for (int i = from; i < to; i++) {
                capacity[i] -= max;
            }

            total += max;
        }

        System.out.println(total);
    }

    static class Box implements Comparable<Box> {
        int from, to, amount;

        public Box(int from, int to, int amount) {
            this.from = from;
            this.to = to;
            this.amount = amount;
        }

        @Override
        public int compareTo(Box o) {
            if (this.to == o.to) {
                return this.from - o.from;
            }
            return this.to - o.to;
        }
    }
}
