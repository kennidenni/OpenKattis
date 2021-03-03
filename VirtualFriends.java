import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class VirtualFriends {
    static Kattio io;

    public static void main(String[] args) {
        io = new Kattio(System.in, System.out);

        int times = io.getInt();

        while (times > 0) {
            int friends = io.getInt();
            HashMap<String, Integer> hash = new HashMap<>();
            UnionFind uf = new UnionFind(friends * 2);

            int run = 0;
            int j = 0;
            while (run < friends) {
                String f = io.getWord();
                String s = io.getWord();

                if (hash.get(f) == null) {
                    hash.put(f, j);
                    j++;
                }
                if (hash.get(s) == null) {
                    hash.put(s, j);
                    j++;
                }
                int f1 = hash.get(f);
                int s1 = hash.get(s);
                uf.union(f1, s1);

                run++;
            }
            times--;
        }
        io.close();

    }

    public static class UnionFind {
        int[] parent;
        int[] size;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public void union(int p, int q) {
            int x = find(p);
            int y = find(q);
            if (x == y) {
                io.println(size[y]);
                return;
            }

            parent[x] = y;
            size[y] += size[x];
            io.println(size[y]);
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
