import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        TreeMap<Integer, List<String>> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            if (!map.containsKey(age)) {
                map.put(age, new ArrayList<>());
            }
            map.get(age).add(name);
        }

        StringBuilder sb = new StringBuilder();
        for (int age : map.keySet()) {
            for (String name : map.get(age)) {
                sb.append(age).append(" ").append(name).append("\n");
            }
        }
        System.out.print(sb);
    }
}