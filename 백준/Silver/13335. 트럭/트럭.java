import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        Queue<Integer> wait = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            wait.offer(Integer.parseInt(st.nextToken()));
        }

        Queue<Truck> bridge = new ArrayDeque<>();

        int crossed = 0;
        int weight = 0;     //현재 다리 위에 있는 트럭의 무게 합
        int time = 0;       //걸린 시간

        while (crossed < n) {

            time++;

            if (!bridge.isEmpty() && bridge.peek().idx > w) {
                weight -= bridge.poll().val;
                crossed++;
            }

            if (!wait.isEmpty() && weight + wait.peek() <= l) {
                int t_w = wait.poll();
                bridge.offer(new Truck(1, t_w));
                weight += t_w;
            }

            for (Truck truck : bridge) {
                truck.idx++;
            }
        }

        System.out.println(time);
    }

    static class Truck {
        int idx, val;

        public Truck(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }
}