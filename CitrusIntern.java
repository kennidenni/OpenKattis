import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;


public class CitrusIntern {
    static long[] inside;
    static long[] over;
    static long[] down;

    static HashMap<Integer, Member> tree;

    public static void main(String[] args) {
        tree = new HashMap<>();

        readInput();

        int root = findRoot();

        solveDomSet(root);

        printAnswer(root);

    }

    private static void readInput() {
        Kattio io = new Kattio(System.in, System.out);

        int members = io.getInt();
        inside = new long[members];
        over = new long[members];
        down = new long[members];

        for (int i = 0; i < members; i++) {
            if (!tree.containsKey(i))
                tree.put(i, new Member(i, false));

            int initCost = io.getInt();
            tree.get(i).cost = initCost;

            int subs = io.getInt();
            for (int j = 0; j < subs; j++) {
                int subId = io.getInt();

                tree.get(i).children.add(subId);
                if (!tree.containsKey(subId))
                    tree.put(subId, new Member(subId, true));
                else
                    tree.get(subId).isChild = true;
            }
        }
    }

    private static int findRoot() {
        for (int i = 0; i < tree.keySet().size(); i++) {
            if (!tree.get(i).isChild) {
                return i;
            }
        }
        return 0;
    }

    static void solveDomSet(int parent) {
        inside[parent] = tree.get(parent).cost;
        over[parent] = 0;
        down[parent] = Integer.MAX_VALUE;

        long minDiff = Long.MAX_VALUE;

        for (int child : tree.get(parent).children) {
            solveDomSet(child);
            inside[parent] += over[child];
            over[parent] += Math.min(inside[child], down[child]);

            long diff = inside[child] - down[child];

            if (minDiff > diff)
                minDiff = diff;
        }

        down[parent] = over[parent];
        if (minDiff > 0)
            down[parent] += minDiff;
    }

    private static void printAnswer(int root) {
        if (inside[root] > down[root]) {
            System.out.println(down[root]);
        } else {
            System.out.println(inside[root]);
        }
    }

    static class Member {
        int index;
        int cost;

        boolean isChild;

        ArrayList<Integer> children;

        public Member(int index, boolean isChild) {
            this.index = index;
            this.isChild = isChild;
            children = new ArrayList<>();
        }
    }

    static class Kattio extends PrintWriter {
        public Kattio(InputStream i) {
            super(new BufferedOutputStream(System.out));
            r = new BufferedReader(new InputStreamReader(i));
        }

        public Kattio(InputStream i, OutputStream o) {
            super(new BufferedOutputStream(o));
            r = new BufferedReader(new InputStreamReader(i));
        }

        public boolean hasMoreTokens() {
            return peekToken() != null;
        }

        public int getInt() {
            return Integer.parseInt(nextToken());
        }

        public double getDouble() {
            return Double.parseDouble(nextToken());
        }

        public long getLong() {
            return Long.parseLong(nextToken());
        }

        public String getWord() {
            return nextToken();
        }

        private BufferedReader r;
        private String line;
        private StringTokenizer st;
        private String token;

        private String peekToken() {
            if (token == null)
                try {
                    while (st == null || !st.hasMoreTokens()) {
                        line = r.readLine();
                        if (line == null) return null;
                        st = new StringTokenizer(line);
                    }
                    token = st.nextToken();
                } catch (IOException e) {
                }
            return token;
        }

        private String nextToken() {
            String ans = peekToken();
            token = null;
            return ans;
        }
    }
}
