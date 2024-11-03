import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int root = Integer.parseInt(st.nextToken());
            Tree tree = new Tree(root, k);

            for (int j = 0; j < k - 1; j++) {
                int child = Integer.parseInt(st.nextToken());
                tree.insert(child);
            }

            Arrays.sort(tree.index);
            set.add(Arrays.toString(tree.index));
        }

        System.out.println(set.size());

    }

    static class Node {

        int num;
        Node left, right;

        public Node(int num) {
            this.num = num;
        }
    }

    static class Tree {

        Node root;
        int[] index;
        int idx;

        public Tree(int num, int size){
            this.root = new Node(num);
            this.index = new int[size];
            this.index[0] = 1;
            this.idx = 0;
        }

        public void insert(int num) {
            Node node = new Node(num);

            Node parent = root;

            int childNum = 1;
            this.idx++;

            while (true) {
                if (parent.num > num) {
                    childNum *= 2;
                    if (parent.left == null) {
                        parent.left = node;
                        this.index[this.idx] = childNum;
                        return;
                    }
                    parent = parent.left;
                }
                else if (parent.num < num) {
                    childNum = childNum * 2 + 1;
                    if (parent.right == null) {
                        parent.right = node;
                        this.index[this.idx] = childNum;
                        return;
                    }
                    parent = parent.right;
                }
            }
        }
    }
}
