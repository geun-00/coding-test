import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            HashMap<String, List<Integer>> map = new HashMap<>();
            String[] arr = br.readLine().split(" ");

            for (int i = 0; i < arr.length; i++) {
                String s = arr[i];
                if (map.get(s) == null) {
                    map.put(s, new ArrayList<>());
                }

                map.get(s).add(i);
            }

            while (true) {
                String s = br.readLine();
                if (s.equals("what does the fox say?")) {
                    break;
                }

                String[] split = s.split(" ");
                List<Integer> indexList = map.getOrDefault(split[2], Collections.emptyList());
                for (int index : indexList) {
                    arr[index] = "";
                }
            }

            for (String s : arr) {
                if (!s.isEmpty()) {
                    sb.append(s).append(" ");
                }
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }
}