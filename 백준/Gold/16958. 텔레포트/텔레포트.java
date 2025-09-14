import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        City[] cities = new City[n];
        List<Integer> specials = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            cities[i] = new City(x, y, (s == 1));
            if (cities[i].special) {
                specials.add(i);
            }
        }

        int[] arr = new int[n];
        Arrays.fill(arr, Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            if (cities[i].special) {
                arr[i] = 0;
                continue;
            }

            for (int s : specials) {
                arr[i] = Math.min(arr[i], cities[i].getTime(cities[s]));
            }
        }

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            System.out.println(Math.min(cities[a].getTime(cities[b]), arr[a] + t + arr[b]));
        }
    }

    static class City {
        int r, c;
        boolean special;

        public City(int r, int c, boolean special) {
            this.r = r;
            this.c = c;
            this.special = special;
        }

        public int getTime(City other) {
            return Math.abs(this.r - other.r) + Math.abs(this.c - other.c);
        }
    }
}