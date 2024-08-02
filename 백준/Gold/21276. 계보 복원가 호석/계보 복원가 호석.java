import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        HashMap<String, ArrayList<String>> xToy = new HashMap<>();
        HashMap<String, ArrayList<String>> yTox = new HashMap<>();
        ArrayList<String> people = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            String name = st.nextToken();
            people.add(name);
            xToy.put(name, new ArrayList<>());
            yTox.put(name, new ArrayList<>());
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            String x = st.nextToken();
            String y = st.nextToken();

            xToy.get(x).add(y);
            yTox.get(y).add(x);
        }

        ArrayList<String> root = new ArrayList<>();
        for (String key : xToy.keySet()) {
            if (xToy.get(key).isEmpty()) {
                root.add(key);
            }
        }
        root.sort(null);

        System.out.println(root.size()); //가문의 개수
        for (String r : root) {
            System.out.print(r + " ");
        }
        System.out.println();

        people.sort(null);

        for (String person : people) {
            ArrayList<String> list = yTox.get(person);

            if (list.size() <= 1) {
                list.sort(null);
                System.out.print(person + " " + list.size() + " ");
                for (String s : list) {
                    System.out.print(s + " ");
                }
                System.out.println();
                continue;
            }

            ArrayList<String> temp = new ArrayList<>();
            for (String s : list) {
                if (xToy.get(s).size() == 1) {
                    temp.add(s);
                }
            }

            temp.sort(null);
            System.out.print(person + " " + temp.size() + " ");
            for (String s : temp) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
}
