package Baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/13335">백준 13335번 - 시뮬레이션 : 트럭</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-13335%EB%B2%88-%ED%8A%B8%EB%9F%AD">velog</a>
 * @since 2024-11-10
 */
public class BJ_13335 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        //대기 큐
        Queue<Integer> wait = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            wait.offer(Integer.parseInt(st.nextToken()));
        }

        //다리 위에 있는 트럭
        Queue<Truck> bridge = new ArrayDeque<>();

        int crossed = 0;    //다리를 건넌 트럭의 수
        int weight = 0;     //현재 다리 위에 있는 트럭의 무게 합
        int time = 0;       //걸린 시간

        while (crossed < n) {

            time++;

            //건널 수 있는 트럭 건너기
            if (!bridge.isEmpty() && bridge.peek().pos > w) {
                weight -= bridge.poll().val;
                crossed++;
            }

            //다리 위에 올라갈 수 있는 트럭 올라가기
            if (!wait.isEmpty() && weight + wait.peek() <= l) {
                int t_w = wait.poll();
                bridge.offer(new Truck(1, t_w));
                weight += t_w;
            }

            //다리 위에 있는 트럭 한 칸씩 이동
            for (Truck truck : bridge) {
                truck.pos++;
            }
        }

        System.out.println(time);
    }

    static class Truck {

        //트럭이 있는 위치, 무게
        int pos, val;

        public Truck(int pos, int val) {
            this.pos = pos;
            this.val = val;
        }
    }
}