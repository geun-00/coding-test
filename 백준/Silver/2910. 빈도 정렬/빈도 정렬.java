import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        HashMap<Integer, Integer> order = new HashMap<>();
        HashMap<Integer, Integer> freq = new HashMap<>();

        int o = 1;

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());

            order.putIfAbsent(num, o++);

            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        ArrayList<Data> list = new ArrayList<>();

        for (int key : freq.keySet()) {
            list.add(new Data(key, freq.get(key), order.get(key)));
        }

        list.sort(null);

        StringBuilder sb = new StringBuilder();

        for (Data data : list) {

            for (int i = 0; i < data.freq; i++) {
                sb.append(data.num).append(" ");
            }
        }
        System.out.print(sb);
    }

    static class Data implements Comparable<Data>{

        int num, freq, order;

        public Data(int num, int freq, int order) {
            this.num = num;
            this.freq = freq;
            this.order = order;
        }

        @Override
        public int compareTo(Data o) {
            if (this.freq == o.freq) {
                return this.order - o.order;
            }

            return o.freq - this.freq;
        }
    }
}